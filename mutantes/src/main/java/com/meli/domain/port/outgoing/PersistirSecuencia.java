package com.meli.domain.port.outgoing;

import com.meli.domain.model.Secuencia;

public interface PersistirSecuencia {

    void save(Secuencia secuencia);
}