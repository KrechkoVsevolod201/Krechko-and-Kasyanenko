package ru.ssau.tk.chpok.labs.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

class AbstractTabulatedFunctionTest {
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
        assertEquals(newMock.getY(0),5,delta);
        assertEquals(newMock.getY(1),5,delta);
        assertEquals(newMock.getY(2),5,delta);
    }

    @Test
    public void testIndexOfX() {
        assertEquals(newMock.indexOfX(0),0,delta);
        assertEquals(newMock.indexOfX(1),0,delta);
        assertEquals(newMock.indexOfX(2),0,delta);
    }

    @Test
    public void testIndexOfY() {
        assertEquals(newMock.indexOfY(0),0,delta);
        assertEquals(newMock.indexOfY(1),0,delta);
        assertEquals(newMock.indexOfY(2),0,delta);
    }

    @Test
    public void testLeftBound() {
        assertEquals(newMock.leftBound(),2,delta);
    }

    @Test
    public void testRightBound() {
        assertEquals(newMock.rightBound(),4,delta);
    }

    @Test
    public void testGetCount() {
        assertEquals(newMock.getCount(),2,delta);
    }

    @Test
    public void testFloorIndexOfX() {
        assertEquals(newMock.floorIndexOfX(2),0,delta);
    }

    @Test
    public void testExtrapolateLeft() {
        assertEquals(newMock.extrapolateLeft(3),6,delta);
    }

    @Test
    public void testExtrapolateRight() {
        assertEquals(newMock.extrapolateLeft(3),6,delta);
    }

    @Test
    public void testInterpolate() {
        assertEquals(newMock.extrapolateLeft(3),6,delta);
    }

    @Test
    public void testTestInterpolate() {
    }

}