package com.leofuso.academico.cd.bancod.client.domain.integration.resources;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Objects;

@ApplicationRESTResource
public class ContaResource implements Serializable {

    private final Integer id;
    private final Double saldo;
    private final String email;

    private ContaResource(Integer id, Double saldo, String email) {
        this.id = Objects.requireNonNull(id);
        this.saldo = Objects.requireNonNull(saldo);
        this.email = Objects.requireNonNull(email);
    }

    @JsonCreator
    public static ContaResource produce(@JsonProperty("id") Integer id,
                                        @JsonProperty("saldo") Double saldo,
                                        @JsonProperty("email") String email) {
        return new ContaResource(id, saldo, email);
    }

    public Integer getId() {
        return id;
    }


    public Double getSaldo() {
        return saldo;
    }


    public String getEmail() {
        return email;
    }
}
