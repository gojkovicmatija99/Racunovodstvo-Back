package raf.si.racunovodstvo.nabavka.requests;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import raf.si.racunovodstvo.nabavka.model.KalkulacijaArtikal;
import raf.si.racunovodstvo.nabavka.model.Lokacija;
import raf.si.racunovodstvo.nabavka.model.TroskoviNabavke;
import raf.si.racunovodstvo.nabavka.model.enums.TipKalkulacije;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@Getter
@Setter
public class KalkulacijaRequest {
    private Long id;
    @NotNull(message = "Datum je obavezan")
    private Date datum;
    private Long dobavljacId;
    private Lokacija lokacija;
    private List<TroskoviNabavke> troskoviNabavke;
    @NotNull(message = "Valuta je obavezna")
    private String valuta;
    private String komentar;
    @NotNull(message = "Broj kalkulacije je obavezan")
    private String brojKalkulacije;
    @NotNull(message = "Tip kalkulacije je obavezan")
    private TipKalkulacije tipKalkulacije;
    private List<KalkulacijaArtikal> artikli;
}
