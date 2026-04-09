package org.eduardomango.practicaspringweb.model.exceptions;

public class RepeatedIDException extends RuntimeException {
    public RepeatedIDException(String message) {
        super(message);
    }

    public RepeatedIDException() {
    }
}
