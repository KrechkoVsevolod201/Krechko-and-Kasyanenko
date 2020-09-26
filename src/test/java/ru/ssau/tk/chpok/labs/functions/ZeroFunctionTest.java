package ru.ssau.tk.chpok.labs.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ZeroFunctionTest {

    private static final double DELTA = 0.00001;
    private final ZeroFunction testFunction = new ZeroFunction();

    @Test
    public void testGetConstant() {
        assertEquals(testFunction.apply(0), 0.0, DELTA);
        assertEquals(testFunction.apply(-8876542), 0.0, DELTA);
        assertEquals(testFunction.apply(742), 0.0, DELTA);
    }

    @Test
    public void testApply() {
        assertEquals(testFunction.getConstant(), 0.0, DELTA);
    }

}