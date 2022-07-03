package com.meli.domain.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@NoArgsConstructor
@Entity
@Table(name = "secuencia")
public class Secuencia {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "adn")
    private String[] adn;

    @Column(name = "mutante")
    private boolean mutante;

}
