package ru.ssau.tk.chpok.labs.exceptions;

import java.io.Serializable;

public class DifferentLengthOfArraysException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = -1945375361267455276L;

    public DifferentLengthOfArraysException() {
        super();
    }

    public DifferentLengthOfArraysException(String comment) {
        super(comment);
    }
}
