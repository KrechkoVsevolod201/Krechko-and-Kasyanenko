package ru.ssau.tk.chpok.labs.exceptions;

public class InterpolationException extends RuntimeException{
    private int x;
    public InterpolationException (){
        super();
    }
    public InterpolationException (String comment){
        super(comment);
    }
}
