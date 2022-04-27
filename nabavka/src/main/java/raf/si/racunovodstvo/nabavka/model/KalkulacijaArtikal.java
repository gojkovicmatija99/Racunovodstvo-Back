package raf.si.racunovodstvo.nabavka.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class KalkulacijaArtikal extends KonverzijaArtikal {

    private Double marzaProcenat;
    @Column(nullable = false)
    private Double marza;
    @Column(nullable = false)
    private Double osnovicaZaProdaju;
    @Column(nullable = false)
    private Double porezProcenat;
    @Column(nullable = false)
    private Double porez;
    @Column(nullable = false)
    private Double prodajnaCena;
    @Column(nullable = false)
    private Double osnovica;
    @Column(nullable = false)
    private Double ukupnaProdajnaCena;
}
