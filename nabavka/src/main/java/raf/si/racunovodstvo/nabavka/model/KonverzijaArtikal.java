package raf.si.racunovodstvo.nabavka.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
public class KonverzijaArtikal {

    @Id
    @NotNull(message = "Sifra artikla je obavezna")
    private String sifraArtikla;
    @Column(nullable = false)
    @NotNull(message = "Naziv artikla je obavezan")
    private String nazivArtikla;
    @Column(nullable = false)
    @NotNull(message = "Jedinica mere je obavezna")
    private String jedinicaMere;
    @Column(nullable = false)
    @NotNull(message = "Kolicina je obavezna")
    private Integer kolicina;
    @Column(nullable = false)
    @NotNull(message = "Nabavna cena je obavezna")
    private Double nabavnaCena;
    @Column(nullable = false)
    @NotNull(message = "Procenat rabata je obavezan")
    private Double rabatProcenat;
    @Column(nullable = false)
    private Double rabat;
    @Column(nullable = false)
    private Double nabavnaCenaPosleRabata;
    @Column(nullable = false)
    private Double ukupnaNabavnaCena;
}
