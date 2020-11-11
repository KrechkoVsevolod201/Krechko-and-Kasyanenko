package ru.ssau.tk.chpok.labs.exceptions;

import java.io.Serializable;

public class InterpolationException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = -6628868806971737925L;

    public InterpolationException() {
        super();
    }

    public InterpolationException(String comment) {
        super(comment);
    }
}
