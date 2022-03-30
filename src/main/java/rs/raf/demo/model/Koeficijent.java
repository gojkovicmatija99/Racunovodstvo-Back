package rs.raf.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
public class Koeficijent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long koeficijentId;
    @Column
    private Double pio1;
    @Column
    private Double pio2;
    @Column
    private Double zo1;
    @Column
    private Double zo2;
    @Column
    private Double nez1;
    @Column
    private Double nez2;
    @Column
    private boolean status;
    @Column
    private Date date;

}
