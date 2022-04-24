package raf.si.racunovodstvo.nabavka.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@Getter
@Setter
public class ProdajniArtikal extends Artikal{

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
