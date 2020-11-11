package ru.ssau.tk.chpok.labs.functions;

import ru.ssau.tk.chpok.labs.exceptions.InterpolationException;

import java.util.NoSuchElementException;
import java.util.Arrays;
import java.io.Serializable;
import java.util.Iterator;

public class ArrayTabulatedFunction extends AbstractTabulatedFunction implements Serializable {
    private static final long serialVersionUID = 1993252187784624486L;
    private final double[] xValues;
    private final double[] yValues;
    private final int count;

    public ArrayTabulatedFunction(double[] xValues, double[] yValues) {
        AbstractTabulatedFunction.checkLengthIsTheSame(xValues, yValues);
        AbstractTabulatedFunction.checkSorted(xValues);
        if (xValues.length < 2) {
            throw new IllegalArgumentException("length of the array is less than 2");
        }
        count = xValues.length;
        this.xValues = Arrays.copyOf(xValues, count);
        this.yValues = Arrays.copyOf(yValues, count);
    }

    public ArrayTabulatedFunction(MathFunction source, double xFrom, double xTo, int count) {
        if (count < 2) {
            throw new IllegalArgumentException("length of the array is less than 2");
        }
        this.count = count;
        xValues = new double[count];
        yValues = new double[count];
        xValues[0] = xFrom;
        yValues[count - 1] = source.apply(xTo);
        for (int i = 0; i != count - 1; i++) {
            xValues[i + 1] = xValues[i] + (xTo - xFrom) / (count - 1);
            yValues[i] = source.apply(xValues[i]);
        }
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public double getX(int index) {
        return xValues[index];
    }

    @Override
    public double getY(int index) {
        return yValues[index];
    }

    @Override
    public void setY(int index, double value) {
        yValues[index] = value;
    }

    @Override
    public int indexOfX(double x) {
        for (int i = 0; i < count; i++) {
            if (xValues[i] == x) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int indexOfY(double y) {
        for (int i = 0; i < count; i++) {
            if (yValues[i] == y) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public double leftBound() {
        return xValues[0];
    }

    @Override
    public double rightBound() {
        return xValues[count - 1];
    }

    @Override
    public int floorIndexOfX(double x) {
        if (x < leftBound()) {
            throw new IllegalArgumentException("x < leftBound");
        }

        if (x - rightBound() > 0) {
            return count;
        }
        for (int i = 1; i != count; i++) {
            if (x - xValues[i] <= 0) {
                return i - 1;
            }
        }
        return -1;
    }

    @Override
    public double extrapolateLeft(double x) {

        return interpolate(x, xValues[0], xValues[1], yValues[0], yValues[1]);
    }

    @Override
    public double extrapolateRight(double x) {

        return interpolate(x, xValues[count - 2], xValues[count - 1], yValues[count - 2], yValues[count - 1]);
    }

    @Override
    public double interpolate(double x, int floorIndex) {

        if (x < xValues[floorIndex] || xValues[floorIndex + 1] < x) {
            throw new InterpolationException("Index is out of bounds");
        }

        if (floorIndex == 0) {
            return extrapolateLeft(x);
        }
        if (floorIndex == count) {
            return extrapolateRight(x);
        }
        return interpolate(x, xValues[floorIndex], xValues[floorIndex + 1], yValues[floorIndex], yValues[floorIndex + 1]);
    }

    public static ArrayTabulatedFunction createTabulatedFunctionDefinedThroughArray(double[] xValues, double[] yValues) {
        return new ArrayTabulatedFunction(xValues, yValues);
    }

    public static ArrayTabulatedFunction createTabulatedFunctionDefinedThroughMathFunction(MathFunction source, double xFrom, double xTo, int count) {
        return new ArrayTabulatedFunction(source, xFrom, xTo, count);
    }


    @Override
    public Iterator<Point> iterator() {
        return new Iterator<>() {
            int i = 0;

            @Override
            public boolean hasNext() {
                return i < count;
            }

            @Override
            public Point next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Point point = new Point(xValues[i], yValues[i]);
                i++;
                return point;
            }
        };
    }
}