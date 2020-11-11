package ru.ssau.tk.chpok.labs.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class SinFunctionTest {

    private static final double DELTA = 0.00001;

    @Test
    public void testApply() {
        SinFunction testFunction = new SinFunction();
        assertEquals(testFunction.apply(0), 0, DELTA);
        assertEquals(testFunction.apply(-Math.PI / 4), -Math.sqrt(2) / 2, DELTA);
        assertNotEquals(testFunction.apply(2.0), 1, DELTA);
        assertNotEquals(testFunction.apply(2.0), 4.0, DELTA);
        assertNotEquals(testFunction.apply(12), 144, DELTA);
    }
}