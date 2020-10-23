package ru.ssau.tk.chpok.labs.exceptions;

public class ArrayIsNotSortedException extends RuntimeException{

    public ArrayIsNotSortedException (){
        super();

    }
    public ArrayIsNotSortedException (String comment){
        super(comment);
    }
}
