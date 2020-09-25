package ru.ssau.tk.LaboratoryWork2.functions;

public class ConstantFunction implements MathFunction {
    private final double constant;//инициализация переменной

    public double getConstant() {
        return constant;//возвращение переменной
    }

    public ConstantFunction(double constant) {
        this.constant = constant;//инициализация в конструкторе
    }

    @Override//переопределение метода базового класса.
    public double apply(double x) {
        return constant;
    }
}
