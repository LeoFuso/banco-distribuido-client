package com.leofuso.academico.cd.bancod.client.application.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leofuso.academico.cd.bancod.client.application.configuration.handlers.RestTemplateResponseErrorHandler;
import com.leofuso.academico.cd.bancod.client.application.configuration.properties.ConnectionProperties;
import com.leofuso.academico.cd.bancod.client.domain.OperacaoBancaria;
import com.leofuso.academico.cd.bancod.client.domain.OperacaoBancariaImpl;
import com.leofuso.academico.cd.bancod.client.domain.RestTemplateExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
@EnableConfigurationProperties({ConnectionProperties.class})
public class ConnectionConfiguration {

    private final RestTemplateBuilder templateBuilder;

    private final ConnectionProperties properties;

    private final ObjectMapper objectMapper;

    @Autowired
    public ConnectionConfiguration(ConnectionProperties properties,
                                   RestTemplateBuilder templateBuilder, ObjectMapper objectMapper) {
        this.properties = properties;
        this.templateBuilder = templateBuilder;
        this.objectMapper = objectMapper;
    }

    @Bean
    public OperacaoBancaria operacaoBancariaFactory() {

        RestTemplateExtension restTemplate = templateBuilder
                .setConnectTimeout(Duration.ofMillis(5000))
                .errorHandler(new RestTemplateResponseErrorHandler(objectMapper))
                .build(RestTemplateExtension.class);

        return new OperacaoBancariaImpl(properties.getProtocol(),
                properties.getServerAddress(),
                properties.getServerPort(),
                restTemplate);
    }

    private ClientHttpRequestFactory getClientHttpRequestFactory() {
        int timeout = 5000;
        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory
                = new HttpComponentsClientHttpRequestFactory();
        clientHttpRequestFactory.setConnectTimeout(timeout);
        return clientHttpRequestFactory;
    }
}
