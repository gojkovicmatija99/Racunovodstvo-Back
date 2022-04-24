package raf.si.racunovodstvo.nabavka.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class TroskoviNabavke {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long troskoviNabavkeId;
    @Column(nullable = false)
    private String naziv;
    @Column(nullable = false)
    private Double cena;
}
