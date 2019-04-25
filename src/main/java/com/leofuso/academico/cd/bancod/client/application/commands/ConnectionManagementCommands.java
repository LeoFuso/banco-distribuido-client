package com.leofuso.academico.cd.bancod.client.application.commands;

import com.leofuso.academico.cd.bancod.client.domain.OperacaoBancaria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class ConnectionManagementCommands {

    private final OperacaoBancaria banco;

    @Autowired
    public ConnectionManagementCommands(OperacaoBancaria banco) {
        this.banco = banco;
    }

    @ShellMethod("Atualiza porta")
    public String updatePorta(int numero) {
        banco.reconfigure(null, null, numero);
        return banco.getURI();
    }

    @ShellMethod("Atualiza endereco")
    public String updateEndereco(String endereco) {
        banco.reconfigure(null, endereco, null);
        return banco.getURI();
    }

    @ShellMethod("Atualiza protocolo")
    public String updateProtocolo(String protocolo) {
        banco.reconfigure(protocolo, null, null);
        return banco.getURI();
    }

}
