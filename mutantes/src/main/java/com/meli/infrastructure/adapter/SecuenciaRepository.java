package com.meli.infrastructure.adapter;


import com.meli.domain.model.Secuencia;
import com.meli.domain.port.outgoing.PersistirSecuencia;
import com.meli.domain.port.outgoing.RecuperarSecuencia;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class SecuenciaRepository implements RecuperarSecuencia, PersistirSecuencia {

    private final SpringDataSecuenciaRepository repository;

    public SecuenciaRepository(SpringDataSecuenciaRepository repository) {
        this.repository = repository;
    }

    public void save(Secuencia secuencia) {
        repository.save(secuencia);
    }

    @Override
    public List<Map<String, Object>> stats() {
        return  repository.countAllByMutante();
    }
}
