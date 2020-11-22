package ru.ssau.tk.chpok.labs.functions;

import ru.ssau.tk.chpok.labs.exceptions.DifferentLengthOfArraysException;
import ru.ssau.tk.chpok.labs.exceptions.ArrayIsNotSortedException;
import ru.ssau.tk.chpok.labs.operations.TabulatedFunctionOperationService;

public abstract class AbstractTabulatedFunction extends Object implements TabulatedFunction {

    protected abstract int floorIndexOfX(double x);

    protected abstract double extrapolateLeft(double x);

    protected abstract double extrapolateRight(double x);

    protected abstract double interpolate(double x, int floorIndex);

    protected double interpolate(double x, double leftX, double rightX, double leftY, double rightY) {
        return (leftY + (x - leftX) * (rightY - leftY) / (rightX - leftX));
    }

    public double apply(double x) {
        if (x < leftBound()) {
            return extrapolateLeft(x);
        }
        if (x > rightBound()) {
            return extrapolateRight(x);
        }
        if (indexOfX(x) == -1) {
            return interpolate(x, floorIndexOfX(x));
        }
        return getY(indexOfX(x));
    }

    protected static void checkLengthIsTheSame(double[] xValues, double[] yValues) {
        int xV, yV;
        xV = xValues.length;
        yV = yValues.length;
        if (xV != yV) {
            throw new DifferentLengthOfArraysException("Lengths of xValues and yValues are different");
        }
    }

    protected static void checkSorted(double[] xValues) {
        int xV;
        xV = xValues.length;
        for (int i = 0; (i + 1) < xV; i++) {
            if (xValues[i] > xValues[i + 1]) {
                throw new ArrayIsNotSortedException("Array is not sorted");
            }
        }
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(this.getClass().getSimpleName()).append(" size = ").append(this.getCount()).append("\n");

        for (Point point : this) {
            str.append("[")
                    .append(point.x)
                    .append("; ")
                    .append(point.y)
                    .append("]\n");

        }
        str.deleteCharAt(str.length() - 1);
        return str.toString();
    }
}