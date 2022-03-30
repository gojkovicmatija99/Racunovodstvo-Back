package rs.raf.demo.model;

import lombok.Getter;
import lombok.Setter;
import rs.raf.demo.model.enums.StatusZaposlenog;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
@Getter
@Setter
public class Zaposleni {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long zaposleniId;
    @Column(nullable = false)
    @NotBlank(message = "Ime je obavezno")
    private String ime;
    @Column(nullable = false)
    @NotBlank(message = "Prezime je obavezno")
    private String prezime;
    @Column
    private String imeRoditelja;
    @Column(nullable = false)
    private Date pocetakRadnogOdnosa;
    @Column(nullable = false)
    @NotBlank(message = "JMBG je obavezan")
    private String jmbg;
    @Column(nullable = false)
    @NotBlank(message = "Pol je obavezno")
    private String pol;
    @Column(nullable = false)
    private Date datumRodjenja;
    @Column
    private String adresa;
    @Column
    private String grad;
    @Column
    private Long brojRacuna;
    @Column
    private String stepenObrazovanja;
    @Column
    private Long brojRadneKnjizice;
    @Column
    private Date radniStaz;
    @Column
    @Enumerated(EnumType.STRING)
    private StatusZaposlenog statusZaposlenog;
    @Column
    private String komentar;
    @ManyToOne
    @JoinColumn(name = "preduzeceId")
    private Preduzece preduzece;

}
