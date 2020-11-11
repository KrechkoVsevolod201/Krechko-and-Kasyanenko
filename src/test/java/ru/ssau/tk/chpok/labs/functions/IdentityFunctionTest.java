package ru.ssau.tk.chpok.labs.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class IdentityFunctionTest {

    private static final double DELTA = 0.00001;

    @Test
    public void testApply() {
        MathFunction testFunction = new IdentityFunction();
        assertEquals(testFunction.apply(1.0), 1, DELTA);
        assertNotEquals(testFunction.apply(2.0), 1, DELTA);
        assertEquals(testFunction.apply(128.365), 128.365, DELTA);
        assertEquals(testFunction.apply(-666.999), -666.999, DELTA);
    }
}