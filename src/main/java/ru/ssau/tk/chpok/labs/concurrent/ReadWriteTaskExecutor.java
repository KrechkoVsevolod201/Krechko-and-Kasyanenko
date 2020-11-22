package ru.ssau.tk.chpok.labs.concurrent;
import ru.ssau.tk.chpok.labs.functions.LinkedListTabulatedFunction;
import ru.ssau.tk.chpok.labs.functions.ZeroFunction;

import java.util.ArrayList;


public class ReadWriteTaskExecutor {
    public static void main(String[] args) throws InterruptedException {
        LinkedListTabulatedFunction linkedListTabulatedFunction = new LinkedListTabulatedFunction(new ZeroFunction(), 1, 10, 10);
        ArrayList<Thread> listThread = new ArrayList<>();

        for (int i = 0; i != 20; i++) {
            listThread.add(new Thread(new ReadWriteTask(linkedListTabulatedFunction)));
        }
        for (Thread thread: listThread){
            thread.start();
        }
        Thread.sleep(1000);
        System.out.println(linkedListTabulatedFunction.toString());
    }
}