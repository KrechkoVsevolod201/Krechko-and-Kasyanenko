package ru.ssau.tk.chpok.labs.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class SqrtFunctionTest {

    @Test
    public void testApply() {
        MathFunction testFunction = new SqrtFunction();
        assertEquals(testFunction.apply(1.0), 1,0);
        assertNotEquals(testFunction.apply(5.0), 1,0);
        assertEquals(testFunction.apply(4.0), 2.0,0);
        assertEquals(testFunction.apply(144), 12,0);
    }
}