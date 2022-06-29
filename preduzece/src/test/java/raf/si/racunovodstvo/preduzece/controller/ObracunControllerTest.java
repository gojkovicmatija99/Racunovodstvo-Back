package raf.si.racunovodstvo.preduzece.controller;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import raf.si.racunovodstvo.preduzece.requests.ObracunZaradeRequest;
import raf.si.racunovodstvo.preduzece.services.impl.ObracunService;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ObracunControllerTest {

    @InjectMocks
    private ObracunController obracunController;

    @Mock
    private ObracunService obracunService;

    private static final Long MOCK_ID = 1l;
    private static final String MOCK_TOKEN = "Token";

    @Test
    void getAllObracunWithZaposleni(){
        ResponseEntity<?> responseEntity = obracunController.getAllObracunWithZaposleni();
        assertEquals(200, responseEntity.getStatusCodeValue());
    }

    @Test
    void setObracunZaradeNaziv(){
        ObracunZaradeRequest obracunZaradeRequest = new ObracunZaradeRequest();
        ResponseEntity<?> responseEntity = obracunController.setObracunZaradeNaziv(obracunZaradeRequest);
        assertEquals(200, responseEntity.getStatusCodeValue());
    }
    @Test
    void obradiObracun(){
        ResponseEntity<?> responseEntity = obracunController.obradiObracun(MOCK_ID,MOCK_TOKEN);
        assertEquals(200, responseEntity.getStatusCodeValue());
    }
}