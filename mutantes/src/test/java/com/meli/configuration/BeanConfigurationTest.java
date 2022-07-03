package com.meli.configuration;

import com.meli.infrastructure.adapter.SecuenciaRepository;
import com.meli.infrastructure.adapter.SpringDataSecuenciaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class BeanConfigurationTest {

    @InjectMocks
    private BeanConfiguration beanConfiguration;
    @Mock
    private SpringDataSecuenciaRepository repository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void shouldBuildSecuenciaService() {
        assertNotNull(beanConfiguration.secuenciaService(new SecuenciaRepository(repository)));
    }
}