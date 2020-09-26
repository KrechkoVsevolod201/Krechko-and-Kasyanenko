package ru.ssau.tk.chpok.labs.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class UnitFunctionTest {
    UnitFunction testFunction = new UnitFunction();
    @Test
    public void testGetConstant(){
        assertEquals(testFunction.apply(0), 1.0,0);
        assertEquals(testFunction.apply(-8876542), 1.0,0);
        assertEquals(testFunction.apply(742), 1.0,0);
    }
    @Test
    public void testApply() {
        assertEquals(testFunction.getConstant(), 1.0, 0);
        assertNotEquals(testFunction.getConstant(), 10.0, 0);

    }
}