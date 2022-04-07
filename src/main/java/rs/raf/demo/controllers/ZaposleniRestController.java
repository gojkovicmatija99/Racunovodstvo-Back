package rs.raf.demo.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.raf.demo.model.Staz;
import rs.raf.demo.model.Zaposleni;
import rs.raf.demo.model.enums.StatusZaposlenog;
import rs.raf.demo.services.IStazService;
import rs.raf.demo.services.IZaposleniService;
import rs.raf.demo.specifications.RacunSpecificationsBuilder;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@CrossOrigin
@RestController
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/api/zaposleni")
public class ZaposleniRestController {

    private final IZaposleniService iZaposleniService;
    private final IStazService iStazService;

    public ZaposleniRestController(IZaposleniService iZaposleniService, IStazService iStazService) {
        this.iZaposleniService = iZaposleniService;
        this.iStazService = iStazService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createZaposleni(@Valid @RequestBody Zaposleni zaposleni) {
        return ResponseEntity.ok(iZaposleniService.save(zaposleni));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateZaposleni(@Valid @RequestBody Zaposleni zaposleni,  @PathVariable Long id) {
        if (iZaposleniService.findById(id).isPresent())
            return ResponseEntity.ok(iZaposleniService.save(zaposleni));
        throw new EntityNotFoundException();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteZaposleni(@PathVariable("id") Long id) {
        Optional<Zaposleni> optionalZaposleni = iZaposleniService.findById(id);
        Zaposleni newZaposleni = new Zaposleni();
        if (optionalZaposleni.isPresent()) {
            optionalZaposleni.get().setStatusZaposlenog(StatusZaposlenog.NEZAPOSLEN);
            for(Staz staz : optionalZaposleni.get().getStaz()){
                if(staz.getKrajRada() == null){
                    staz.setKrajRada(new Date());
                    iStazService.save(staz);
                }
            }
            return ResponseEntity.ok(iZaposleniService.save(optionalZaposleni.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getZaposleniId(@PathVariable("id") Long id) {
        Optional<Zaposleni> optionalZaposleni = iZaposleniService.findById(id);
        if (optionalZaposleni.isPresent()) {
            return ResponseEntity.ok(iZaposleniService.findById(id));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> search(@RequestParam(name = "search") String search) {
        RacunSpecificationsBuilder<Zaposleni> builder = new RacunSpecificationsBuilder<>();

        Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
        Matcher matcher = pattern.matcher(search + ",");
        while (matcher.find()) {
            builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
        }

        Specification<Zaposleni> spec = builder.build();

        List<Zaposleni> result = iZaposleniService.findAll(spec);

        if (result.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(result);
    }
}
