package ru.ssau.tk.chpok.labs.functions.factory;

import ru.ssau.tk.chpok.labs.functions.ArrayTabulatedFunction;
import ru.ssau.tk.chpok.labs.functions.MathFunction;
import ru.ssau.tk.chpok.labs.functions.TabulatedFunction;

public class ArrayTabulatedFunctionFactory implements TabulatedFunctionFactory {
    @Override
    public TabulatedFunction create(double[] xValues, double[] yValues) {
        return new ArrayTabulatedFunction(xValues, yValues);
    }

    public TabulatedFunction create(MathFunction source, double xFrom, double xTo, int count) {
        return new ArrayTabulatedFunction(source, xFrom, xTo, count);
    }
}
