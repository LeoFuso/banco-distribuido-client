package com.leofuso.academico.cd.bancod.client.domain;

import com.leofuso.academico.cd.bancod.client.domain.integration.resources.ContaResource;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;
import java.util.Optional;

public class OperacaoBancariaImpl implements OperacaoBancaria {

    private static final String RESOURCE = "contas";

    private RestTemplate restTemplate;

    private URI baseURI;

    private String protocol;
    private String serverAddress;
    private int serverPort;

    public OperacaoBancariaImpl(String protocol, String serverAddress, int serverPort) {
        this.protocol = Objects.requireNonNull(protocol);
        this.serverAddress = Objects.requireNonNull(serverAddress);
        this.serverPort = serverPort;
        this.restTemplate = new RestTemplate();
        this.baseURI = this.buildBaseURI();
    }

    private URI buildBaseURI() {

        final String baseURL = String.format("%s://%s:%s/%s/", protocol, serverAddress, serverPort, OperacaoBancariaImpl.RESOURCE);

        URI uri = null;
        try {
            uri = new URI(baseURL);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return uri;
    }

    @Override
    public void deposito(int conta, double valor) {
        throw new UnsupportedOperationException("Não implementado.");
    }

    @Override
    public void saque(int conta, double valor) {
        throw new UnsupportedOperationException("Não implementado.");
    }

    @Override
    public void transferencia(int contaOrigem, int contaDestino, double valor) {
        throw new UnsupportedOperationException("Não implementado.");
    }

    @Override
    public double saldo(int conta) {

        String idPath = String.format("/%d", conta);
        URI uri = baseURI.resolve(baseURI.getPath() + idPath);
        final ContaResource object = restTemplate.getForObject(uri, ContaResource.class);

        return Optional.ofNullable(object)
                .map(ContaResource::getSaldo)
                .orElseThrow(IllegalArgumentException::new);
    }
}
