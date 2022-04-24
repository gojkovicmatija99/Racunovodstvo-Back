package raf.si.racunovodstvo.nabavka.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
@Getter
@Setter
public class Konverzija {

    @Id
    private String brojKonverzije;
    @Column(nullable = false)
    private Date datum;
    @Column(nullable = false)
    private Long dobavljacId;
    @ManyToOne
    private Lokacija lokacija;
    @OneToMany
    @JoinColumn(name = "konverzijaKalkulacija")
    private List<TroskoviNabavke> troskoviNabavke;
    @Column(nullable = false)
    private Double fakturnaCena;
    @Column(nullable = false)
    private Double nabavnaCena;
    @Column(nullable = false)
    private String valuta;
    @OneToMany
    @JoinColumn(name = "konverzija")
    private List<KonverzijaArtikal> artikli;
    @Column
    private String komentar;
}
