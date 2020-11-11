package ru.ssau.tk.chpok.labs.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class SqrtFunctionTest {

    private static final double DELTA = 0.00001;

    @Test
    public void testApply() {
        MathFunction testFunction = new SqrtFunction();
        assertEquals(testFunction.apply(1.0), 1, DELTA);
        assertNotEquals(testFunction.apply(5.0), 1, DELTA);
        assertEquals(testFunction.apply(4.0), 2.0, DELTA);
        assertEquals(testFunction.apply(144), 12, DELTA);
    }
}