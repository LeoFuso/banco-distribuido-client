package com.leofuso.academico.cd.bancod.client.application.configuration;

import com.leofuso.academico.cd.bancod.client.application.configuration.properties.ConnectionProperties;
import com.leofuso.academico.cd.bancod.client.domain.OperacaoBancaria;
import com.leofuso.academico.cd.bancod.client.domain.OperacaoBancariaImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

@Configuration
@EnableConfigurationProperties({ConnectionProperties.class})
public class ConnectionConfiguration {

    private final ConnectionProperties properties;

    @Autowired
    public ConnectionConfiguration(ConnectionProperties properties) {
        this.properties = properties;
    }

    @Bean
    public OperacaoBancaria operacaoBancariaFactory() {
        return new OperacaoBancariaImpl(properties.getProtocol(),
                properties.getServerAddress(),
                properties.getServerPort(),
                getClientHttpRequestFactory());
    }

    private ClientHttpRequestFactory getClientHttpRequestFactory() {
        int timeout = 5000;
        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory
                = new HttpComponentsClientHttpRequestFactory();
        clientHttpRequestFactory.setConnectTimeout(timeout);
        return clientHttpRequestFactory;
    }
}
