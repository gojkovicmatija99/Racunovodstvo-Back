package raf.si.racunovodstvo.nabavka.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import raf.si.racunovodstvo.nabavka.model.TroskoviNabavke;
import raf.si.racunovodstvo.nabavka.services.TroskoviNabavkeService;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.Optional;

@CrossOrigin
@RestController
@SecurityRequirement(name = "bearerAuth")
@Validated
@RequestMapping("/api/troskovi")
public class TroskoviNabavkeController {

    private final TroskoviNabavkeService troskoviNabavkeService;

    public TroskoviNabavkeController(TroskoviNabavkeService troskoviNabavkeService){
        this.troskoviNabavkeService = troskoviNabavkeService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createTroskoviNabavke(@Valid @RequestBody TroskoviNabavke troskoviNabavke) {
        return ResponseEntity.ok(troskoviNabavkeService.save(troskoviNabavke));
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateTroskoviNabavke(@Valid @RequestBody TroskoviNabavke troskoviNabavke) {
        if (troskoviNabavkeService.findById(troskoviNabavke.getTroskoviNabavkeId()).isPresent())
            return ResponseEntity.ok(troskoviNabavkeService.save(troskoviNabavke));
        throw new EntityNotFoundException();
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getTroskoviNabavke() {
        return ResponseEntity.ok(troskoviNabavkeService.findAll());
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteTroskoviNabavke(@PathVariable Long id) {
        Optional<TroskoviNabavke> optionalTroskoviNabavke = troskoviNabavkeService.findById(id);
        if (optionalTroskoviNabavke.isPresent()) {
            troskoviNabavkeService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        throw new EntityNotFoundException();
    }
}
