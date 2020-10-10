package ru.ssau.tk.chpok.labs.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class LinkedListTabulatedFunctionTest {

    protected final double[] xArr = new double[]{1d, 2d, 3d};
    protected final double[] yArr = new double[]{1d, 4d, 9d};

    protected final double[] funArr = new double[]{Double.NaN, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY};
    protected final double[] ePiZero = new double[]{Math.E, Math.PI, 0d};

    private static final double delta = 0.001;

    public LinkedListTabulatedFunction testList() {
        return new LinkedListTabulatedFunction(xArr, yArr);
    }

    public LinkedListTabulatedFunction testListFun() {
        return new LinkedListTabulatedFunction(funArr, funArr);
    }

    public LinkedListTabulatedFunction testListEPiZero() {
        return new LinkedListTabulatedFunction(ePiZero, ePiZero);
    }

    @Test
    public void testApply() {
        final LinkedListTabulatedFunction testFunction = new LinkedListTabulatedFunction(xArr, yArr);
        assertEquals(testFunction.apply(1.0), 1, delta);
        assertNotEquals(testFunction.apply(5.0), 1, delta);
        assertEquals(testFunction.apply(4.0), 14.0, delta);
        assertEquals(testFunction.apply(12), 54, delta);
    }

    @Test
    public void testApply1() {
        final LinkedListTabulatedFunction testFunction = new LinkedListTabulatedFunction(funArr, funArr);
        assertEquals(testFunction.apply(1.0), Double.NaN, delta);
        assertNotEquals(testFunction.apply(5.0), 8, delta);
        assertEquals(testFunction.apply(4.0), Double.NaN, delta);
        assertEquals(testFunction.apply(12), Double.NaN, delta);
    }

    @Test
    public void testApply2() {
        final LinkedListTabulatedFunction testFunction = new LinkedListTabulatedFunction(ePiZero, ePiZero);
        assertEquals(testFunction.apply(1.0), 1, delta);
        assertNotEquals(testFunction.apply(5.0), 1, delta);
        assertEquals(testFunction.apply(4.0), 4, delta);
        assertEquals(testFunction.apply(12), 12, delta);
    }

    @Test
    public void testGetX() {
        for (int i = 0; i < 3; i++) {
            assertEquals(testList().getX(i), xArr[i], delta);
            assertEquals(testListFun().getX(i), funArr[i]);
            assertEquals(testListEPiZero().getX(i), ePiZero[i], delta);
        }
    }

    @Test
    public void testGetY() {
        for (int i = 0; i < 3; i++) {
            assertEquals(testList().getY(i), yArr[i], delta);
            assertEquals(testListFun().getY(i), funArr[i]);
            assertEquals(testListEPiZero().getY(i), ePiZero[i], delta);
        }
    }

    @Test
    public void testSetY() {
        testListFun().setY(1, 1);
        testList().setY(2, Double.POSITIVE_INFINITY);
        testListEPiZero().setY(3, Double.NaN);

        assertEquals(testListFun().getY(1), 1, delta);
        assertEquals(testList().getY(2), Double.POSITIVE_INFINITY);
        assertEquals(testListEPiZero().getY(3), Double.NaN);
    }

    @Test
    public void testGetCount() {
        assertEquals(testList().getCount(), 3, delta);
        assertEquals(testListFun().getCount(), 3, delta);
        assertEquals(testListEPiZero().getCount(), 3, delta);
    }

    @Test
    public void testLeftBound() {
        assertEquals(testList().leftBound(), 1, delta);
        assertEquals(testListFun().leftBound(), Double.NaN, delta);
        assertEquals(testListEPiZero().leftBound(), Math.E, delta);
    }

    @Test
    public void testRightBound() {
        assertEquals(testList().rightBound(), 3, delta);
        assertEquals(testListFun().rightBound(), Double.NEGATIVE_INFINITY, delta);
        assertEquals(testListEPiZero().rightBound(), 0, delta);
    }

    @Test
    public void testIndexOfX() {
        assertEquals(testList().indexOfX(2), 1, delta);
        assertEquals(testListEPiZero().indexOfX(Math.PI), 1, delta);
        assertEquals(testListFun().indexOfX(Double.NaN), 0, delta);
    }

    @Test
    public void testIndexOfY() {
        assertEquals(testList().indexOfY(4), 1, delta);
        assertEquals(testListEPiZero().indexOfY(Math.PI), 1, delta);
        assertEquals(testListFun().indexOfY(Double.NaN), 0, delta);
    }

    @Test
    public void testFloorIndexOfX() {
        assertEquals(testList().floorIndexOfX(2), 1, delta);
        assertEquals(testListFun().floorIndexOfX(1), 0, delta);
        assertEquals(testListEPiZero().floorIndexOfX(3), 1, delta);
    }

    @Test
    public void testExtrapolateLeft() {
        assertEquals(testList().extrapolateLeft(1.9), 3.7, delta);
    }

    @Test
    public void testExtrapolateRight() {
        assertEquals(testList().extrapolateRight(1.9), 3.5, delta);
    }

    @Test
    public void testInterpolate() {
        assertEquals(testList().interpolate(1.9, 0), 3.7, delta);
    }
}