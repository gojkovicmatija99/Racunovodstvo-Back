package raf.si.racunovodstvo.nabavka.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import raf.si.racunovodstvo.nabavka.model.Kalkulacija;
import raf.si.racunovodstvo.nabavka.requests.KalkulacijaRequest;

import java.util.List;
import java.util.Map;

public interface IKalkulacijaService extends IService<Kalkulacija, Long> {
    Page<Kalkulacija> findAll(Pageable pageable);

    Page<Kalkulacija> findAll(Specification<Kalkulacija> spec, Pageable pageSort);
    Map<String, Number> getTotalKalkulacije(List<Kalkulacija> kalulacije);

    Kalkulacija increaseNabavnaAndProdajnaCena(Long kalkulacijaId, Double nabavnaCena, Double prodajnaCena);

    Kalkulacija save(KalkulacijaRequest kalkulacija);

    Kalkulacija update(KalkulacijaRequest kalkulacija);
}
