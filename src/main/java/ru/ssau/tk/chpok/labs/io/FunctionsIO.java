package ru.ssau.tk.chpok.labs.io;
import ru.ssau.tk.chpok.labs.functions.Point;
import ru.ssau.tk.chpok.labs.functions.TabulatedFunction;
import ru.ssau.tk.chpok.labs.functions.factory.TabulatedFunctionFactory;
import ru.ssau.tk.chpok.labs.operations.TabulatedFunctionOperationService;

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
    static void writeTabulatedFunction(BufferedOutputStream outputStream, TabulatedFunction function) throws IOException {
        DataOutputStream out = new DataOutputStream(outputStream);
        out.writeInt(function.getCount());
        for (Point currentPoint : TabulatedFunctionOperationService.asPoints(function)) {
            out.writeDouble(currentPoint.x);
            out.writeDouble(currentPoint.y);
        }
        out.flush();
    }
}
