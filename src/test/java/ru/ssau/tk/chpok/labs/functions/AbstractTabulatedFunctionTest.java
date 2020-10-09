package ru.ssau.tk.chpok.labs.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class AbstractTabulatedFunctionTest {
    MockTabulatedFunction newMock = new MockTabulatedFunction();
    private static double delta = 0.001;
    //expression of the extrapolation is 3+x
    @Test
    public void testApply() {
        assertEquals(newMock.apply(1), 4, delta);//less than leftBound
        assertEquals(newMock.apply(5), 8, delta);//larger than RightBound
        assertEquals(newMock.apply(6), 9, delta);
    }

    @Test
    public void testGetX() {
        assertEquals(newMock.getX(0),2,delta);
        assertEquals(newMock.getX(1),2,delta);
        assertEquals(newMock.getX(2),2,delta);
    }


    @Test
    public void testGetY() {
    }

    @Test
    public void testSetY() {
    }

    @Test
    public void testIndexOfX() {
    }

    @Test
    public void testIndexOfY() {
    }

    @Test
    public void testLeftBound() {
    }

    @Test
    public void testRightBound() {
    }

    @Test
    public void testGetCount() {
    }

    @Test
    public void testFloorIndexOfX() {
    }

    @Test
    public void testExtrapolateLeft() {
    }

    @Test
    public void testExtrapolateRight() {
    }

    @Test
    public void testInterpolate() {
    }

    @Test
    public void testTestInterpolate() {
    }

}