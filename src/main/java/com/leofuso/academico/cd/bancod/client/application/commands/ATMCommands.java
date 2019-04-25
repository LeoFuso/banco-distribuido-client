package com.leofuso.academico.cd.bancod.client.application.commands;

import com.leofuso.academico.cd.bancod.client.application.communication.resources.ContaResource;
import com.leofuso.academico.cd.bancod.client.domain.OperacaoBancaria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.net.URI;

@ShellComponent
public class ATMCommands {

    private final OperacaoBancaria banco;

    @Autowired
    public ATMCommands(OperacaoBancaria banco) {
        this.banco = banco;
    }

    private static String extractSaldo(ContaResource conta) {
        return String.format("SALDO [ %.2f ]", conta.getSaldo());
    }

    @ShellMethod("Retorna o saldo de uma conta")
    public String saldo(int numero) {
        final ContaResource conta = banco.saldo(numero);
        return extractSaldo(conta);
    }

    @ShellMethod("Realiza um depósito")
    public String deposito(int numeroConta, double valor) {
        final URI deposito = banco.deposito(numeroConta, valor);
        final ContaResource conta = banco.saldo(deposito);
        return extractSaldo(conta);
    }

    @ShellMethod("Realiza um saque")
    public String saque(int numeroConta, double valor) {
        final URI deposito = banco.saque(numeroConta, valor);
        final ContaResource conta = banco.saldo(deposito);
        return extractSaldo(conta);
    }

    @ShellMethod("Realiza uma transferência")
    public String transferencia(int numeroContaOrigem, int numeroContaDestino, double valor) {
        final URI deposito = banco.transferencia(numeroContaOrigem, numeroContaDestino, valor);
        final ContaResource conta = banco.saldo(deposito);
        return extractSaldo(conta);
    }
}
