package ru.ssau.tk.chpok.labs.functions;

import org.testng.annotations.Test;
import ru.ssau.tk.chpok.labs.exceptions.ArrayIsNotSortedException;
import ru.ssau.tk.chpok.labs.exceptions.DifferentLengthOfArraysException;
import ru.ssau.tk.chpok.labs.exceptions.InterpolationException;

import java.util.Iterator;
import java.util.NoSuchElementException;
import static org.testng.Assert.*;

public class LinkedListTabulatedFunctionTest {

    protected final double[] xArr = new double[]{1d, 6d, 7d};
    protected final double[] yArr = new double[]{1d, 36d, 49d};

    protected final double[] funArr = new double[]{Double.NEGATIVE_INFINITY, Double.NaN, Double.POSITIVE_INFINITY, };
    protected final double[] ePiZero = new double[]{0d, Math.E, Math.PI};
    private final MathFunction sqrtFunc = new SqrtFunction();
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
    public void testConstructor() {
        assertThrows(IllegalArgumentException.class, () -> LinkedListTabulatedFunction.createTabulatedFunctionDefinedThroughList(new double[]{1}, new double[]{1}));
        assertThrows(IllegalArgumentException.class, () -> LinkedListTabulatedFunction.createTabulatedFunctionDefinedThroughList(new double[]{}, new double[]{}));
        assertThrows(DifferentLengthOfArraysException.class, () -> LinkedListTabulatedFunction.createTabulatedFunctionDefinedThroughList(new double[]{1, 4}, new double[]{1}));
        assertThrows(DifferentLengthOfArraysException.class, () -> LinkedListTabulatedFunction.createTabulatedFunctionDefinedThroughList(new double[]{1, 5, 6}, new double[]{1, 5}));
        assertThrows(DifferentLengthOfArraysException.class, () -> LinkedListTabulatedFunction.createTabulatedFunctionDefinedThroughList(new double[]{1, 2, 3}, new double[]{1, 1, 1, 2}));
        assertThrows(ArrayIsNotSortedException.class, () -> LinkedListTabulatedFunction.createTabulatedFunctionDefinedThroughList(new double[]{69, 5, 2}, new double[]{1, 5, 2}));
        assertThrows(ArrayIsNotSortedException.class, () -> LinkedListTabulatedFunction.createTabulatedFunctionDefinedThroughList(new double[]{10, 9, 8}, new double[]{1, 5, 10}));
        assertThrows(IllegalArgumentException.class, () -> LinkedListTabulatedFunction.createTabulatedFunctionDefinedThroughMathFunction(sqrtFunc, 4, -1, 0));
        assertThrows(IllegalArgumentException.class, () -> LinkedListTabulatedFunction.createTabulatedFunctionDefinedThroughMathFunction(sqrtFunc, -10, 24, -33));
        assertThrows(IllegalArgumentException.class, () -> LinkedListTabulatedFunction.createTabulatedFunctionDefinedThroughMathFunction(sqrtFunc, 5, 6, 1));
        assertThrows(IllegalArgumentException.class, () -> LinkedListTabulatedFunction.createTabulatedFunctionDefinedThroughMathFunction(sqrtFunc, 5, 5, -1));
    }
    @Test
    public void testApply() {
        final LinkedListTabulatedFunction testFunctionArr = new LinkedListTabulatedFunction(xArr, yArr);
        final LinkedListTabulatedFunction testFunctionFun = new LinkedListTabulatedFunction(funArr, funArr);
        final LinkedListTabulatedFunction testFunctionEPiZero = new LinkedListTabulatedFunction(ePiZero, ePiZero);
        assertEquals(testFunctionArr.apply(1.0), 1, delta);
        assertEquals(testFunctionArr.apply(7.0), 49.0, delta);
        assertEquals(testFunctionArr.apply(6.0), 36.0, delta);
        assertEquals(testFunctionArr.apply(12), 114, delta);
        assertEquals(testFunctionFun.apply(1.0), Double.NaN, delta);
        assertNotEquals(testFunctionFun.apply(5.0), 8, delta);
        assertEquals(testFunctionFun.apply(4.0), Double.NaN, delta);
        assertEquals(testFunctionFun.apply(12), Double.NaN, delta);
        assertEquals(testFunctionEPiZero.apply(0.0), 0, delta);
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

        assertEquals(testListFun().getY(1), Double.NaN, delta);
        assertEquals(testList().getY(2), 49);
        assertEquals(testListEPiZero().getY(3), 0);
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
        assertEquals(testListFun().leftBound(), Double.NEGATIVE_INFINITY, delta);
        assertEquals(testListEPiZero().leftBound(), 0, delta);
    }

    @Test
    public void testRightBound() {
        assertEquals(testList().rightBound(), 7, delta);
        assertEquals(testListFun().rightBound(), Double.POSITIVE_INFINITY, delta);
        assertEquals(testListEPiZero().rightBound(), Math.PI, delta);
    }

    @Test
    public void testIndexOfX() {
        assertEquals(testList().indexOfX(2), -1, delta);
        assertEquals(testListEPiZero().indexOfX(Math.PI), 2, delta);
        assertEquals(testListFun().indexOfX(Double.NaN), 1, delta);
    }

    @Test
    public void testIndexOfY() {
        assertEquals(testList().indexOfY(4), -1, delta);
        assertEquals(testListEPiZero().indexOfY(Math.PI), 2, delta);
        assertEquals(testListFun().indexOfY(Double.NaN), 1, delta);
    }

    @Test
    public void testFloorIndexOfX() {
        assertEquals(testList().floorIndexOfX(2), 1, delta);
        assertEquals(testListFun().floorIndexOfX(1), 1, delta);
        assertEquals(testListEPiZero().floorIndexOfX(3), 2, delta);
        assertEquals(testList().floorIndexOfX(Double.POSITIVE_INFINITY), 3, delta);
        assertEquals(testListFun().floorIndexOfX(Double.POSITIVE_INFINITY), 1, delta);
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
        assertThrows(InterpolationException.class, () -> testList().interpolate(10, testList().floorIndexOfX(7)));
        assertEquals(testList().interpolate(1, 0), 1, delta);
    }

    @Test
    public void testIteratorThroughWhile() {
        LinkedListTabulatedFunction definedThroughList = LinkedListTabulatedFunction.createTabulatedFunctionDefinedThroughList(xArr, yArr);
        Iterator<Point> iterator = definedThroughList.iterator();
        int j = 0;
        while (iterator.hasNext()) {
            Point point = iterator.next();
            assertEquals(point.x, definedThroughList.getX(j++));
        }
        assertEquals(j, 3);
        assertThrows(NoSuchElementException.class, iterator::next);
        j = 0;
        for (Point point : definedThroughList) {
            assertEquals(point.x, definedThroughList.getX(j++));
        }
        assertEquals(j, 3);
        assertThrows(NoSuchElementException.class, iterator::next);
    }

}
