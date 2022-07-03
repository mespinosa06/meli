package com.meli.domain.service;

import com.meli.domain.model.Secuencia;
import com.meli.domain.port.incoming.ValidadorSecuenciaAdn;
import com.meli.domain.port.outgoing.PersistirSecuencia;
import com.meli.domain.port.outgoing.RecuperarSecuencia;
import com.meli.domain.port.outgoing.RespuestaStats;
import com.meli.infrastructure.adapter.exceptions.RepositoryExeption;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

public class SecuenciaService implements ValidadorSecuenciaAdn {

    private final RecuperarSecuencia recuperarSecuencia;
    private final PersistirSecuencia persistirSecuencia;


    public SecuenciaService(RecuperarSecuencia recuperarSecuencia, PersistirSecuencia persistirSecuencia) {
        this.recuperarSecuencia = recuperarSecuencia;
        this.persistirSecuencia = persistirSecuencia;
    }

    @Override
    public boolean isMutant(String[] adn) {
        boolean mutante = new ValidadorCadenaAdn().validarSecuencia(adn);
        guardarSecuencia(adn, mutante);
        return mutante;
    }


    private void guardarSecuencia(String[] adn, boolean mutante) {
        Secuencia secuencia = new Secuencia();
        secuencia.setAdn(adn);
        secuencia.setMutante(mutante);
        try {
            persistirSecuencia.save(secuencia);
        } catch (Exception e) {
            throw new RepositoryExeption("Error en la base de datos al guardar el adn. ".concat(e.getMessage()));

        }
    }

    @Override
    public RespuestaStats cuenta() {
        DecimalFormat df = new DecimalFormat("0.00");
        List<Map<String, Object>> cuentas;
        try {
            cuentas = recuperarSecuencia.stats();
        } catch (Exception e) {
            throw new RepositoryExeption("Error en la base de datos. ".concat(e.getMessage()));
        }
        RespuestaStats respuestaStats = new RespuestaStats(0L, 0L, 0D);
        for (Map c : cuentas) {
            if ((Boolean) c.get("1"))
                respuestaStats.setCount_mutant_dna((Long) c.get("cantidad"));
            else
                respuestaStats.setCount_human_dna((Long) c.get("cantidad"));
        }
        if (respuestaStats.getCount_human_dna() > 0)
            respuestaStats.setRatio(
                    Double.valueOf(df.format(Double.valueOf(respuestaStats.getCount_mutant_dna()) / Double.valueOf(respuestaStats.getCount_human_dna()))));
        return respuestaStats;
    }


}