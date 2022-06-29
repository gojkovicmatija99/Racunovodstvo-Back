package raf.si.racunovodstvo.preduzece.services.impl;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import raf.si.racunovodstvo.preduzece.feign.TransakcijeFeignClient;
import raf.si.racunovodstvo.preduzece.model.Obracun;
import raf.si.racunovodstvo.preduzece.model.ObracunZaposleni;
import raf.si.racunovodstvo.preduzece.model.Transakcija;
import raf.si.racunovodstvo.preduzece.repositories.ObracunRepository;
import raf.si.racunovodstvo.preduzece.repositories.ObracunZaposleniRepository;
import raf.si.racunovodstvo.preduzece.requests.ObracunTransakcijeRequest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class ObracunServiceTest {

    @InjectMocks
    private ObracunService obracunService;
    @Mock
    private ObracunRepository obracunRepository;
    @Mock
    private TransakcijeFeignClient transakcijeFeignClient;


    private static final Long MOCK_ID = 1L;
    private static final String MOCK_NAZIV = "Naziv";
    private static final String MOCK_TOKEN = "token";

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void save(){
        Obracun obracun = new Obracun();
        given(obracunRepository.save(obracun)).willReturn(obracun);

        assertEquals(obracun,obracunService.save(obracun));
    }

    @Test
    void findById(){
        Obracun obracun = new Obracun();
        given(obracunRepository.findById(MOCK_ID)).willReturn(Optional.of(obracun));

        assertEquals(obracun,obracunService.findById(MOCK_ID).get());
    }

    @Test
    void findAll(){
        List<Obracun> obracunList = new ArrayList<>();
        given(obracunRepository.findAll()).willReturn(obracunList);

        assertEquals(obracunList, obracunService.findAll());
    }

    @Test
    void deleteById(){
        obracunService.deleteById(MOCK_ID);
        then(obracunRepository).should(times(1)).deleteById(MOCK_ID);
    }

    @Test
    void obradiObracunException(){
        Obracun obracun = new Obracun();
        given(obracunService.findById(MOCK_ID)).willReturn(Optional.of(obracun));
        assertThrows(RuntimeException.class, ()-> obracunService.obradiObracun(MOCK_ID,MOCK_TOKEN));
        then(obracunRepository).should(never()).save(any());
    }

    @Test
    void updateObracunZaradeNaziv(){
        Obracun obracun = new Obracun();
        String naziv = obracun.getNaziv();
        given(obracunRepository.findById(MOCK_ID)).willReturn(Optional.of(obracun));

        obracunService.updateObracunZaradeNaziv(MOCK_ID,MOCK_NAZIV);
        then(obracunRepository).should(times(1)).save(obracun);

        assertNotEquals(naziv,obracun.getNaziv());
    }
}