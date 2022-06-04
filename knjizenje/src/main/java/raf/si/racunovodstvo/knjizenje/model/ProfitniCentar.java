package raf.si.racunovodstvo.knjizenje.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
@Getter
@Setter
public class ProfitniCentar extends BazniCentar {

    @ManyToOne
    @JoinColumn(name = "parentId", nullable = false)
    private ProfitniCentar parentProfitniCentar;
    @JsonIgnore
    @OneToMany(mappedBy = "parentProfitniCentar")
    private List<ProfitniCentar> profitniCentarList;
}