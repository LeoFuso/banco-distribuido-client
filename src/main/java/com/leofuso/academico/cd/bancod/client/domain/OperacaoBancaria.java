package com.leofuso.academico.cd.bancod.client.domain;

public interface OperacaoBancaria {

    void deposito(int conta, double valor);

    void saque(int conta, double valor);

    void transferencia(int contaOrigem, int contaDestino, double valor);

    double saldo(int conta);

}
