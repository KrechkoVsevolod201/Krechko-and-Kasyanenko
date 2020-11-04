package ru.ssau.tk.chpok.labs.functions.factory;

import ru.ssau.tk.chpok.labs.functions.TabulatedFunction;

public interface TabulatedFunctionFactory {
    TabulatedFunction create(double[] xValues, double[] yValues);
}
