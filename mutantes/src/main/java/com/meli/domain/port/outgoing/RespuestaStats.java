package com.meli.domain.port.outgoing;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RespuestaStats {

    Long count_mutant_dna;
    Long count_human_dna;
    Double ratio;
}
