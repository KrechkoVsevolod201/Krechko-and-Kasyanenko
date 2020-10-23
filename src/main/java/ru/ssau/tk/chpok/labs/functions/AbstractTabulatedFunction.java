package ru.ssau.tk.chpok.labs.functions;

import ru.ssau.tk.chpok.labs.exceptions.DifferentLengthOfArraysException;
import ru.ssau.tk.chpok.labs.exceptions.ArrayIsNotSortedException;

public abstract class AbstractTabulatedFunction implements TabulatedFunction {

    protected abstract int floorIndexOfX(double x);

    protected abstract double extrapolateLeft(double x);

    protected abstract double extrapolateRight(double x);

    protected abstract double interpolate(double x, int floorIndex);

    protected double interpolate(double x, double leftX, double rightX, double leftY, double rightY) {
        return (leftY + (x - leftX) * (rightY - leftY) / (rightX - leftX));
    }

    @Override
    public double apply(double x) {
        if (x < leftBound()) {
            return (extrapolateLeft(x));
        }

        if (x > rightBound()) {
            return (extrapolateRight(x));
        }

        if (indexOfX(x) != -1) {
            return getY(indexOfX(x));
        } else {
            return interpolate(x, floorIndexOfX(x));
        }
    }

    void checkLengthIsTheSame(double[] xValues, double[] yValues) {
        int xV, yV;
        xV = xValues.length;
        yV = yValues.length;
        if (xV != yV) {
            throw new DifferentLengthOfArraysException("Lengths of xValues and yValues are different");
        }
    }

    void checkSorted(double[] xValues) {
        int xV;
        xV = xValues.length;
        for (int i = 0; i < xV; i++) {
            if (xValues[i] > xValues[i + 1]) {
                throw new ArrayIsNotSortedException("Array is not sorted");
            }
        }
    }
}