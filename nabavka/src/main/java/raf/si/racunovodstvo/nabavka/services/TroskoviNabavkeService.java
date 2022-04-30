package raf.si.racunovodstvo.nabavka.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import raf.si.racunovodstvo.nabavka.model.TroskoviNabavke;
import raf.si.racunovodstvo.nabavka.repositories.TroskoviNabavkeRepository;
import raf.si.racunovodstvo.nabavka.services.impl.IService;

import java.util.List;
import java.util.Optional;

@Service
public class TroskoviNabavkeService implements IService<TroskoviNabavke, Long> {

    private final TroskoviNabavkeRepository troskoviNabavkeRepository;

    @Autowired
    public TroskoviNabavkeService(TroskoviNabavkeRepository troskoviNabavkeRepository){
        this.troskoviNabavkeRepository = troskoviNabavkeRepository;
    }

    @Override
    public TroskoviNabavke save(TroskoviNabavke troskoviNabavke) {
        return troskoviNabavkeRepository.save(troskoviNabavke);
    }

    @Override
    public Optional<TroskoviNabavke> findById(Long id) {
        return troskoviNabavkeRepository.findByTroskoviNabavkeId(id);
    }

    @Override
    public List<TroskoviNabavke> findAll() {
        return troskoviNabavkeRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        troskoviNabavkeRepository.deleteById(id);
    }
}
