package ru.ssau.tk.chpok.labs.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class LinkedListTabulatedFunctionTest {

    protected final double[] xArr = new double[]{1d, 6d, 7d};
    protected final double[] yArr = new double[]{1d, 36d, 49d};

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
        final LinkedListTabulatedFunction testFunctionArr = new LinkedListTabulatedFunction(xArr, yArr);
        final LinkedListTabulatedFunction testFunctionFun = new LinkedListTabulatedFunction(funArr, funArr);
        final LinkedListTabulatedFunction testFunctionEPiZero = new LinkedListTabulatedFunction(ePiZero, ePiZero);
        assertEquals(testFunctionArr.apply(1.0), 1, delta);
        assertNotEquals(testFunctionArr.apply(5.0), 1, delta);
        assertEquals(testFunctionArr.apply(4.0), 10.0, delta);
        assertEquals(testFunctionArr.apply(12), 114, delta);
        assertEquals(testFunctionFun.apply(1.0), Double.NaN, delta);
        assertNotEquals(testFunctionFun.apply(5.0), 8, delta);
        assertEquals(testFunctionFun.apply(4.0), Double.NaN, delta);
        assertEquals(testFunctionFun.apply(12), Double.NaN, delta);
        assertEquals(testFunctionEPiZero.apply(1.0), 1, delta);
        assertNotEquals(testFunctionEPiZero.apply(5.0), 1, delta);
        assertEquals(testFunctionEPiZero.apply(4.0), 4, delta);
        assertEquals(testFunctionEPiZero.apply(12), 12, delta);
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

        assertEquals(testListFun().getY(1), Double.POSITIVE_INFINITY, delta);
        assertEquals(testList().getY(2), 49);
        assertEquals(testListEPiZero().getY(3), Math.E);
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
        assertEquals(testList().rightBound(), 7, delta);
        assertEquals(testListFun().rightBound(), Double.NEGATIVE_INFINITY, delta);
        assertEquals(testListEPiZero().rightBound(), 0, delta);
    }

    @Test
    public void testIndexOfX() {
        assertEquals(testList().indexOfX(2), -1, delta);
        assertEquals(testListEPiZero().indexOfX(Math.PI), 1, delta);
        assertEquals(testListFun().indexOfX(Double.NaN), 0, delta);
    }

    @Test
    public void testIndexOfY() {
        assertEquals(testList().indexOfY(4), -1, delta);
        assertEquals(testListEPiZero().indexOfY(Math.PI), 1, delta);
        assertEquals(testListFun().indexOfY(Double.NaN), 0, delta);
    }

    @Test
    public void testFloorIndexOfX() {
        assertEquals(testList().floorIndexOfX(2), 1, delta);
        assertEquals(testListFun().floorIndexOfX(1), 0, delta);
        assertEquals(testListEPiZero().floorIndexOfX(3), 1, delta);
        assertEquals(testList().floorIndexOfX(Double.POSITIVE_INFINITY), 3, delta);
        assertEquals(testListFun().floorIndexOfX(Double.POSITIVE_INFINITY), 0, delta);
        assertEquals(testListEPiZero().floorIndexOfX(Double.POSITIVE_INFINITY), 3, delta);
        assertEquals(testList().floorIndexOfX(Double.NEGATIVE_INFINITY), 0, delta);
        assertEquals(testListFun().floorIndexOfX(Double.NEGATIVE_INFINITY), 0, delta);
        assertEquals(testListEPiZero().floorIndexOfX(Double.NEGATIVE_INFINITY), 0, delta);
    }

    @Test
    public void testExtrapolateLeft() {
        assertEquals(testList().extrapolateLeft(2), 8, delta);
        assertEquals(testList().extrapolateLeft(1), 1, delta);
        assertEquals(testList().extrapolateLeft(-1), -13, delta);
    }

    @Test
    public void testExtrapolateRight() {
        assertEquals(testList().extrapolateRight(1), -29, delta);
        assertEquals(testList().extrapolateRight(-1), -55, delta);
        assertEquals(testList().extrapolateRight(5), 23, delta);
    }

    @Test
    public void testInterpolate() {
        assertEquals(testList().interpolate(5, 0), 29, delta);
        assertEquals(testList().interpolate(-1, 0), -13, delta);
        assertEquals(testList().interpolate(1, 0), 1, delta);
    }
}