package ru.ssau.tk.chpok.labs.functions;

import org.testng.annotations.Test;
import ru.ssau.tk.chpok.labs.exceptions.ArrayIsNotSortedException;
import ru.ssau.tk.chpok.labs.exceptions.DifferentLengthOfArraysException;
import ru.ssau.tk.chpok.labs.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.chpok.labs.functions.factory.LinkedListTabulatedFunctionFactory;
import ru.ssau.tk.chpok.labs.functions.factory.TabulatedFunctionFactory;

import static org.testng.Assert.*;

public class AbstractTabulatedFunctionTest {
    MockTabulatedFunction newMock = new MockTabulatedFunction();
    private static final double delta = 0.001;
    protected final double[] xArr = new double[]{1d, 6d, 7d};
    protected final double[] yArr = new double[]{2d, 6d, 7d};
    protected final double[] xArrAnotherWrong = new double[]{81d, 36d, 49d};
    protected final double[] xArrLong = new double[]{-1d, 0d, 6d, 7d, 8d, 9d};
    protected final double[] xArrWrong = new double[]{1d, 36d, 64d, 49d, 81d};

    private ArrayTabulatedFunction function() {

        return new ArrayTabulatedFunction(xArr, xArrLong);
    }

    @Test
    public void testApply() {
        assertEquals(newMock.apply(1), 4, delta);//less than leftBound
        assertEquals(newMock.apply(5), 8, delta);//larger than RightBound
        assertEquals(newMock.apply(6), 9, delta);
    }

    @Test
    public void testGetX() {
        assertEquals(newMock.getX(0), 2, delta);
        assertEquals(newMock.getX(1), 2, delta);
        assertEquals(newMock.getX(2), 2, delta);
    }


    @Test
    public void testGetY() {
        assertEquals(newMock.getY(0), 5, delta);
        assertEquals(newMock.getY(1), 5, delta);
        assertEquals(newMock.getY(2), 5, delta);
    }

    @Test
    public void testIndexOfX() {
        assertEquals(newMock.indexOfX(0), 0, delta);
        assertEquals(newMock.indexOfX(1), 0, delta);
        assertEquals(newMock.indexOfX(2), 0, delta);
    }

    @Test
    public void testIndexOfY() {
        assertEquals(newMock.indexOfY(0), 0, delta);
        assertEquals(newMock.indexOfY(1), 0, delta);
        assertEquals(newMock.indexOfY(2), 0, delta);
    }

    @Test
    public void testLeftBound() {
        assertEquals(newMock.leftBound(), 2, delta);
    }

    @Test
    public void testRightBound() {
        assertEquals(newMock.rightBound(), 4, delta);
    }

    @Test
    public void testGetCount() {
        assertEquals(newMock.getCount(), 2, delta);
    }

    @Test
    public void testFloorIndexOfX() {
        assertEquals(newMock.floorIndexOfX(-10), 0, delta);
        assertEquals(newMock.floorIndexOfX(2), 0, delta);
        assertEquals(newMock.floorIndexOfX(10), 0, delta);
        assertEquals(newMock.floorIndexOfX(3), 0, delta);
    }

    @Test
    public void testExtrapolateLeft() {
        assertEquals(newMock.extrapolateLeft(3), 6, delta);
    }

    @Test
    public void testExtrapolateRight() {
        assertEquals(newMock.extrapolateLeft(3), 6, delta);
    }

    @Test
    public void testInterpolate() {
        assertEquals(newMock.extrapolateLeft(3), 6, delta);
    }

    @Test
    public void testTestInterpolate() {

    }

    @Test
    public void testCheckLengthIsTheSame() {
        assertThrows(DifferentLengthOfArraysException.class, () -> AbstractTabulatedFunction.checkLengthIsTheSame(xArr, xArrLong));
        assertThrows(DifferentLengthOfArraysException.class, () -> AbstractTabulatedFunction.checkLengthIsTheSame(xArrLong, xArr));
        AbstractTabulatedFunction.checkLengthIsTheSame(xArr, yArr);
    }

    @Test
    public void testCheckSorted() {
        assertThrows(ArrayIsNotSortedException.class, () -> AbstractTabulatedFunction.checkSorted(xArrWrong));
        assertThrows(ArrayIsNotSortedException.class, () -> AbstractTabulatedFunction.checkSorted(xArrAnotherWrong));
        AbstractTabulatedFunction.checkSorted(xArr);
    }

    @Test
    public void testToString() {
        TabulatedFunctionFactory listFactory = new LinkedListTabulatedFunctionFactory();
        TabulatedFunctionFactory arrayFactory = new ArrayTabulatedFunctionFactory();
        TabulatedFunction listFunction = listFactory.create(new double[]{-1.0, 0.0, 1.0, 2.0}, new double[]{-5.0, 0.0, 5.0, 12.0});
        TabulatedFunction arrayFunction = arrayFactory.create(new double[]{-7.0, -2.0, 1.0}, new double[]{-49.0, -8.0, 2.0});
        assertEquals(listFunction.toString(), "LinkedListTabulatedFunction; size = 4\n[-1.0, -5.0]\n[0.0, 0.0]\n[1.0, 5.0]\n[2.0, 12.0]");
        assertEquals(arrayFunction.toString(), "ArrayTabulatedFunction; size = 3\n[-7.0, -49.0]\n[-2.0, -8.0]\n[1.0, 2.0]");
    }

}
