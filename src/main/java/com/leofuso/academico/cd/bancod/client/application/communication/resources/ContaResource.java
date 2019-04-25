package com.leofuso.academico.cd.bancod.client.application.communication.resources;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Getter
@ApplicationRESTResource
public class ContaResource implements Serializable {

    @NotNull
    private final Integer id;

    @NotNull
    private final Double saldo;

    @NotNull
    private final String email;

    private ContaResource(@NotNull Integer id,
                          @NotNull Double saldo,
                          @NotNull String email) {
        this.id = Objects.requireNonNull(id);
        this.saldo = Objects.requireNonNull(saldo);
        this.email = Objects.requireNonNull(email);
    }

    @JsonCreator
    public static ContaResource create(@JsonProperty("id") Integer id,
                                       @JsonProperty("saldo") Double saldo,
                                       @JsonProperty("email") String email) {
        return new ContaResource(id, saldo, email);
    }
}
