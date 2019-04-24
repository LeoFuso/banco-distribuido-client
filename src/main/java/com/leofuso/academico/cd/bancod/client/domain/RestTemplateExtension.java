package com.leofuso.academico.cd.bancod.client.domain;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.lang.Nullable;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

public class RestTemplateExtension extends RestTemplate {

    public RestTemplateExtension() {
        super();
    }

    public RestTemplateExtension(ClientHttpRequestFactory requestFactory) {
        super(requestFactory);
    }

    public RestTemplateExtension(List<HttpMessageConverter<?>> messageConverters) {
        super(messageConverters);
    }

    @Nullable
    public URI putForLocation(String url, @Nullable Object request, Object... uriVariables)
            throws RestClientException {
        RequestCallback requestCallback = httpEntityCallback(request);
        HttpHeaders headers = execute(url, HttpMethod.PUT, requestCallback, headersExtractor(), uriVariables);
        return (headers != null ? headers.getLocation() : null);
    }

    @Nullable
    public URI putForLocation(URI url, @Nullable Object request)
            throws RestClientException {
        RequestCallback requestCallback = httpEntityCallback(request);
        HttpHeaders headers = execute(url, HttpMethod.PUT, requestCallback, headersExtractor());
        return (headers != null ? headers.getLocation() : null);
    }
}
