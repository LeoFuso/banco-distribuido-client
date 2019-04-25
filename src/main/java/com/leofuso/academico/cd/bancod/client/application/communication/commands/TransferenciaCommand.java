package com.leofuso.academico.cd.bancod.client.application.communication.commands;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@CommandApplicationEvent
public class TransferenciaCommand implements Serializable {

    @NotNull
    private final Integer contaOrigemId;

    @NotNull
    private final Integer contaDestinoId;

    @NotNull
    private final Double valor;

    private TransferenciaCommand(Integer contaOrigemId,
                                 Integer contaDestinoId,
                                 Double valor) {
        this.contaOrigemId = Objects.requireNonNull(contaOrigemId);
        this.contaDestinoId = Objects.requireNonNull(contaDestinoId);
        this.valor = Objects.requireNonNull(valor);
    }

    public static TransferenciaCommand produce(@NotNull Integer contaOrigemId,
                                               @NotNull Integer contaDestinoId,
                                               @NotNull Double valor) {
        return new TransferenciaCommand(contaOrigemId, contaDestinoId, valor);
    }

    @JsonProperty("conta_origem_id")
    public Integer getContaOrigemId() {
        return contaOrigemId;
    }

    @JsonProperty("conta_destino_id")
    public Integer getContaDestinoId() {
        return contaDestinoId;
    }

    @JsonProperty("valor")
    public Double getValor() {
        return valor;
    }
}
