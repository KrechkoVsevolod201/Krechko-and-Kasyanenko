package ru.ssau.tk.chpok.labs.concurrent;
import ru.ssau.tk.chpok.labs.functions.LinkedListTabulatedFunction;
import ru.ssau.tk.chpok.labs.functions.TabulatedFunction;
import ru.ssau.tk.chpok.labs.functions.ZeroFunction;

import java.util.ArrayList;
import java.util.List;


public class ReadWriteTaskExecutor {
    public static void main(String[] args) {
        TabulatedFunction tabulatedFunction = new LinkedListTabulatedFunction(new ZeroFunction(), 1, 10, 10);
        List<Thread> threadList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Thread newThread = new Thread(new ReadWriteTask(tabulatedFunction));
            threadList.add(newThread);
        }
        for (Thread currentTread : threadList) {
            currentTread.start();
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(tabulatedFunction);
    }
}