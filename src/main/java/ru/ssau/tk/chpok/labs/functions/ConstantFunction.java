package ru.ssau.tk.chpok.labs.functions;

public class ConstantFunction implements MathFunction {
    private final double constant;

    public ConstantFunction(double constant) {
        this.constant = constant;
    }

    @Override//переопределение метода базового класса.
    public double apply(double x) {
        return constant;
    }

    public double getConstant() {
        return constant;
    }
}
