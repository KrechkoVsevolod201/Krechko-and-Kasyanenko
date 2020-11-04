package ru.ssau.tk.chpok.labs.operations;

import ru.ssau.tk.chpok.labs.functions.MathFunction;

public interface DifferentialOperator<T extends MathFunction>  {
    T derive(T function);
}
