package ru.ssau.tk.chpok.labs.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ArrayTabulatedFunctionTest {
    protected final double[] xArr = new double[]{1d, 2d, 3d};
    protected final double[] yArr = new double[]{1d, 4d, 9d};
    private final double ACCURACY = 0.0001;
    private final MathFunction sqr = new SqrFunction();
    private final MathFunction indetityFunction = new IdentityFunction();

    private ArrayTabulatedFunction function() {

        return new ArrayTabulatedFunction(sqr, 2, 9, 8);
    }

    private ArrayTabulatedFunction function1() {

        return new ArrayTabulatedFunction(xArr, yArr);
    }

    @Test
    public void testArrayTabulatedFunction() {
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> new ArrayTabulatedFunction(xArr, new double[]{1d, 4d}));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> new ArrayTabulatedFunction(new double[]{1d, 4d}, yArr));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> new ArrayTabulatedFunction(xArr, new double[]{}));

        assertThrows(IllegalArgumentException.class, () -> new ArrayTabulatedFunction(new double[]{1d}, new double[]{1d}));
        assertThrows(IllegalArgumentException.class, () -> new ArrayTabulatedFunction(new double[]{}, new double[]{}));
        assertThrows(IllegalArgumentException.class, () -> new ArrayTabulatedFunction(new double[]{2d}, new double[]{0d}));

        assertThrows(IllegalArgumentException.class, () -> new ArrayTabulatedFunction(sqr, 1, 2, 1));
        assertThrows(IllegalArgumentException.class, () -> new ArrayTabulatedFunction(sqr, 0, 0, 0));
        assertThrows(IllegalArgumentException.class, () -> new ArrayTabulatedFunction(sqr, 1, 2, -2));
    }

    @Test
    public void testApply() {
        final ArrayTabulatedFunction testFunction = new ArrayTabulatedFunction(sqr, 2, 6, 5);
        final ArrayTabulatedFunction testFunctionArr = new ArrayTabulatedFunction(xArr, yArr);
        assertEquals(testFunction.apply(1.0), -1, ACCURACY);
        assertNotEquals(testFunction.apply(5.0), 1, ACCURACY);
        assertEquals(testFunction.apply(4.0), 16.0, ACCURACY);
        assertEquals(testFunction.apply(12), 102, ACCURACY);
        assertEquals(testFunctionArr.apply(1.0), 1, ACCURACY);
        assertNotEquals(testFunctionArr.apply(5.0), 1, ACCURACY);
        assertEquals(testFunctionArr.apply(4.0), 14.0, ACCURACY);
        assertEquals(testFunctionArr.apply(12), 54, ACCURACY);
    }

    @Test
    public void testGetCount() {
        final ArrayTabulatedFunction testFunction = new ArrayTabulatedFunction(sqr, 2, 6, 5);
        assertNotEquals(function().getCount(), 0);
        assertNotEquals(testFunction.getCount(), 0);
        assertEquals(function().getCount(), 8);
        assertEquals(testFunction.getCount(), 5);
    }

    @Test
    public void testGetX() {
        assertEquals(function().getX(0), 2);
        assertEquals(function().getX(2), 4);
        assertEquals(function().getX(1), 3, ACCURACY);
        assertEquals(function().getX(2), 4, ACCURACY);
        assertEquals(function1().getX(0), 1);
        assertEquals(function1().getX(1), 2, ACCURACY);
        assertEquals(function1().getX(2), 3, ACCURACY);

        assertThrows(ArrayIndexOutOfBoundsException.class, () -> function().getX(-20));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> function().getX(10));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> function().getX(9));
    }

    @Test
    public void testGetY() {
        assertEquals(function().getY(0), 4);
        assertEquals(function().getY(1), 9);
        assertEquals(function().getY(2), 16, ACCURACY);
        assertEquals(function1().getY(0), 1);
        assertEquals(function1().getY(1), 4);
        assertEquals(function1().getY(2), 9, ACCURACY);

        assertThrows(ArrayIndexOutOfBoundsException.class, () -> function().getY(-40));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> function().getY(40));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> function().getY(15));
    }

    @Test
    public void testSetY() {
        function().setY(0, 4);
        function().setY(1, 9);
        function().setY(2, 16);
        assertEquals(function().getY(0), 4, ACCURACY);
        assertEquals(function().getY(1), 9, ACCURACY);
        assertEquals(function().getY(2), 16);
        function1().setY(0, 1);
        function1().setY(1, 4);
        function1().setY(2, 9);
        assertEquals(function1().getY(0), 1, ACCURACY);
        assertEquals(function1().getY(1), 4, ACCURACY);
        assertEquals(function1().getY(2), 9);
    }

    @Test
    public void testIndexOfX() {
        assertEquals(function().indexOfX(2), 0);
        assertEquals(function().indexOfX(3), 1);
        assertEquals(function().indexOfX(4), 2);
        assertEquals(function1().indexOfX(2), 1);
        assertEquals(function1().indexOfX(3), 2);
        assertEquals(function1().indexOfX(4), -1);
    }

    @Test
    public void testIndexOfY() {
        assertEquals(function().indexOfY(4), 0);
        assertEquals(function().indexOfY(9), 1);
        assertEquals(function().indexOfY(16), 2);
        assertEquals(function1().indexOfY(4), 1);
        assertEquals(function1().indexOfY(9), 2);
        assertEquals(function1().indexOfY(16), -1);
    }

    @Test
    public void testLeftBound() {
        assertEquals(function().leftBound(), 2, ACCURACY);
        assertEquals(function1().leftBound(), 1, ACCURACY);
    }

    @Test
    public void testRightBound() {
        final ArrayTabulatedFunction anotherFunction = new ArrayTabulatedFunction(indetityFunction, 3, 4, 1);
        assertEquals(anotherFunction.rightBound(), 3, ACCURACY);
        assertEquals(function().rightBound(), 9, ACCURACY);
        assertEquals(function1().rightBound(), 3, ACCURACY);

    }

    @Test
    public void testFloorIndexOfX() {
        final ArrayTabulatedFunction testFunction = new ArrayTabulatedFunction(sqr, 2, 4, 3);
        assertEquals(testFunction.floorIndexOfX(2), 0);
        assertEquals(testFunction.floorIndexOfX(3), 0);
        assertEquals(testFunction.floorIndexOfX(4), 1);
        assertEquals(function().floorIndexOfX(2), 0);
        assertEquals(function().floorIndexOfX(3), 0);
        assertEquals(function1().floorIndexOfX(2), 0);
        assertEquals(function1().floorIndexOfX(3), 1);

        assertThrows(IllegalArgumentException.class, () -> testFunction.floorIndexOfX(-25));
        assertThrows(IllegalArgumentException.class, () -> testFunction.floorIndexOfX(0));
        assertThrows(IllegalArgumentException.class, () -> testFunction.floorIndexOfX(Double.NEGATIVE_INFINITY));
    }

    @Test
    public void testExtrapolateLeft() {
        final ArrayTabulatedFunction testFunction = new ArrayTabulatedFunction(indetityFunction, 2, 5, 4);
        final ArrayTabulatedFunction anotherFunction = new ArrayTabulatedFunction(sqr, 2, 6, 5);
        assertEquals(function().extrapolateLeft(3), 9, ACCURACY);
        assertEquals(function1().extrapolateLeft(3), 7, ACCURACY);
        assertEquals(testFunction.extrapolateLeft(4), 4, ACCURACY);
        assertEquals(anotherFunction.extrapolateLeft(6), 24, ACCURACY);
    }

    @Test
    public void testExtrapolateRight() {
        final ArrayTabulatedFunction testFunction = new ArrayTabulatedFunction(indetityFunction, 5, 8, 4);
        final ArrayTabulatedFunction anotherFunction = new ArrayTabulatedFunction(sqr, 5, 7, 3);
        assertEquals(function().extrapolateRight(4), -4, ACCURACY);
        assertEquals(function1().extrapolateRight(4), 14, ACCURACY);
        assertEquals(testFunction.extrapolateRight(7), 7, ACCURACY);
        assertEquals(anotherFunction.extrapolateRight(6), 36, ACCURACY);
    }

    @Test
    public void testInterpolate() {
        final ArrayTabulatedFunction anotherFunction = new ArrayTabulatedFunction(sqr, 7, 9, 3);
        final ArrayTabulatedFunction testFunction = new ArrayTabulatedFunction(sqr, 5, 9, 5);
        assertEquals(anotherFunction.interpolate(7, anotherFunction.floorIndexOfX(7)), 49, ACCURACY);
        assertEquals(testFunction.interpolate(5, testFunction.floorIndexOfX(5)), 25, ACCURACY);
        assertEquals(function().interpolate(3, function().floorIndexOfX(3)), 9, ACCURACY);
        assertEquals(function1().interpolate(3, function1().floorIndexOfX(3)), 9, ACCURACY);
        assertEquals(testFunction.interpolate(6, testFunction.floorIndexOfX(6)), 36, ACCURACY);

        assertThrows(IllegalArgumentException.class, () -> anotherFunction.interpolate(15, anotherFunction.floorIndexOfX(1)));
        assertThrows(IllegalArgumentException.class, () -> anotherFunction.interpolate(10, anotherFunction.floorIndexOfX(7)));
        assertThrows(IllegalArgumentException.class, () -> function().interpolate(0, function().floorIndexOfX(0)));
    }
}

