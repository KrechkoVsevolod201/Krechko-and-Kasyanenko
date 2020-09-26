package ru.ssau.tk.chpok.labs.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class SqrFunctionTest {

    private static final double DELTA = 0.00001;

    @Test
    public void testApply() {
        MathFunction testFunction = new SqrFunction();
        assertEquals(testFunction.apply(1.0), 1,DELTA);
        assertNotEquals(testFunction.apply(2.0), 1,DELTA);
        assertEquals(testFunction.apply(-2.0), 4.0,DELTA);
        assertEquals(testFunction.apply(12), 144,DELTA);
    }
}