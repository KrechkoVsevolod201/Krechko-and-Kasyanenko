package ru.ssau.tk.chpok.labs.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class UnitFunctionTest {

    private static final double DELTA = 0.00001;

    UnitFunction testFunction = new UnitFunction();
    @Test
    public void testGetConstant(){
        assertEquals(testFunction.apply(0), 1.0,DELTA);
        assertEquals(testFunction.apply(-8876542), 1.0,DELTA);
        assertEquals(testFunction.apply(742), 1.0,DELTA);
    }
    @Test
    public void testApply() {
        assertEquals(testFunction.getConstant(), 1.0, DELTA);
        assertNotEquals(testFunction.getConstant(), 10.0, DELTA);
    }
}