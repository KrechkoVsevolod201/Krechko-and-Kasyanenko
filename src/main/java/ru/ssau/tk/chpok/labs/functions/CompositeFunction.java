package ru.ssau.tk.chpok.labs.functions;

public class CompositeFunction implements MathFunction {
    private final MathFunction firstFunction;//создает константные поля
    private final MathFunction secondFunction;//создает константные поля

    public CompositeFunction(MathFunction firstFunction, MathFunction secondFunction) {
        this.firstFunction = firstFunction;
        this.secondFunction = secondFunction;
    }

    public double apply(double x) {
        return secondFunction.apply(firstFunction.apply(x));//сложная функция
    }
}
