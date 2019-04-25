package com.leofuso.academico.cd.bancod.client.application.communication.commands;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Getter
@CommandApplicationEvent
public class DepositoCommand implements Serializable {

    @NotNull
    @JsonProperty("conta_id")
    private final Integer contaId;

    @NotNull
    @JsonProperty("valor")
    private final Double valor;

    private DepositoCommand(Integer contaId,
                            Double valor) {
        this.contaId = Objects.requireNonNull(contaId);
        this.valor = Objects.requireNonNull(valor);
    }

    public static DepositoCommand produce(Integer contaId,
                                          Double valor) {
        return new DepositoCommand(contaId, valor);
    }
}
