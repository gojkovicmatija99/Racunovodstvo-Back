package rs.raf.demo.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.raf.demo.model.Knjizenje;
import rs.raf.demo.services.IService;

@CrossOrigin
@RestController
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/api/knjizenje")
public class KnjizenjeController {

    private final IService<Knjizenje, Long> knjizenjeService;

    public KnjizenjeController(IService<Knjizenje, Long> knjizenjeService) {
        this.knjizenjeService = knjizenjeService;
    }
}
