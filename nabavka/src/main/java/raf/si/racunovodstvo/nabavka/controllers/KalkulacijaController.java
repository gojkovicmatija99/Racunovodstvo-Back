package raf.si.racunovodstvo.nabavka.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import raf.si.racunovodstvo.nabavka.model.Kalkulacija;
import raf.si.racunovodstvo.nabavka.services.IKalkulacijaService;
import raf.si.racunovodstvo.nabavka.utils.ApiUtil;
import raf.si.racunovodstvo.nabavka.utils.SearchUtil;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Optional;

@CrossOrigin
@RestController
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/api/kalkulacije")
public class KalkulacijaController {

    private final IKalkulacijaService kalkulacijaService;

    private final SearchUtil<Kalkulacija> searchUtil;

    public KalkulacijaController(IKalkulacijaService kalkulacijaService) {
        this.kalkulacijaService = kalkulacijaService;
        this.searchUtil = new SearchUtil<>();
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> searchKalkulacija(
            @RequestParam(name = "search", required = false, defaultValue = "") String search,
            @RequestParam(defaultValue = ApiUtil.DEFAULT_PAGE) @Min(ApiUtil.MIN_PAGE) Integer page,
            @RequestParam(defaultValue = ApiUtil.DEFAULT_SIZE) @Min(ApiUtil.MIN_SIZE) @Max(ApiUtil.MAX_SIZE) Integer size,
            @RequestParam(defaultValue = "brojKalkulacije") String[] sort
    ) {
        if (search.isEmpty()) {
            return ResponseEntity.ok(this.kalkulacijaService.findAll());
        }

        Pageable pageSort = ApiUtil.resolveSortingAndPagination(page, size, sort);
        Specification<Kalkulacija> spec = this.searchUtil.getSpec(search);
        return ResponseEntity.ok(this.kalkulacijaService.findAll(spec, pageSort));
    }

    @GetMapping(value = "/total", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getTotalKalkulacije(
            @RequestParam(name = "search", required = false, defaultValue = "") String search,
            @RequestParam(defaultValue = ApiUtil.DEFAULT_PAGE) @Min(ApiUtil.MIN_PAGE) Integer page,
            @RequestParam(defaultValue = ApiUtil.DEFAULT_SIZE) @Min(ApiUtil.MIN_SIZE) @Max(ApiUtil.MAX_SIZE) Integer size,
            @RequestParam(defaultValue = "brojKalkulacije") String[] sort) {

        if (search.isEmpty()) {
            return ResponseEntity.ok(kalkulacijaService.getTotalKalkulacije(kalkulacijaService.findAll()));
        }

        Pageable pageSort = ApiUtil.resolveSortingAndPagination(page, size, sort);
        Specification<Kalkulacija> spec = this.searchUtil.getSpec(search);
        return ResponseEntity.ok(this.kalkulacijaService.getTotalKalkulacije(kalkulacijaService.findAll(spec, pageSort).toList()));
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getKalkulacija() {
        return ResponseEntity.ok(kalkulacijaService.findAll());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createKalkulacija(@Valid @RequestBody Kalkulacija kalkulacija) {
        return ResponseEntity.ok(kalkulacijaService.save(kalkulacija));
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateFaktura(@Valid @RequestBody Kalkulacija kalkulacija) {
        Optional<Kalkulacija> optionalKalkulacija = kalkulacijaService.findById(kalkulacija.getId());
        if(optionalKalkulacija.isPresent()) {
            return ResponseEntity.ok(kalkulacijaService.save(kalkulacija));
        }
        throw new EntityNotFoundException();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteFaktura(@PathVariable("id") Long id){
        Optional<Kalkulacija> optionalKalkulacija = kalkulacijaService.findById(id);
        if (optionalKalkulacija.isPresent()){
            kalkulacijaService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        throw new EntityNotFoundException();
    }
}
