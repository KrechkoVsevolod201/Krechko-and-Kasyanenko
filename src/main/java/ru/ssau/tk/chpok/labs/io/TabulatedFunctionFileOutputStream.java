package ru.ssau.tk.chpok.labs.io;

import ru.ssau.tk.chpok.labs.functions.TabulatedFunction;
import ru.ssau.tk.chpok.labs.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.chpok.labs.functions.factory.LinkedListTabulatedFunctionFactory;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class TabulatedFunctionFileOutputStream {
    public static void main(String[] args) {
        try (BufferedOutputStream listOut = new BufferedOutputStream(new FileOutputStream("output/linked list function.bin"));
             BufferedOutputStream arrayOut = new BufferedOutputStream(new FileOutputStream("output/array function.bin"))) {
            double[] xValues = new double[]{-3., -2., -1., 0., 1., 2., 3.};
            double[] yValues = new double[]{9., 4., 1., 0., 1., 4., 9.};
            ArrayTabulatedFunctionFactory arrayFactory = new ArrayTabulatedFunctionFactory();
            LinkedListTabulatedFunctionFactory linkedListFactory = new LinkedListTabulatedFunctionFactory();
            TabulatedFunction arrayFunction = arrayFactory.create(xValues, yValues);
            TabulatedFunction linkedListFunction = linkedListFactory.create(xValues, yValues);
            FunctionsIO.writeTabulatedFunction(arrayOut, arrayFunction);
            FunctionsIO.writeTabulatedFunction(listOut, linkedListFunction);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}