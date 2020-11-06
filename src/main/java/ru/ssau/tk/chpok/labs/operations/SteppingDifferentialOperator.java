package ru.ssau.tk.chpok.labs.operations;

import ru.ssau.tk.chpok.labs.functions.MathFunction;

public abstract class SteppingDifferentialOperator implements DifferentialOperator<MathFunction>{
    protected double step;
    public SteppingDifferentialOperator (double step){
        if (Double.isNaN(step) || step == Double.POSITIVE_INFINITY || step == Double.NEGATIVE_INFINITY || step <= 0){
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
