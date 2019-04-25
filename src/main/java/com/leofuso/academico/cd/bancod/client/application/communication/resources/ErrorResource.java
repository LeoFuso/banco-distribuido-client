package com.leofuso.academico.cd.bancod.client.application.communication.resources;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Getter
@ApplicationRESTResource
public class ErrorResource implements Serializable {

    @NotNull
    @JsonProperty("code")
    private final Integer code;

    @NotNull
    @JsonProperty("status")
    private final String status;

    @Nullable
    @JsonProperty("cause")
    private final String cause;

    private ErrorResource(@NotNull Integer code,
                          @NotNull String status,
                          @Nullable String cause) {
        this.code = Objects.requireNonNull(code);
        this.status = Objects.requireNonNull(status);
        this.cause = cause;
    }

    @JsonCreator
    public ErrorResource produce(Integer code,
                                 String status,
                                 String cause) {
        return new ErrorResource(code, status, cause);
    }
}
