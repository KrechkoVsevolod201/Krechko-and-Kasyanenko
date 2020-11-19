package ru.ssau.tk.chpok.labs.concurrent;

import ru.ssau.tk.chpok.labs.functions.LinkedListTabulatedFunction;
import ru.ssau.tk.chpok.labs.functions.TabulatedFunction;
import ru.ssau.tk.chpok.labs.functions.ZeroFunction;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

public class ReadWriteTaskExecutor {
    public static void main(String[] args) {
        TabulatedFunction function = new LinkedListTabulatedFunction(new ZeroFunction(), 1, 10, 10);
        ArrayList<Thread> list = new ArrayList<>();
        int countThread = 20;
        CountDownLatch countDownLatch = new CountDownLatch(countThread);
        ReadWriteTask myTask = new ReadWriteTask(function, countDownLatch::countDown);
        for (int i = 0; i < countThread; i++) {
            list.add(new Thread(myTask));
        }

        for (Thread thread : list) {
            thread.start();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(function.toString());

    }
}
