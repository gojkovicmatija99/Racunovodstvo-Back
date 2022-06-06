package raf.si.racunovodstvo.knjizenje.services;

import raf.si.racunovodstvo.knjizenje.reports.Reports;
import raf.si.racunovodstvo.knjizenje.reports.ReportsConstants;
import raf.si.racunovodstvo.knjizenje.reports.TableReport;
import raf.si.racunovodstvo.knjizenje.responses.BilansResponse;
import raf.si.racunovodstvo.knjizenje.services.impl.IBilansService;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

// http://www.cekos.rs/sites/default/files/media/obrasci/15085201-05.pdf
public class PromenaNaKapitalServiceHelper {

    private int godina1;

    private int godina2;

    private IBilansService bilansService;

    public PromenaNaKapitalServiceHelper(int godina1, int godina2, IBilansService bilansService) {
        this.godina1 = godina1;
        this.godina2 = godina2;
        this.bilansService = bilansService;
    }

    public Reports makePromenaNaKapitalTableReport() {
        List<List<Double>> rows = calculateForGodina(godina1);
        rows.addAll(calculateForGodina(godina2));
        List<List<String>> listOfStringRows = new ArrayList<>();
        rows.forEach(row -> {
            List<String> stringRow = row.stream().map(d -> String.valueOf(d)).collect(Collectors.toList());
            listOfStringRows.add(stringRow);
        });
        return new TableReport(null, null, null, null, listOfStringRows);
    }

    private List<List<Double>> calculateForGodina(int godina) {
        Date startOfGodina = getDateForDayMonthYear(1, 1, godina);
        Date endOfGodina = getDateForDayMonthYear(31, 12, godina);
        List<List<Double>> rows = new ArrayList<>();
        rows.add(calculateRow(startOfGodina));
        rows.add(calculateRow(endOfGodina));
        rows.add(calculateRowDiff(rows.get(0), rows.get(1)));
        return rows;
    }

    private Date getDateForDayMonthYear(int day, int month, int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        return calendar.getTime();
    }

    private List<Double> calculateRow(Date dateLimit) {
        List<Double> row = new ArrayList<>();
        row.add(calculate2ndColumnHelper(dateLimit));
        row.add(calculateForBrojKonta(dateLimit, "309"));
        row.add(calculateForBrojKonta(dateLimit, "31"));
        row.add(calculate5thColumnHelper(dateLimit));
        row.add(calculateForBrojKonta(dateLimit, "33"));
        row.add(calculateForBrojKonta(dateLimit, "34"));
        row.add(calculateForBrojKonta(dateLimit, "35"));
        double sum = row.stream().mapToDouble(value -> value).sum() - 2 * row.get(6);
        row.add(sum);
        return row;
    }

    private List<Double> calculateRowDiff(List<Double> pocetakGodine, List<Double> krajGodine) {
        List<Double> diff = new ArrayList<>();
        Iterator<Double> i1 = pocetakGodine.iterator();
        Iterator<Double> i2= krajGodine.iterator();
        while(i1.hasNext() && i2.hasNext())
        {
            diff.add(i1.next() - i2.next());
        }
        return diff;
    }

    private double calculate2ndColumnHelper(Date dateLimit) {
        double konto30Saldo = calculateForBrojKonta(dateLimit, "30");
        double konto306Saldo = calculateForBrojKonta(dateLimit, "306");
        double konto309Saldo = calculateForBrojKonta(dateLimit, "309");

        return konto30Saldo - konto306Saldo - konto309Saldo;
    }

    private double calculate5thColumnHelper(Date dateLimit) {
        double konto306Saldo = calculateForBrojKonta(dateLimit, "306");
        double konto32Saldo = calculateForBrojKonta(dateLimit, "32");

        return konto32Saldo + konto306Saldo;
    }

    private double calculateForBrojKonta(Date dateLimit, String brojKonta) {
        List<BilansResponse> bilansResponse = bilansService.findBrutoBilans(brojKonta, brojKonta, new Date(0), dateLimit);
        double saldoZaKonto = bilansResponse.stream()
                                            .filter(br -> br.getBrojKonta().equals(brojKonta))
                                            .mapToDouble(br -> br.getSaldo()).sum();
        return saldoZaKonto;
    }


}
