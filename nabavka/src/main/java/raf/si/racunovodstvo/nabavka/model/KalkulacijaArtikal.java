package raf.si.racunovodstvo.nabavka.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class KalkulacijaArtikal {

    @Id
    private String sifraArtikla;
    @Column(nullable = false)
    private String nazivArtikla;
    @Column(nullable = false)
    private String jedinicaMere;
    @Column(nullable = false)
    private Integer kolicina;
    @Column(nullable = false)
    private Double nabavnaCena;
    @Column(nullable = false)
    private Double rabatProcenat;
    @Column(nullable = false)
    private Double rabat;
    @Column(nullable = false)
    private Double nabavnaCenaPosleRabata;
    @Column(nullable = false)
    private Double ukupnaNabavnaCena;
    @Column(nullable = false)
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
