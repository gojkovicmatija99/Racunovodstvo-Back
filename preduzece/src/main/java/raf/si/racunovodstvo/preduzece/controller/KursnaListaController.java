package raf.si.racunovodstvo.preduzece.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import raf.si.racunovodstvo.preduzece.responses.KursnaListaResponse;
import raf.si.racunovodstvo.preduzece.services.IKursnaListaService;

@CrossOrigin
@RestController
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/api/kursna_lista")
public class KursnaListaController {

    private final IKursnaListaService kursnaListaService;

    public KursnaListaController(IKursnaListaService kursnaListaService) {
        this.kursnaListaService = kursnaListaService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<KursnaListaResponse> getKursnaLista(@RequestParam(required = false) String date) {
        KursnaListaResponse response =
            date == null || date.isBlank() ? kursnaListaService.getKursnaLista() : kursnaListaService.getKursnaListaForDay(date);
        return ResponseEntity.ok(response);
    }
}
