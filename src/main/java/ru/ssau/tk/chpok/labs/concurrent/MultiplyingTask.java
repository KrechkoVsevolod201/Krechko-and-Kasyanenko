package ru.ssau.tk.chpok.labs.concurrent;

import ru.ssau.tk.chpok.labs.functions.TabulatedFunction;

public class MultiplyingTask implements Runnable{
    final TabulatedFunction tabulatedFunction;
    Runnable postRunAction;

    public MultiplyingTask(TabulatedFunction func) {
        this.tabulatedFunction = func;
    }

    public MultiplyingTask(TabulatedFunction func, Runnable postRunAction) {
        this.tabulatedFunction = func;
        this.postRunAction = postRunAction;
    }

    public void run() {
        for (int i = 0; i < tabulatedFunction.getCount(); i++) {
            double currentX = tabulatedFunction.getX(i);
            double currentY = tabulatedFunction.getY(i);
            System.out.println(Thread.currentThread().getName() + "i =" + i + " x = " + currentX + " old y = " + currentY);
            tabulatedFunction.setY(i, currentY * 10);
            currentY = tabulatedFunction.getY(i);
            System.out.println(Thread.currentThread().getName() + "i =" + i + " x = " + currentX + " new y = " + currentY);
        }
    }
}