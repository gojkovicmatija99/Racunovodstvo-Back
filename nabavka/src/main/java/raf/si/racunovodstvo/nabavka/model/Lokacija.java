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
public class Lokacija {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lokacijaId;
    @Column(nullable = false)
    private String naziv;
    @Column(nullable = false)
    private String adresa;
}
