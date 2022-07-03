package com.meli.domain.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class ValidadorCadenaAdnTest {

    @InjectMocks
    ValidadorCadenaAdn validadorCadenaAdn;

    String[] secuenciasMutante;
    String[] secuenciasHumano;

    @BeforeEach
    void setUp() {
        secuenciasMutante = new String[]{"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG´"};
        secuenciasHumano = new String[]{"GTGCAA", "CAGTGC", "TTATGT", "AGAAGG", "TCCCTA", "TCACTG´"};
    }

    @Test
    void validarSecuenciaMutante() {
        assertTrue(validadorCadenaAdn.validarSecuencia(secuenciasMutante));
    }

    @Test
    void validarSecuenciaHumano() {
        assertFalse(validadorCadenaAdn.validarSecuencia(secuenciasHumano));
    }
}