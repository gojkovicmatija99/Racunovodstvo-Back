package raf.si.racunovodstvo.nabavka.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import raf.si.racunovodstvo.nabavka.model.Kalkulacija;
import raf.si.racunovodstvo.nabavka.requests.KalkulacijaRequest;
import raf.si.racunovodstvo.nabavka.services.IKalkulacijaService;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class KalkulacijaControllerTest {
    @InjectMocks
    private KalkulacijaController kalkulacijaController;

    @Mock
    private IKalkulacijaService kalkulacijaService;

    @Test
    void findAllTest() {
        Page<Kalkulacija> kalkulacijaResponsePage = new PageImpl<>(new ArrayList<>());
        given(kalkulacijaService.findAll(any())).willReturn(kalkulacijaResponsePage);

        ResponseEntity<Page<Kalkulacija>> response = kalkulacijaController.getKalkulacija(0, 1, new String[]{});

        assertEquals(kalkulacijaResponsePage, response.getBody());
    }

    @Test
    void findAllSizeOutOfBoundsTest() {
        assertThrows(IllegalArgumentException.class, () -> kalkulacijaController.getKalkulacija(0, 0, new String[]{}));
    }

    @Test
    void findAllPageOutOfBoundsTest() {
        assertThrows(IllegalArgumentException.class, () -> kalkulacijaController.getKalkulacija(-1, 1, new String[]{}));
    }

    @Test
    void deleteTest() {
        ResponseEntity<String> response = kalkulacijaController.deleteKalkulacija(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void createTest() {
        KalkulacijaRequest artikalRequest = new KalkulacijaRequest();
        Kalkulacija expectedResponse = new Kalkulacija();
        given(kalkulacijaService.save(artikalRequest)).willReturn(expectedResponse);

        Kalkulacija actualResponse = kalkulacijaController.createKalkulacija(artikalRequest).getBody();

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void updateTest() {
        KalkulacijaRequest artikalRequest = new KalkulacijaRequest();
        Kalkulacija expectedResponse = new Kalkulacija();
        given(kalkulacijaService.update(artikalRequest)).willReturn(expectedResponse);

        Kalkulacija actualResponse = kalkulacijaController.updateKalkulacija(artikalRequest).getBody();

        assertEquals(expectedResponse, actualResponse);
    }
}
