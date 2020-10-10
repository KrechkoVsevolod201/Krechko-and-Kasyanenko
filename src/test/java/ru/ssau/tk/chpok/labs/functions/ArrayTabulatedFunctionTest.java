package ru.ssau.tk.chpok.labs.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ArrayTabulatedFunctionTest {
    private final double ACCURACY = 0.0001;
    private final MathFunction sqr = new SqrFunction();
    private final MathFunction IndetityFunction = new IdentityFunction();
    private ArrayTabulatedFunction function(){return new ArrayTabulatedFunction(sqr, 2, 9, 8);}

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
        assertEquals(function().getX(1), 3, ACCURACY);
        assertEquals(function().getX(2), 4, ACCURACY);
    }

    @Test
    public void testGetY() {
        assertEquals(function().getY(0),4);
        assertEquals(function().getY(1), 9);
        assertEquals(function().getY(2), 16, ACCURACY);
    }

    @Test
    public void testSetY() {
        function().setY(0, 4);
        function().setY(1, 9);
        function().setY(2,16 );
        assertEquals(function().getY(0), 4, ACCURACY);
        assertEquals(function().getY(1), 9, ACCURACY);
        assertEquals(function().getY(2), 16 );
    }

    @Test
    public void testIndexOfX() {
        assertEquals(function().indexOfX(2),0);
        assertEquals(function().indexOfX(3), 1);
        assertEquals(function().indexOfX(4), 2);

    }

    @Test
    public void testIndexOfY() {
        assertEquals(function().indexOfY(4), 0);
        assertEquals(function().indexOfY(9), 1);
        assertEquals(function().indexOfY(16), 2);

    }

    @Test
    public void testLeftBound() {

        assertEquals(function().leftBound(), 2, ACCURACY);
    }

    @Test
    public void testRightBound() {
        final ArrayTabulatedFunction testFunction = new ArrayTabulatedFunction(sqr,2, 4, 3);
        final ArrayTabulatedFunction anotherFunction = new ArrayTabulatedFunction(IndetityFunction, 3, 4, 1);
        assertEquals(anotherFunction.rightBound(), 3, ACCURACY);
        assertEquals(function().rightBound(), 9, ACCURACY);
    }

    @Test
    public void testFloorIndexOfX() {
        final ArrayTabulatedFunction testFunction = new ArrayTabulatedFunction(sqr, 2, 4, 3);
        assertEquals(testFunction.floorIndexOfX(2), 0);
        assertEquals(testFunction.floorIndexOfX(3), 0);
        assertEquals(testFunction.floorIndexOfX(4), 1);
        assertEquals(function().floorIndexOfX(2), 0);
        assertEquals(function().floorIndexOfX(3), 0);
    }




    @Test
    public void testExtrapolateLeft() {
        final ArrayTabulatedFunction testFunction = new ArrayTabulatedFunction(IndetityFunction, 2, 5, 4);
        final ArrayTabulatedFunction anotherFunction = new ArrayTabulatedFunction(sqr, 2, 6, 5);
        assertEquals(function().extrapolateLeft(3), 9, ACCURACY);
        assertEquals(testFunction.extrapolateLeft(4), 4, ACCURACY);
        assertEquals(anotherFunction.extrapolateLeft(6), 24, ACCURACY);
    }

    @Test
    public void testExtrapolateRight() {
        final ArrayTabulatedFunction testFunction = new ArrayTabulatedFunction(IndetityFunction, 5, 8, 4);
        final ArrayTabulatedFunction anotherFunction = new ArrayTabulatedFunction(sqr, 5, 7, 3);
        assertEquals(function().extrapolateRight(4), -4, ACCURACY);
        assertEquals(testFunction.extrapolateRight(7), 7, ACCURACY);
        assertEquals(anotherFunction.extrapolateRight(6), 36, ACCURACY);
    }

    @Test
    public void testInterpolate() {
        final ArrayTabulatedFunction anotherFunction = new ArrayTabulatedFunction(sqr, 7, 9, 3);
        final ArrayTabulatedFunction testFunction = new ArrayTabulatedFunction(sqr, 5, 9, 5);
        assertEquals(anotherFunction.interpolate(7, anotherFunction.floorIndexOfX(7)), 49, ACCURACY);
        assertEquals(testFunction.interpolate(5, testFunction.floorIndexOfX(5)), 25, ACCURACY);
        assertEquals(function().interpolate(3, function().floorIndexOfX( 3)), 9, ACCURACY);
        assertEquals(testFunction.interpolate(6, testFunction.floorIndexOfX(6)), 36, ACCURACY);
    }
}