package ru.ssau.tk.chpok.labs.io;

import ru.ssau.tk.chpok.labs.functions.ArrayTabulatedFunction;
import ru.ssau.tk.chpok.labs.functions.LinkedListTabulatedFunction;
import ru.ssau.tk.chpok.labs.functions.TabulatedFunction;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class TabulatedFunctionFileWriter {
    public static void main(String[] args) {
        double[] x = {0, 1, 2};
        double[] y = {0, 10, 20};
        TabulatedFunction listFunction = new LinkedListTabulatedFunction(x, y);
        TabulatedFunction arrayFunction = new ArrayTabulatedFunction(x, y);
        try (BufferedWriter outArray = new BufferedWriter(new FileWriter("output/array function.txt"));
             BufferedWriter outList = new BufferedWriter(new FileWriter("output/linked list function.txt"))) {
            FunctionsIO.writeTabulatedFunction(outArray, arrayFunction);
            FunctionsIO.writeTabulatedFunction(outList, listFunction);

        } catch (IOException err) {
            err.printStackTrace();
        }
    }
}

