package ru.ssau.tk.chpok.labs.concurrent;

import ru.ssau.tk.chpok.labs.functions.TabulatedFunction;

public class ReadWriteTask implements Runnable {
    private final TabulatedFunction tabulatedFunction;

    public ReadWriteTask(TabulatedFunction tabulatedFunction) {
        this.tabulatedFunction = tabulatedFunction;
    }

    @Override
    public void run() {
        for (int i = 0; i < tabulatedFunction.getCount(); i++) {
            double currentX = tabulatedFunction.getX(i);
            double currentY;
            synchronized (tabulatedFunction) {
                currentY = tabulatedFunction.getY(i);
                System.out.println(Thread.currentThread().getName() + " before write: i = " + i + " x = " + currentX + " y = " + currentY);
                tabulatedFunction.setY(i, currentY + 1);
                currentY = tabulatedFunction.getY(i);
            }
            System.out.println(Thread.currentThread().getName() + " after write: i = " + i + " x = " + currentX + " y = " + currentY);
        }
    }
}
