package com.leofuso.academico.cd.bancod.client.application.configuration.properties;

import org.hibernate.validator.constraints.Range;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@ConfigurationProperties(prefix = "connection")
public class ConnectionProperties {

    /**
     * Protocol of connection to the application api
     */
    private String protocol = "http";

    /**
     * Address of the application api
     */
    private String serverAddress = "localhost";

    /**
     * Port of the application api
     */
    private int serverPort = 8080;

    @NotBlank
    @Size(min = 1, max = 5)
    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    @NotBlank
    public String getServerAddress() {
        return serverAddress;
    }

    public void setServerAddress(String serverAddress) {
        this.serverAddress = serverAddress;
    }

    @NotBlank
    @Range(min = 0, max = 65535)
    public int getServerPort() {
        return serverPort;
    }

    public void setServerPort(int serverPort) {
        this.serverPort = serverPort;
    }
}
