package raf.si.racunovodstvo.knjizenje.services;

import raf.si.racunovodstvo.knjizenje.model.Preduzece;
import raf.si.racunovodstvo.knjizenje.reports.Reports;
import raf.si.racunovodstvo.knjizenje.reports.TableReport;
import raf.si.racunovodstvo.knjizenje.responses.TransakcijaResponse;

import java.util.ArrayList;
import java.util.List;

public class StatickiIzvestajOTransakcijamaHelper {

    private String naslov;
    private Preduzece preduzece;
    private List<TransakcijaResponse> transakcijaResponses;

    private static final List<String> HEADER = List.of("Sifra transakcije","Tip","Iznos");

    public StatickiIzvestajOTransakcijamaHelper(String naslov,
                                                Preduzece preduzece,
                                                List<TransakcijaResponse> transakcijaResponses) {
        this.naslov = naslov;
        this.preduzece = preduzece;
        this.transakcijaResponses = transakcijaResponses;
    }

    public Reports makeTableReport() {
        List<List<String>> table = new ArrayList<>();
        double iznos = 0;
        for (TransakcijaResponse transakcija : transakcijaResponses) {
            List<String> row = new ArrayList<>();
            row.add(transakcija.getSifraTransakcije().getSifra().toString());
            row.add(transakcija.getTipTransakcije().toString());
            row.add(transakcija.getIznos().toString());
            iznos += transakcija.getIznos();
            table.add(row);
        }
        table.add(List.of("", "", String.valueOf(iznos)));
        return new TableReport("author", String.format("Naslov: %s\nIme komitenta: %s", naslov,preduzece.getNaziv()), "footer", HEADER, table);
    }

}
