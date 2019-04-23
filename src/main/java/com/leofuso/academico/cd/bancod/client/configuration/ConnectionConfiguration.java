package com.leofuso.academico.cd.bancod.client.configuration;

import com.leofuso.academico.cd.bancod.client.configuration.properties.ConnectionProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({ConnectionProperties.class})
public class ConnectionConfiguration {

    private final ConnectionProperties properties;

    @Autowired
    public ConnectionConfiguration(ConnectionProperties properties) {
        this.properties = properties;
    }
}
