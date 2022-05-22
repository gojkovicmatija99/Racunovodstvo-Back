package raf.si.racunovodstvo.nabavka.auditor;

import raf.si.racunovodstvo.nabavka.model.BaznaKonverzijaKalkulacija;

import javax.persistence.PrePersist;

public class BaznaKonverzijaKalkulacijaAuditor {

    @PrePersist
    private void beforeSave(BaznaKonverzijaKalkulacija baznaKonverzijaKalkulacija) {
        baznaKonverzijaKalkulacija.getTroskoviNabavke()
                                  .forEach(troskoviNabavke -> troskoviNabavke.setBaznaKonverzijaKalkulacija(baznaKonverzijaKalkulacija));
    }
}
