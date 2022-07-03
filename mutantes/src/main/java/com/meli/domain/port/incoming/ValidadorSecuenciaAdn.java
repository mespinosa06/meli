package com.meli.domain.port.incoming;

import com.meli.domain.port.outgoing.RespuestaStats;

public interface ValidadorSecuenciaAdn {
    boolean isMutant(String[] adn);
    RespuestaStats cuenta();
}
