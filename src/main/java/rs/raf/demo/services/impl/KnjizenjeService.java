package rs.raf.demo.services.impl;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import rs.raf.demo.model.Knjizenje;
import rs.raf.demo.model.enums.TipDokumenta;
import rs.raf.demo.repositories.KnjizenjeRepository;
import rs.raf.demo.services.IKnjizenjeService;
import rs.raf.demo.services.IService;

import java.util.List;
import java.util.Optional;

@Service
public class KnjizenjeService implements IKnjizenjeService {

    private final KnjizenjeRepository knjizenjeRepository;

    public KnjizenjeService(KnjizenjeRepository knjizenjeRepository) {
        this.knjizenjeRepository = knjizenjeRepository;
    }
    
    @Override
    public <S extends Knjizenje> S save(S var1) {
        return null;
    }

    @Override
    public Optional<Knjizenje> findById(Long var1) {
        return Optional.empty();
    }

    @Override
    public List<Knjizenje> findAll() {
        return null;
    }

    @Override
    public void deleteById(Long var1) {

    }
    @Override
    public List<Knjizenje> findAll(Specification<Knjizenje> spec) {
        return null;
    }
    @Override
    public List<Knjizenje> findByTipDokumenta(TipDokumenta tipDokumenta) {
        return null;
    }
}
