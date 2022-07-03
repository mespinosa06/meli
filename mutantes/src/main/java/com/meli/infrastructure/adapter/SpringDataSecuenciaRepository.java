package com.meli.infrastructure.adapter;

import com.meli.domain.model.Secuencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;


public interface SpringDataSecuenciaRepository extends JpaRepository<Secuencia, Long> {
    @Query(value = "select new Map(count(v) as cantidad, v.mutante) from Secuencia v group by v.mutante")
    List<Map<String, Object>> countAllByMutante();
}
