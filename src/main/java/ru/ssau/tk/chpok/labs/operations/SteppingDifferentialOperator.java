package ru.ssau.tk.chpok.labs.operations;

import ru.ssau.tk.chpok.labs.functions.MathFunction;

public abstract class SteppingDifferentialOperator implements DifferentialOperator{
    protected double step;
    private final double nan = Double.NaN, infinityPlus = Double.POSITIVE_INFINITY, infinityMinus = Double.NEGATIVE_INFINITY, zero = 0;

    public SteppingDifferentialOperator (double step){
        if (step == nan || step == infinityPlus || step == infinityMinus || step <= zero){
            throw new IllegalArgumentException("You have the wrong argument, BOY NEXT DOOR");
        }
        this.step = step;
    }

    public void setStep(double step) {
        this.step = step;
    }

    public double getStep() {
        return step;
    }

    @Override
    public abstract MathFunction derive(MathFunction function);
}
