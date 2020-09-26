package ru.ssau.tk.chpok.labs.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class SqrFunctionTest {

    @Test
    public void testApply() {
        MathFunction testFunction = new SqrFunction();
        assertEquals(testFunction.apply(1.0), 1,0);
        assertNotEquals(testFunction.apply(2.0), 1,0);
        assertEquals(testFunction.apply(2.0), 4.0,0);
        assertEquals(testFunction.apply(12), 144,0);
    }
}