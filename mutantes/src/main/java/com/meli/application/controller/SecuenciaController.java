package com.meli.application.controller;

import com.meli.domain.model.secuenciaadn.SecuenciaAdn;
import com.meli.domain.port.incoming.ValidadorSecuenciaAdn;
import com.meli.domain.port.outgoing.RespuestaStats;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class SecuenciaController {

    private final ValidadorSecuenciaAdn validadorSecuenciaAdnUseCase;


    public SecuenciaController(ValidadorSecuenciaAdn validadorSecuenciaAdnUseCase) {
        this.validadorSecuenciaAdnUseCase = validadorSecuenciaAdnUseCase;
    }

    @GetMapping("/stats")
    public RespuestaStats stats() {
        return validadorSecuenciaAdnUseCase.cuenta();

    }

    @PostMapping("/mutant")
    public ResponseEntity isMutant(@RequestBody final SecuenciaAdn dna) {
        try {
            if (validadorSecuenciaAdnUseCase.isMutant(dna.getDna()))
                return ResponseEntity.status(HttpStatus.OK).body("200");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("403");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("500");
        }
    }
}
