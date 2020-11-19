package ru.ssau.tk.chpok.labs.concurrent;

import ru.ssau.tk.chpok.labs.functions.TabulatedFunction;

public class AddingTask implements Runnable {
    TabulatedFunction function;

    public AddingTask(TabulatedFunction func) {
        this.function = func;
    }

    @Override
    public void run() {
        double x;
        double y;
        for (int i = 0; i < function.getCount(); i++) {
            x = function.getX(i);
            y = function.getY(i);
            System.out.printf("%s, i = %d, x = %f, old y = %f \n", Thread.currentThread().getName(), i, x, y);
            function.setY(i, y + 3);
            y = function.getY(i);
            System.out.printf("%s, i = %d, x = %f, new y = %f \n", Thread.currentThread().getName(), i, x, y);
        }
    }
}