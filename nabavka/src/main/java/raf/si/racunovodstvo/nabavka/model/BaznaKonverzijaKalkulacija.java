package raf.si.racunovodstvo.nabavka.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
public class BaznaKonverzijaKalkulacija {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    @Column(nullable = false)
    @NotNull(message = "Datum je obavezan")
    private Date datum;
    @Column(nullable = false)
    private Long dobavljacId;
    @ManyToOne(optional = true)
    private Lokacija lokacija;
    @OneToMany(fetch = FetchType.EAGER)
    @Cascade(CascadeType.ALL)
    private List<TroskoviNabavke> troskoviNabavke;
    @Column(nullable = false)
    private Double fakturnaCena;
    @Column(nullable = false)
    private Double nabavnaCena;
    @Column(nullable = false)
    @NotNull(message = "Valuta je obavezna")
    private String valuta;
    @Column
    private String komentar;
}
