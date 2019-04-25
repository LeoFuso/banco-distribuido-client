package com.leofuso.academico.cd.bancod.client.domain;

import com.leofuso.academico.cd.bancod.client.application.communication.commands.DepositoCommand;
import com.leofuso.academico.cd.bancod.client.application.communication.commands.SaqueCommand;
import com.leofuso.academico.cd.bancod.client.application.communication.commands.TransferenciaCommand;
import com.leofuso.academico.cd.bancod.client.application.communication.resources.ContaResource;

import javax.validation.constraints.NotNull;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;
import java.util.Optional;

public class OperacaoBancariaImpl implements OperacaoBancaria {

    private static final String RESOURCE = "contas";

    private RestTemplateExtension restTemplate;

    @NotNull
    private URI baseURI;

    @NotNull
    private String protocol;

    @NotNull
    private String serverAddress;

    private int serverPort;

    public OperacaoBancariaImpl(String protocol,
                                String serverAddress,
                                int serverPort,
                                RestTemplateExtension restTemplate) {
        this.protocol = Objects.requireNonNull(protocol);
        this.serverAddress = Objects.requireNonNull(serverAddress);
        this.serverPort = serverPort;
        this.restTemplate = restTemplate;
        this.baseURI = this.buildBaseURI();
    }

    private URI buildBaseURI() {

        final String baseURL = String.format("%s://%s:%s/%s", protocol, serverAddress, serverPort, OperacaoBancariaImpl.RESOURCE);

        URI uri = null;
        try {
            uri = new URI(baseURL);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return uri;
    }

    @Override
    public void reconfigure(String protocol, String serverAddress, Integer serverPort) {

        this.protocol = Optional.ofNullable(protocol).orElse(this.protocol);
        this.serverAddress = Optional.ofNullable(serverAddress).orElse(this.serverAddress);
        this.serverPort = Optional.ofNullable(serverPort).orElse(this.serverPort);
        this.baseURI = buildBaseURI();
    }

    @Override
    public String getURI() {
        return this.baseURI.toString();
    }

    @Override
    public URI deposito(Integer conta, Double valor) {

        String idPath = String.format("/%d/deposito", conta);
        URI uri = baseURI.resolve(baseURI.getPath() + idPath);
        final DepositoCommand command = DepositoCommand.produce(conta, valor);
        return restTemplate.putForLocation(uri, command);

    }

    @Override
    public URI saque(Integer conta, Double valor) {

        String idPath = String.format("/%d/saque", conta);
        URI uri = baseURI.resolve(baseURI.getPath() + idPath);
        final SaqueCommand command = SaqueCommand.produce(conta, valor);
        return restTemplate.putForLocation(uri, command);

    }

    @Override
    public URI transferencia(Integer contaOrigem, Integer contaDestino, Double valor) {

        String idPath = String.format("/%d/transferencia", contaOrigem);
        URI uri = baseURI.resolve(baseURI.getPath() + idPath);
        final TransferenciaCommand command = TransferenciaCommand.produce(contaOrigem, contaDestino, valor);
        return restTemplate.putForLocation(uri, command);

    }

    @Override
    public ContaResource saldo(Integer conta) {

        String idPath = String.format("/%d", conta);
        URI uri = baseURI.resolve(baseURI.getPath() + idPath);
        return restTemplate.getForObject(uri, ContaResource.class);

    }

    @Override
    public ContaResource saldo(URI uri) {

        final URI nonNullUri = Objects.requireNonNull(uri);
        return restTemplate.getForObject(nonNullUri, ContaResource.class);

    }
}
