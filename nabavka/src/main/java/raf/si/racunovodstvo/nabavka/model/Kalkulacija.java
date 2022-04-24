package raf.si.racunovodstvo.nabavka.model;

import lombok.Getter;
import lombok.Setter;
import raf.si.racunovodstvo.nabavka.model.enums.TipKalkulacije;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
@Getter
@Setter
public class Kalkulacija{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String brojKalkulacije;
    @Column(nullable = false)
    private TipKalkulacije tipKalkulacije;
    @Column(nullable = false)
    private Date datum;
    @Column(nullable = false)
    private Long dobavljacId;
    @ManyToOne
    private Lokacija lokacija;
    @OneToMany
    @JoinColumn(name = "troskoviNabavkeId")
    private List<TroskoviNabavke> troskoviNabavke;
    @Column(nullable = false)
    private Double fakturnaCena;
    @Column(nullable = false)
    private Double nabavnaCena;
    @Column(nullable = false)
    private String valuta;
    @Column(nullable = false)
    private Double prodajnaCena;
    @OneToMany
    @JoinColumn(name = "sifraArtikla")
    private List<ProdajniArtikal> prodajniArtikali;
    @Column
    private String komentar;
}
