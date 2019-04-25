package com.leofuso.academico.cd.bancod.client.application.configuration.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leofuso.academico.cd.bancod.client.application.communication.resources.ErrorResource;
import com.leofuso.academico.cd.bancod.client.application.exceptions.CommunicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;
import java.io.InputStream;

import static org.springframework.http.HttpStatus.Series.CLIENT_ERROR;
import static org.springframework.http.HttpStatus.Series.SERVER_ERROR;

@Component
public class RestTemplateResponseErrorHandler
        implements ResponseErrorHandler {

    private final ObjectMapper objectMapper;

    @Autowired
    public RestTemplateResponseErrorHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public boolean hasError(ClientHttpResponse httpResponse)
            throws IOException {

        return (httpResponse.getStatusCode().series() == CLIENT_ERROR ||
                httpResponse.getStatusCode().series() == SERVER_ERROR);
    }

    @Override
    public void handleError(ClientHttpResponse httpResponse)
            throws IOException {

        final boolean isSeverError = httpResponse.getStatusCode().series() == SERVER_ERROR;
        final boolean isClientError = httpResponse.getStatusCode().series() == CLIENT_ERROR;
        final InputStream body = httpResponse.getBody();

        final ErrorResource errorResource = objectMapper.readValue(body, ErrorResource.class);
        throw new CommunicationException(errorResource);
    }
}