package ru.ssau.tk.chpok.labs.io;
import ru.ssau.tk.chpok.labs.functions.Point;
import ru.ssau.tk.chpok.labs.functions.TabulatedFunction;
import ru.ssau.tk.chpok.labs.functions.factory.TabulatedFunctionFactory;

import java.io.*;

final class FunctionsIO {
    static void readTabulatedFunction(BufferedInputStream inputStream, TabulatedFunctionFactory factory) {
        throw new UnsupportedOperationException("Oh no, boy, I think that is wrong operator");
    }
    static void  writeTabulatedFunction(BufferedWriter writer, TabulatedFunction function){
        PrintWriter printWriter = new PrintWriter(writer);
        printWriter.println(function.getCount());
        int i = 0;
        for (Point point : function) {
            printWriter.printf("%f %f\n", point.x, point.y);
        }
        printWriter.flush();
    }
}
