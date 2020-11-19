package ru.ssau.tk.chpok.labs.concurrent;

import ru.ssau.tk.chpok.labs.functions.ConstantFunction;
import ru.ssau.tk.chpok.labs.functions.LinkedListTabulatedFunction;
import ru.ssau.tk.chpok.labs.functions.TabulatedFunction;

import java.util.concurrent.CountDownLatch;


public class AddingMultiplyingTaskExecutor {
    public static void main(String[] args) throws InterruptedException {
        TabulatedFunction function = new LinkedListTabulatedFunction(new ConstantFunction(2), 1, 100, 100);

        CountDownLatch countDownLatch = new CountDownLatch(3);

        AddingTask addingTask = new AddingTask(function, countDownLatch::countDown);
        MultiplyingTask multiplyingTask = new MultiplyingTask(function, countDownLatch::countDown);

        Thread thread1 = new Thread(multiplyingTask);
        thread1.start();
        Thread thread2 = new Thread(multiplyingTask);
        thread2.start();
        Thread thread3 = new Thread(addingTask);
        thread3.start();

        countDownLatch.await();

        System.out.println(function.toString());
    }
}