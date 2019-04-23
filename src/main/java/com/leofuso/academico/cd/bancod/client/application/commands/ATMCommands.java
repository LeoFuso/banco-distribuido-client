package com.leofuso.academico.cd.bancod.client.application.commands;

import com.leofuso.academico.cd.bancod.client.domain.OperacaoBancaria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class ATMCommands {

    private final OperacaoBancaria banco;

    @Autowired
    public ATMCommands(OperacaoBancaria banco) {
        this.banco = banco;
    }

    @ShellMethod("Retorna o saldo de uma conta")
    public double saldo(int numeroConta) {
        return banco.saldo(numeroConta);
    }
}
