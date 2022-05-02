package raf.si.racunovodstvo.nabavka.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
public class KalkulacijaArtikal extends KonverzijaArtikal {

    private Double marzaProcenat;
    @Column(nullable = false)
    @NotNull(message = "Marza je obavezna")
    private Double marza;
    @Column(nullable = false)
    @NotNull(message = "Osnovica za prodaju je obavezna")
    private Double osnovicaZaProdaju;
    @Column(nullable = false)
    @NotNull(message = "Procenat poreza je obavezan")
    private Double porezProcenat;
    @Column(nullable = false)
    private Double porez;
    @Column(nullable = false)
    @NotNull(message = "Prodajna cena je obavezna")
    private Double prodajnaCena;
    @Column(nullable = false)
    @NotNull(message = "Osnovica je obavezna")
    private Double osnovica;
    @Column(nullable = false)
    @NotNull(message = "Ukupna prodajna cena je obavezna")
    private Double ukupnaProdajnaCena;
}
