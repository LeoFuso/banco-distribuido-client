package com.leofuso.academico.cd.bancod.client.domain;

import com.leofuso.academico.cd.bancod.client.application.communication.resources.ContaResource;

import java.net.URI;

public interface OperacaoBancaria {

    void reconfigure(String protocol,
                     String serverAddress,
                     Integer serverPort);

    String getURI();

    URI deposito(Integer conta, Double valor);

    URI saque(Integer conta, Double valor);

    URI transferencia(Integer contaOrigem, Integer contaDestino, Double valor);

    ContaResource saldo(Integer conta);

    ContaResource saldo(URI uri);

}
