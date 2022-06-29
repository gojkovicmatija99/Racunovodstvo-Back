package raf.si.racunovodstvo.knjizenje.bootstrap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import raf.si.racunovodstvo.knjizenje.model.*;
import raf.si.racunovodstvo.knjizenje.model.enums.TipDokumenta;
import raf.si.racunovodstvo.knjizenje.model.enums.TipFakture;
import raf.si.racunovodstvo.knjizenje.repositories.*;
import raf.si.racunovodstvo.knjizenje.model.enums.TipTransakcije;

import java.util.*;

@Component
public class BootstrapData implements CommandLineRunner {

    private final Logger log = LoggerFactory.getLogger(BootstrapData.class);
    private final FakturaRepository fakturaRepository;
    private final KontnaGrupaRepository kontnaGrupaRepository;
    private final KontoRepository kontoRepository;
    private final KnjizenjeRepository knjizenjeRepository;
    private final ProfitniCentarRepository profitniCentarRepository;
    private final TroskovniCentarRepository troskovniCentarRepository;
    private final BazniKontoRepository bazniKontoRepository;
    private final PovracajRepository povracajRepository;
    private final TransakcijaRepository transakcijaRepository;
    private final SifraTransakcijeRepository sifraTransakcijeRepository;


    @Autowired
    public BootstrapData(FakturaRepository fakturaRepository,
                         KontoRepository kontoRepository,
                         KontnaGrupaRepository kontnaGrupaRepository,
                         KnjizenjeRepository knjizenjeRepository,
                         TransakcijaRepository transakcijaRepository, SifraTransakcijeRepository sifraTransakcijeRepository,
                         ProfitniCentarRepository profitniCentarRepository,
                         TroskovniCentarRepository troskovniCentarRepository,
                         BazniKontoRepository bazniKontoRepository,
                         PovracajRepository povracajRepository
    ) {
        this.fakturaRepository = fakturaRepository;
        this.kontoRepository = kontoRepository;
        this.knjizenjeRepository = knjizenjeRepository;
        this.kontnaGrupaRepository = kontnaGrupaRepository;
        this.transakcijaRepository = transakcijaRepository;
        this.sifraTransakcijeRepository = sifraTransakcijeRepository;
        this.profitniCentarRepository = profitniCentarRepository;
        this.troskovniCentarRepository = troskovniCentarRepository;
        this.bazniKontoRepository = bazniKontoRepository;
        this.povracajRepository = povracajRepository;
    }

    private Faktura getDefaultFaktura() {

        Faktura f1 = new Faktura();
        f1.setBrojFakture("1");
        f1.setIznos(1000.00);
        f1.setTipFakture(TipFakture.ULAZNA_FAKTURA);
        f1.setDatumIzdavanja(new Date());
        f1.setDatumPlacanja(new Date());
        f1.setKurs(117.8);
        f1.setNaplata(1000.00);
        f1.setPorez(10.00);
        f1.setPorezProcenat(1.00);
        f1.setProdajnaVrednost(1000.00);
        f1.setValuta("EUR");
        f1.setBrojDokumenta("1234");
        f1.setRokZaPlacanje(new Date());
        f1.setTipDokumenta(TipDokumenta.FAKTURA);

        return f1;
    }

    private Preduzece getDefaultPreduzece() {
        Preduzece p1 = new Preduzece();
        p1.setNaziv("Test Preduzece");
        p1.setPib("111222333");
        p1.setAdresa("test adresa");
        p1.setGrad("Beograd");

        return p1;
    }

    private Konto createKonto(KontnaGrupa kg, Knjizenje knj, Double duguje, Double potrazuje) {
        Konto konto = new Konto();
        konto.setDuguje(duguje);
        konto.setPotrazuje(potrazuje);
        konto.setKnjizenje(knj);
        konto.setKontnaGrupa(kg);
        return konto;
    }

    private SifraTransakcije getRandomSifraTransakcije() {
        SifraTransakcije st = new SifraTransakcije();
        st.setSifra(1010L);
        st.setNazivTransakcije("1010LLLLL");
        return st;
    }

    private Transakcija getRandomTransakcija() {
        Transakcija tr = new Transakcija();
        tr.setTipDokumenta(TipDokumenta.FAKTURA);
        tr.setIznos(222.33);
        tr.setTipTransakcije(TipTransakcije.UPLATA);
        tr.setDatumTransakcije(new Date());
        return tr;
    }

    private Povracaj createPovracaj(String brojPovracaja, Date datum, Double prodajnaVrednost) {
        Povracaj povracaj = new Povracaj();
        povracaj.setBrojPovracaja(brojPovracaja);
        povracaj.setDatumPovracaja(datum);
        povracaj.setProdajnaVrednost(prodajnaVrednost);

        return povracaj;
    }

    @Override
    public void run(String... args) throws Exception {

        log.info("Loading Data...");

        Preduzece p1 = new Preduzece();
        p1.setNaziv("ThinkStudio");
        p1.setPib("102979306");
        p1.setRacun("908‑10501‑97");
        p1.setAdresa("Držićeva 11");
        p1.setGrad("Beograd");
        p1.setTelefon("0112324444");
        p1.setEmail("office@thinkstudio.com");
        p1.setWebAdresa("thinkstudio.com");
        p1.setIsActive(true);

        Preduzece p2 = new Preduzece();
        p2.setNaziv("Universal UK");
        p2.setPib("1238483921");
        p2.setRacun("55743513");
        p2.setAdresa("22 Old Gloucester Street");
        p2.setGrad("London");
        p2.setTelefon("0113928422");
        p2.setEmail("london@universal.co.uk");
        p2.setWebAdresa("universal.co.uk");
        p2.setIsActive(true);
        p2.setKomentar("UK");

        Preduzece p3 = new Preduzece();
        p3.setNaziv("Blue Marble Inc");
        p3.setPib("194382931");
        p3.setRacun("908‑11501‑07");
        p3.setAdresa("Bulevar Mihajla Pupina 13");
        p3.setGrad("Beograd");
        p3.setTelefon("7975777666");
        p3.setEmail("office@bluemarble.com");
        p3.setWebAdresa("bluemarble.com");
        p3.setIsActive(true);

        Preduzece p4 = new Preduzece();
        p4.setNaziv("BP Production");
        p4.setPib("123-442-1134");
        p4.setRacun("132355513");
        p4.setAdresa("7115 3rd Ave");
        p4.setGrad("New York City");
        p4.setTelefon("555-1234");
        p4.setEmail("contact@bp.com");
        p4.setWebAdresa("bp.com");
        p4.setIsActive(true);
        p4.setKomentar("SAD");

        Preduzece p5 = new Preduzece();
        p5.setNaziv("Mark Cinema");
        p5.setPib("129438322");
        p5.setRacun("908‑14501‑28");
        p5.setAdresa("Bulevar Zorana Đinđića 11");
        p5.setGrad("Beograd");
        p5.setTelefon("0119303492");
        p5.setEmail("markcinema@gmail.com");
        p5.setWebAdresa("markcinema.rs");
        p5.setIsActive(false);

        Preduzece p6 = new Preduzece();
        p6.setNaziv("Fashion World");
        p6.setPib("193849293");
        p6.setRacun("908‑28311‑28");
        p6.setAdresa("Semjuela Beketa 55");
        p6.setGrad("Beograd");
        p6.setTelefon("011948293");
        p6.setEmail("office@fashionworld.com");
        p6.setWebAdresa("fashionworld.com");
        p6.setIsActive(false);

        Faktura fu1 = new Faktura();
        fu1.setBrojFakture("F23/11");
        fu1.setBrojDokumenta(fu1.getBrojFakture());
        fu1.setDatumIzdavanja(new Date(2021, Calendar.MAY, 11));
        fu1.setRokZaPlacanje(new Date(2021, Calendar.MAY, 17));
        fu1.setDatumPlacanja(new Date(2021, Calendar.MAY, 16));
        fu1.setProdajnaVrednost(11300.00);
        fu1.setRabatProcenat(5.00);
        fu1.setPorezProcenat(20.00);
        fu1.setValuta("DIN");
        fu1.setTipFakture(TipFakture.ULAZNA_FAKTURA);
        fu1.setTipDokumenta(TipDokumenta.FAKTURA);
        fu1.setPreduzeceId(p1.getPreduzeceId());
        fu1.setKurs(117.00);
        fu1.setNaplata(0.00);

        Faktura fu2 = new Faktura();
        fu2.setBrojFakture("F23/12");
        fu2.setBrojDokumenta(fu2.getBrojFakture());
        fu2.setDatumIzdavanja(new Date(2021, Calendar.MAY, 11));
        fu2.setRokZaPlacanje(new Date(2021, Calendar.MAY, 17));
        fu2.setDatumPlacanja(new Date(2021, Calendar.MAY, 16));
        fu2.setProdajnaVrednost(12400.00);
        fu2.setRabatProcenat(0.00);
        fu2.setPorezProcenat(20.00);
        fu2.setValuta("DIN");
        fu2.setTipFakture(TipFakture.ULAZNA_FAKTURA);
        fu2.setTipDokumenta(TipDokumenta.FAKTURA);
        fu2.setPreduzeceId(p1.getPreduzeceId());
        fu2.setKurs(117.00);
        fu2.setNaplata(0.00);

        Faktura fu3 = new Faktura();
        fu3.setBrojFakture("F23/13");
        fu3.setBrojDokumenta(fu3.getBrojFakture());
        fu3.setDatumIzdavanja(new Date(2021, Calendar.MAY, 12));
        fu3.setRokZaPlacanje(new Date(2021, Calendar.MAY, 17));
        fu3.setDatumPlacanja(new Date(2021, Calendar.MAY, 16));
        fu3.setProdajnaVrednost(11000.00);
        fu3.setRabatProcenat(0.00);
        fu3.setPorezProcenat(20.00);
        fu3.setValuta("DIN");
        fu3.setTipFakture(TipFakture.ULAZNA_FAKTURA);
        fu3.setTipDokumenta(TipDokumenta.FAKTURA);
        fu3.setPreduzeceId(p1.getPreduzeceId());
        fu3.setKurs(117.00);
        fu3.setNaplata(0.00);

        Faktura fu4 = new Faktura();
        fu4.setBrojFakture("F23/14");
        fu4.setBrojDokumenta(fu4.getBrojFakture());
        fu4.setDatumIzdavanja(new Date(2021, Calendar.MAY, 12));
        fu4.setRokZaPlacanje(new Date(2021, Calendar.MAY, 17));
        fu4.setDatumPlacanja(new Date(2021, Calendar.MAY, 16));
        fu4.setProdajnaVrednost(22000.00);
        fu4.setRabatProcenat(0.00);
        fu4.setPorezProcenat(20.00);
        fu4.setValuta("DIN");
        fu4.setTipFakture(TipFakture.ULAZNA_FAKTURA);
        fu4.setTipDokumenta(TipDokumenta.FAKTURA);
        fu4.setPreduzeceId(p1.getPreduzeceId());
        fu4.setKurs(117.00);
        fu4.setNaplata(0.00);

        Faktura fu5 = new Faktura();
        fu5.setBrojFakture("F23/15");
        fu5.setBrojDokumenta(fu5.getBrojFakture());
        fu5.setDatumIzdavanja(new Date(2021, Calendar.MAY, 12));
        fu5.setRokZaPlacanje(new Date(2021, Calendar.MAY, 18));
        fu5.setDatumPlacanja(new Date(2021, Calendar.MAY, 12));
        fu5.setProdajnaVrednost(750000.00);
        fu5.setRabatProcenat(0.00);
        fu5.setPorezProcenat(20.00);
        fu5.setValuta("GBP");
        fu5.setKomentar("inostranstvo");
        fu5.setTipFakture(TipFakture.ULAZNA_FAKTURA);
        fu5.setTipDokumenta(TipDokumenta.FAKTURA);
        fu5.setPreduzeceId(p2.getPreduzeceId());
        fu5.setKurs(117.00);
        fu5.setNaplata(0.00);

        Faktura fu6 = new Faktura();
        fu6.setBrojFakture("F23/16");
        fu6.setBrojDokumenta(fu6.getBrojFakture());
        fu6.setDatumIzdavanja(new Date(2021, Calendar.MAY, 12));
        fu6.setRokZaPlacanje(new Date(2021, Calendar.MAY, 12));
        fu6.setDatumPlacanja(new Date(2021, Calendar.MAY, 12));
        fu6.setProdajnaVrednost(55000.00);
        fu6.setRabatProcenat(0.00);
        fu6.setPorezProcenat(20.00);
        fu6.setValuta("GBP");
        fu6.setKomentar("inostranstvo");
        fu6.setTipFakture(TipFakture.ULAZNA_FAKTURA);
        fu6.setTipDokumenta(TipDokumenta.FAKTURA);
        fu6.setPreduzeceId(p2.getPreduzeceId());
        fu6.setKurs(117.00);
        fu6.setNaplata(0.00);

        Faktura fu7 = new Faktura();
        fu7.setBrojFakture("F23/17");
        fu7.setBrojDokumenta(fu7.getBrojFakture());
        fu7.setDatumIzdavanja(new Date(2021, Calendar.MAY, 12));
        fu7.setRokZaPlacanje(new Date(2021, Calendar.MAY, 12));
        fu7.setDatumPlacanja(new Date(2021, Calendar.MAY, 12));
        fu7.setProdajnaVrednost(55000.00);
        fu7.setRabatProcenat(0.00);
        fu7.setPorezProcenat(20.00);
        fu7.setValuta("GBP");
        fu7.setKomentar("inostranstvo");
        fu7.setTipFakture(TipFakture.ULAZNA_FAKTURA);
        fu7.setTipDokumenta(TipDokumenta.FAKTURA);
        fu7.setPreduzeceId(p2.getPreduzeceId());
        fu7.setKurs(117.00);
        fu7.setNaplata(0.00);

        Faktura fu8 = new Faktura();
        fu8.setBrojFakture("F24/11");
        fu8.setBrojDokumenta(fu8.getBrojFakture());
        fu8.setDatumIzdavanja(new Date(2021, Calendar.MAY, 12));
        fu8.setRokZaPlacanje(new Date(2021, Calendar.MAY, 12));
        fu8.setDatumPlacanja(new Date(2021, Calendar.MAY, 12));
        fu8.setProdajnaVrednost(750000.00);
        fu8.setRabatProcenat(10.00);
        fu8.setPorezProcenat(20.00);
        fu8.setValuta("GBP");
        fu8.setKomentar("inostranstvo");
        fu8.setTipFakture(TipFakture.ULAZNA_FAKTURA);
        fu8.setTipDokumenta(TipDokumenta.FAKTURA);
        fu8.setPreduzeceId(p2.getPreduzeceId());
        fu8.setKurs(117.00);
        fu8.setNaplata(0.00);

        Faktura fu9 = new Faktura();
        fu9.setBrojFakture("F24/12");
        fu9.setBrojDokumenta(fu9.getBrojFakture());
        fu9.setDatumIzdavanja(new Date(2021, Calendar.MAY, 12));
        fu9.setRokZaPlacanje(new Date(2021, Calendar.MAY, 12));
        fu9.setDatumPlacanja(new Date(2021, Calendar.MAY, 12));
        fu9.setProdajnaVrednost(100000.00);
        fu9.setRabatProcenat(10.00);
        fu9.setPorezProcenat(20.00);
        fu9.setValuta("GBP");
        fu9.setKomentar("inostranstvo");
        fu9.setTipFakture(TipFakture.ULAZNA_FAKTURA);
        fu9.setTipDokumenta(TipDokumenta.FAKTURA);
        fu9.setPreduzeceId(p2.getPreduzeceId());
        fu9.setKurs(117.00);
        fu9.setNaplata(0.00);

        Faktura fi1 = new Faktura();
        fi1.setBrojFakture("F24/13");
        fi1.setBrojDokumenta(fi1.getBrojFakture());
        fi1.setDatumIzdavanja(new Date(2021, Calendar.MAY, 13));
        fi1.setRokZaPlacanje(new Date(2021, Calendar.JULY, 31));
        fi1.setDatumPlacanja(new Date(2021, Calendar.JULY, 31));
        fi1.setProdajnaVrednost(23400.00);
        fi1.setRabatProcenat(5.00);
        fi1.setPorezProcenat(20.00);
        fi1.setValuta("DIN");
        fi1.setTipFakture(TipFakture.IZLAZNA_FAKTURA);
        fi1.setTipDokumenta(TipDokumenta.FAKTURA);
        fi1.setPreduzeceId(p3.getPreduzeceId());
        fi1.setKurs(117.00);
        fi1.setNaplata(0.00);

        Faktura fi2 = new Faktura();
        fi2.setBrojFakture("F24/14");
        fi2.setBrojDokumenta(fi2.getBrojFakture());
        fi2.setDatumIzdavanja(new Date(2021, Calendar.MAY, 13));
        fi2.setRokZaPlacanje(new Date(2021, Calendar.JULY, 31));
        fi2.setDatumPlacanja(new Date(2021, Calendar.MAY, 20));
        fi2.setProdajnaVrednost(23400.00);
        fi2.setRabatProcenat(10.00);
        fi2.setPorezProcenat(20.00);
        fi2.setValuta("DIN");
        fi2.setTipFakture(TipFakture.IZLAZNA_FAKTURA);
        fi2.setTipDokumenta(TipDokumenta.FAKTURA);
        fi2.setPreduzeceId(p3.getPreduzeceId());
        fi2.setKurs(117.00);
        fi2.setNaplata(0.00);

        Faktura fi3 = new Faktura();
        fi3.setBrojFakture("F24/15");
        fi3.setBrojDokumenta(fi3.getBrojFakture());
        fi3.setDatumIzdavanja(new Date(2021, Calendar.MAY, 15));
        fi3.setRokZaPlacanje(new Date(2021, Calendar.MAY, 22));
        fi3.setDatumPlacanja(new Date(2021, Calendar.MAY, 22));
        fi3.setProdajnaVrednost(23400.00);
        fi3.setRabatProcenat(5.00);
        fi3.setPorezProcenat(20.00);
        fi3.setValuta("GBP");
        fi3.setKomentar("inostranstvo");
        fi3.setTipFakture(TipFakture.IZLAZNA_FAKTURA);
        fi3.setTipDokumenta(TipDokumenta.FAKTURA);
        fi3.setPreduzeceId(p2.getPreduzeceId());
        fi3.setKurs(117.00);
        fi3.setNaplata(0.00);

        Faktura fi4 = new Faktura();
        fi4.setBrojFakture("F24/16");
        fi4.setBrojDokumenta(fi4.getBrojFakture());
        fi4.setDatumIzdavanja(new Date(2021, Calendar.MAY, 16));
        fi4.setRokZaPlacanje(new Date(2021, Calendar.MAY, 22));
        fi4.setDatumPlacanja(new Date(2021, Calendar.MAY, 22));
        fi4.setProdajnaVrednost(11000.00);
        fi4.setRabatProcenat(10.00);
        fi4.setPorezProcenat(20.00);
        fi4.setValuta("DIN");
        fi4.setTipFakture(TipFakture.IZLAZNA_FAKTURA);
        fi4.setTipDokumenta(TipDokumenta.FAKTURA);
        fi4.setPreduzeceId(p1.getPreduzeceId());
        fi4.setKurs(117.00);
        fi4.setNaplata(0.00);

        Faktura fi5 = new Faktura();
        fi5.setBrojFakture("F24/17");
        fi5.setBrojDokumenta(fi5.getBrojFakture());
        fi5.setDatumIzdavanja(new Date(2021, Calendar.MAY, 17));
        fi5.setRokZaPlacanje(new Date(2021, Calendar.JULY, 31));
        fi5.setDatumPlacanja(new Date(2021, Calendar.MAY, 17));
        fi5.setProdajnaVrednost(23400.00);
        fi5.setRabatProcenat(5.00);
        fi5.setPorezProcenat(20.00);
        fi5.setValuta("DIN");
        fi5.setTipFakture(TipFakture.IZLAZNA_FAKTURA);
        fi5.setTipDokumenta(TipDokumenta.FAKTURA);
        fi5.setPreduzeceId(p3.getPreduzeceId());
        fi5.setKurs(117.00);
        fi5.setNaplata(0.00);

        Faktura fi6 = new Faktura();
        fi6.setBrojFakture("F24/18");
        fi6.setBrojDokumenta(fi6.getBrojFakture());
        fi6.setDatumIzdavanja(new Date(2021, Calendar.MAY, 18));
        fi6.setRokZaPlacanje(new Date(2021, Calendar.OCTOBER, 31));
        fi6.setDatumPlacanja(new Date(2021, Calendar.MAY, 18));
        fi6.setProdajnaVrednost(85230.00);
        fi6.setRabatProcenat(0.00);
        fi6.setPorezProcenat(20.00);
        fi6.setValuta("USD");
        fi6.setKomentar("inostranstvo");
        fi6.setTipFakture(TipFakture.IZLAZNA_FAKTURA);
        fi6.setTipDokumenta(TipDokumenta.FAKTURA);
        fi6.setPreduzeceId(p4.getPreduzeceId());
        fi6.setKurs(117.00);
        fi6.setNaplata(0.00);

        this.fakturaRepository.save(fu1);
        this.fakturaRepository.save(fu2);
        this.fakturaRepository.save(fu3);
        this.fakturaRepository.save(fu4);
        this.fakturaRepository.save(fu5);
        this.fakturaRepository.save(fu6);
        this.fakturaRepository.save(fu7);
        this.fakturaRepository.save(fu8);
        this.fakturaRepository.save(fu9);
        this.fakturaRepository.save(fi1);
        this.fakturaRepository.save(fi2);
        this.fakturaRepository.save(fi3);
        this.fakturaRepository.save(fi4);
        this.fakturaRepository.save(fi5);
        this.fakturaRepository.save(fi6);

        SifraTransakcije st1 = new SifraTransakcije();
        st1.setSifra(124L);
        st1.setNazivTransakcije("Investicije-ostalo");
        SifraTransakcije st2 = new SifraTransakcije();
        st2.setSifra(288L);
        st2.setNazivTransakcije("Donacije");
        SifraTransakcije st3 = new SifraTransakcije();
        st3.setSifra(363L);
        st3.setNazivTransakcije("Ostali transferi");
        SifraTransakcije st4 = new SifraTransakcije();
        st4.setSifra(163L);
        st4.setNazivTransakcije("Ostali transferi");

        this.sifraTransakcijeRepository.save(st1);
        this.sifraTransakcijeRepository.save(st2);
        this.sifraTransakcijeRepository.save(st3);
        this.sifraTransakcijeRepository.save(st4);

        Transakcija t1 = new Transakcija();
        t1.setBrojTransakcije("T11/23");
        t1.setPreduzeceId(p3.getPreduzeceId());
        t1.setDatumTransakcije(new Date(2021, Calendar.MAY, 22));
        t1.setTipTransakcije(TipTransakcije.ISPLATA);
        t1.setIznos(11700.00);
        t1.setSifraTransakcije(st1);
        t1.setBrojDokumenta(t1.getBrojTransakcije());
        t1.setTipDokumenta(TipDokumenta.TRANSAKCIJA);

        Transakcija t2 = new Transakcija();
        t2.setBrojTransakcije("T11/24");
        t2.setPreduzeceId(p2.getPreduzeceId());
        t2.setDatumTransakcije(new Date(2021, Calendar.MAY, 23));
        t2.setTipTransakcije(TipTransakcije.ISPLATA);
        t2.setIznos(11700.00);
        t2.setSifraTransakcije(st1);
        t2.setKomentar("inostranstvo");
        t2.setBrojDokumenta(t2.getBrojTransakcije());
        t2.setTipDokumenta(TipDokumenta.TRANSAKCIJA);

        Transakcija t3 = new Transakcija();
        t3.setBrojTransakcije("T11/25");
        t3.setDatumTransakcije(new Date(2021, Calendar.MAY, 27));
        t3.setTipTransakcije(TipTransakcije.UPLATA);
        t3.setIznos(40000.00);
        t3.setSadrzaj("Donacija Pavle Marković");
        t3.setSifraTransakcije(st2);
        t3.setBrojDokumenta(t3.getBrojTransakcije());
        t3.setTipDokumenta(TipDokumenta.TRANSAKCIJA);

        Transakcija t4 = new Transakcija();
        t4.setBrojTransakcije("T23/33");
        t4.setPreduzeceId(p3.getPreduzeceId());
        t4.setDatumTransakcije(new Date(2021, Calendar.MAY, 27));
        t4.setTipTransakcije(TipTransakcije.ISPLATA);
        t4.setIznos(12000.00);
        t4.setSifraTransakcije(st3);
        t4.setBrojDokumenta(t4.getBrojTransakcije());
        t4.setTipDokumenta(TipDokumenta.TRANSAKCIJA);

        Transakcija t5 = new Transakcija();
        t5.setBrojTransakcije("T11OST");
        t5.setPreduzeceId(p3.getPreduzeceId());
        t5.setDatumTransakcije(new Date(2021, Calendar.MAY, 29));
        t5.setTipTransakcije(TipTransakcije.ISPLATA);
        t5.setIznos(40000.00);
        t5.setSifraTransakcije(st4);
        t5.setBrojDokumenta(t5.getBrojTransakcije());
        t5.setTipDokumenta(TipDokumenta.TRANSAKCIJA);

        this.transakcijaRepository.save(t1);
        this.transakcijaRepository.save(t2);
        this.transakcijaRepository.save(t3);
        this.transakcijaRepository.save(t4);
        this.transakcijaRepository.save(t5);

        Faktura mpf1 = new Faktura();
        mpf1.setBrojFakture("MP12/21");
        mpf1.setBrojDokumenta(mpf1.getBrojFakture());
        mpf1.setDatumIzdavanja(new Date(2021, Calendar.APRIL, 5));
        mpf1.setRokZaPlacanje(new Date(2021, Calendar.APRIL, 5));
        mpf1.setDatumPlacanja(new Date(2021, Calendar.APRIL, 5));
        mpf1.setProdajnaVrednost(5300.00);
        mpf1.setRabatProcenat(0.00);
        mpf1.setPorezProcenat(20.00);
        mpf1.setValuta("DIN");
        mpf1.setTipFakture(TipFakture.MALOPRODAJNA_FAKTURA);
        mpf1.setTipDokumenta(TipDokumenta.FAKTURA);
        mpf1.setKurs(117.00);
        mpf1.setNaplata(0.00);

        Faktura mpf2 = new Faktura();
        mpf2.setBrojFakture("MP12/22");
        mpf2.setBrojDokumenta(mpf2.getBrojFakture());
        mpf2.setDatumIzdavanja(new Date(2021, Calendar.APRIL, 5));
        mpf2.setRokZaPlacanje(new Date(2021, Calendar.APRIL, 5));
        mpf2.setDatumPlacanja(new Date(2021, Calendar.APRIL, 5));
        mpf2.setProdajnaVrednost(11700.00);
        mpf2.setRabatProcenat(0.00);
        mpf2.setPorezProcenat(20.00);
        mpf2.setValuta("DIN");
        mpf2.setTipFakture(TipFakture.MALOPRODAJNA_FAKTURA);
        mpf2.setTipDokumenta(TipDokumenta.FAKTURA);
        mpf2.setKurs(117.00);
        mpf2.setNaplata(0.00);

        Faktura mpf3 = new Faktura();
        mpf3.setBrojFakture("MP12/23");
        mpf3.setBrojDokumenta(mpf3.getBrojFakture());
        mpf3.setDatumIzdavanja(new Date(2021, Calendar.MAY, 7));
        mpf3.setRokZaPlacanje(new Date(2021, Calendar.MAY, 7));
        mpf3.setDatumPlacanja(new Date(2021, Calendar.MAY, 7));
        mpf3.setProdajnaVrednost(24500.00);
        mpf3.setRabatProcenat(0.00);
        mpf3.setPorezProcenat(20.00);
        mpf3.setValuta("DIN");
        mpf3.setTipFakture(TipFakture.MALOPRODAJNA_FAKTURA);
        mpf3.setTipDokumenta(TipDokumenta.FAKTURA);
        mpf3.setKurs(117.00);
        mpf3.setNaplata(0.00);

        Faktura mpf4 = new Faktura();
        mpf4.setBrojFakture("MP17/21");
        mpf4.setBrojDokumenta(mpf4.getBrojFakture());
        mpf4.setDatumIzdavanja(new Date(2021, Calendar.MAY, 7));
        mpf4.setRokZaPlacanje(new Date(2021, Calendar.MAY, 7));
        mpf4.setDatumPlacanja(new Date(2021, Calendar.MAY, 7));
        mpf4.setProdajnaVrednost(9200.00);
        mpf4.setRabatProcenat(0.00);
        mpf4.setPorezProcenat(20.00);
        mpf4.setValuta("DIN");
        mpf4.setTipFakture(TipFakture.MALOPRODAJNA_FAKTURA);
        mpf4.setTipDokumenta(TipDokumenta.FAKTURA);
        mpf4.setKurs(117.00);
        mpf4.setNaplata(0.00);

        Faktura mpf5 = new Faktura();
        mpf5.setBrojFakture("MP18/22");
        mpf5.setBrojDokumenta(mpf5.getBrojFakture());
        mpf5.setDatumIzdavanja(new Date(2021, Calendar.JUNE, 12));
        mpf5.setRokZaPlacanje(new Date(2021, Calendar.JUNE, 12));
        mpf5.setDatumPlacanja(new Date(2021, Calendar.JUNE, 12));
        mpf5.setProdajnaVrednost(8350.00);
        mpf5.setRabatProcenat(0.00);
        mpf5.setPorezProcenat(20.00);
        mpf5.setValuta("DIN");
        mpf5.setTipFakture(TipFakture.MALOPRODAJNA_FAKTURA);
        mpf5.setTipDokumenta(TipDokumenta.FAKTURA);
        mpf5.setKurs(117.00);
        mpf5.setNaplata(0.00);

        this.fakturaRepository.save(mpf1);
        this.fakturaRepository.save(mpf2);
        this.fakturaRepository.save(mpf3);
        this.fakturaRepository.save(mpf4);
        this.fakturaRepository.save(mpf5);

        Povracaj pov1 = new Povracaj();
        pov1.setBrojPovracaja("P01");
        pov1.setDatumPovracaja(new Date(2021, Calendar.APRIL, 5));
        pov1.setProdajnaVrednost(5300.00);

        Povracaj pov2 = new Povracaj();
        pov2.setBrojPovracaja("P02");
        pov2.setDatumPovracaja(new Date(2021, Calendar.MAY, 7));
        pov2.setProdajnaVrednost(11700.00);

        Povracaj pov3 = new Povracaj();
        pov3.setBrojPovracaja("P03");
        pov3.setDatumPovracaja(new Date(2021, Calendar.MAY, 7));
        pov3.setProdajnaVrednost(24500.00);

        Povracaj pov4 = new Povracaj();
        pov4.setBrojPovracaja("P04");
        pov4.setDatumPovracaja(new Date(2021, Calendar.JUNE, 12));
        pov4.setProdajnaVrednost(2000.00);

        this.povracajRepository.save(pov1);
        this.povracajRepository.save(pov2);
        this.povracajRepository.save(pov3);
        this.povracajRepository.save(pov4);

        TroskovniCentar tc1 = new TroskovniCentar();
        tc1.setSifra("MAG-BG");
        tc1.setNaziv("MAGACINI");
        tc1.setLokacijaId(11L);
        tc1.setOdgovornoLiceId(1L);
        tc1.setUkupniTrosak(0.00);

        TroskovniCentar tc2 = new TroskovniCentar();
        tc2.setSifra("MAG-NBG");
        tc2.setNaziv("MAGACIN - NOVI BEOGRAD");
        tc2.setParentTroskovniCentar(tc1);
        tc2.setLokacijaId(11L);
        tc2.setOdgovornoLiceId(1L);
        tc2.setUkupniTrosak(0.00);

        TroskovniCentar tc3 = new TroskovniCentar();
        tc3.setSifra("MAG-NBG-DC");
        tc3.setNaziv("MAGACIN - NOVI BEOGRAD, DELTA CITY");
        tc3.setParentTroskovniCentar(tc2);
        tc3.setLokacijaId(11L);
        tc3.setOdgovornoLiceId(1L);
        tc3.setUkupniTrosak(0.00);

        TroskovniCentar tc4 = new TroskovniCentar();
        tc4.setSifra("MAG-SV");
        tc4.setNaziv("MAGACIN - SAVSKI VENAC");
        tc4.setParentTroskovniCentar(tc1);
        tc4.setLokacijaId(11L);
        tc4.setOdgovornoLiceId(1L);
        tc4.setUkupniTrosak(0.00);

        TroskovniCentar tc5 = new TroskovniCentar();
        tc5.setSifra("MAG-Z");
        tc5.setNaziv("MAGACIN - ZEMUN");
        tc5.setParentTroskovniCentar(tc1);
        tc5.setLokacijaId(11L);
        tc5.setOdgovornoLiceId(1L);
        tc5.setUkupniTrosak(0.00);

        this.troskovniCentarRepository.save(tc1);
        this.troskovniCentarRepository.save(tc2);
        this.troskovniCentarRepository.save(tc3);
        this.troskovniCentarRepository.save(tc4);
        this.troskovniCentarRepository.save(tc5);

        ProfitniCentar pc1 = new ProfitniCentar();
        pc1.setSifra("MP-BG");
        pc1.setNaziv("MALOPRODAJE");
        pc1.setLokacijaId(11L);
        pc1.setOdgovornoLiceId(1L);
        pc1.setUkupniTrosak(0.00);

        ProfitniCentar pc2 = new ProfitniCentar();
        pc2.setSifra("MP-NBG");
        pc2.setNaziv("MALOPRODAJA - NOVI BEOGRAD");
        pc2.setParentProfitniCentar(pc1);
        pc2.setLokacijaId(11L);
        pc2.setOdgovornoLiceId(1L);
        pc2.setUkupniTrosak(0.00);

        ProfitniCentar pc3 = new ProfitniCentar();
        pc3.setSifra("MP-NBG");
        pc3.setNaziv("MALOPRODAJA - NBG - DELTA CITY");
        pc3.setParentProfitniCentar(pc2);
        pc3.setLokacijaId(11L);
        pc3.setOdgovornoLiceId(1L);
        pc3.setUkupniTrosak(0.00);

        this.profitniCentarRepository.save(pc1);
        this.profitniCentarRepository.save(pc2);
        this.profitniCentarRepository.save(pc3);

        log.info("Data loaded!");
    }
}