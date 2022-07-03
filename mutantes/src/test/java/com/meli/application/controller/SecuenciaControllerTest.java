package com.meli.application.controller;

import com.meli.domain.model.secuenciaadn.SecuenciaAdn;
import com.meli.domain.port.incoming.ValidadorSecuenciaAdn;
import com.meli.domain.port.outgoing.RespuestaStats;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SecuenciaControllerTest {


    @InjectMocks
    SecuenciaController secuenciaController;
    @Mock
    private ValidadorSecuenciaAdn validadorSecuenciaAdnUseCase;
    RespuestaStats respuestaStats;
    private SecuenciaAdn dna;
    String[] secuencias;

    @BeforeEach
    void setUp() {
        respuestaStats = new RespuestaStats(4L,2L,2D);
        secuencias = new String[]{"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG´"};
        dna = new SecuenciaAdn();
        dna.setDna(secuencias);
    }


    @Test
    void stats()  {
        when(validadorSecuenciaAdnUseCase.cuenta()).thenReturn(respuestaStats);
        RespuestaStats respuesta = secuenciaController.stats();
        assertNotNull(respuesta);
        assertEquals(respuestaStats.getCount_human_dna(), respuesta.getCount_human_dna());
        assertEquals(respuestaStats.getCount_mutant_dna(), respuesta.getCount_mutant_dna());
        assertEquals(respuestaStats.getRatio(), respuesta.getRatio());


    }

    @Test
    void shouldIsMutantOK() {
        when(validadorSecuenciaAdnUseCase.isMutant(secuencias)).thenReturn(true);
        ResponseEntity responseEntity =  secuenciaController.isMutant(dna);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void shouldIsMutantForbidden() {
        when(validadorSecuenciaAdnUseCase.isMutant(secuencias)).thenReturn(false);
        ResponseEntity responseEntity =  secuenciaController.isMutant(dna);
        assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
    }

    @Test
    void shouldIsMutantException() {
        ResponseEntity responseEntity =  secuenciaController.isMutant(null);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }

}