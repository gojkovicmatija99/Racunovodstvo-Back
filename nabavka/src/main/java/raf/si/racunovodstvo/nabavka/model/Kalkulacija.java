package raf.si.racunovodstvo.nabavka.model;

import lombok.Getter;
import lombok.Setter;
import raf.si.racunovodstvo.nabavka.model.enums.TipKalkulacije;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
@Getter
@Setter
public class Kalkulacija extends BaznaKonverzijaKalkulacija {

    @Column(nullable = false)
    private TipKalkulacije tipKalkulacije;
    @Column(nullable = false)
    private Double prodajnaCena;
    @OneToMany
    @JoinColumn(name = "kalkulacija")
    private List<KalkulacijaArtikal> artikli;
}
