package com.leofuso.academico.cd.bancod.client.application.exceptions;

import com.leofuso.academico.cd.bancod.client.application.communication.resources.ErrorResource;

public class CommunicationException extends RuntimeException {

    public CommunicationException() {
    }

    public CommunicationException(String message) {
        super(message);
    }

    public CommunicationException(ErrorResource error) {
        super(CommunicationException.format(error));
    }

    private static String format(ErrorResource error) {
        return String.format("%s - %s: %s", error.getCode(), error.getStatus(), error.getCause());
    }
}
