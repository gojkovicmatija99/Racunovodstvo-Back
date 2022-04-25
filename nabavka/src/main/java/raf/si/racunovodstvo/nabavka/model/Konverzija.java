package raf.si.racunovodstvo.nabavka.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
@Getter
@Setter
public class Konverzija extends BaznaKonverzijaKalkulacija {

    @OneToMany
    @JoinColumn(name = "konverzija")
    private List<KonverzijaArtikal> artikli;
}
