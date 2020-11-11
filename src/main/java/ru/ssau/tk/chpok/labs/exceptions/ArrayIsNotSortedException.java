package ru.ssau.tk.chpok.labs.exceptions;

import java.io.Serializable;

public class ArrayIsNotSortedException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = -7280175716771272158L;

    public ArrayIsNotSortedException() {
        super();

    }

    public ArrayIsNotSortedException(String comment) {
        super(comment);
    }
}
