package com.meli.domain.service;

import com.meli.domain.port.outgoing.PersistirSecuencia;
import com.meli.domain.port.outgoing.RecuperarSecuencia;
import com.meli.domain.port.outgoing.RespuestaStats;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SecuenciaServiceTest {

    @InjectMocks
    SecuenciaService secuenciaService;
    @Mock
    private RecuperarSecuencia recuperarSecuencia;
    @Mock
    private PersistirSecuencia persistirSecuencia;

    String[] secuenciasMutante;
    String[] secuenciasHumano;
    List<Map<String, Object>> cuentas;
    Map<String, Object> mapMutante = new HashMap<String, Object>();
    Map<String, Object> mapHumano = new HashMap<String, Object>();

    @BeforeEach
    void setUp() {
        secuenciasMutante = new String[]{"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG´"};
        secuenciasHumano = new String[]{"GTGCAA", "CAGTGC", "TTATGT", "AGAAGG", "TCCCTA", "TCACTG´"};

        cuentas = new ArrayList<>();
        mapMutante = new HashMap<String, Object>();
        mapMutante.put("1", true);
        mapMutante.put("cantidad", 4L);
        mapHumano = new HashMap<String, Object>();
        mapHumano.put("1", false);
        mapHumano.put("cantidad", 2L);
        cuentas.add(mapMutante);
        cuentas.add(mapHumano);

    }

    @Test
    void shouldIsMutant() {
        assertTrue(secuenciaService.isMutant(secuenciasMutante));
    }

    @Test
    void shouldIsHuman() {
        assertFalse(secuenciaService.isMutant(secuenciasHumano));
    }

    @Test
    void cuenta() {
       when(recuperarSecuencia.stats()).thenReturn(cuentas);
       RespuestaStats respuestaStats = secuenciaService.cuenta();
       assertNotNull(respuestaStats);
       assertEquals(4, respuestaStats.getCount_mutant_dna());
       assertEquals(2, respuestaStats.getCount_human_dna());
       assertEquals(2.0D, respuestaStats.getRatio());
    }
}