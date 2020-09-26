package ru.ssau.tk.chpok.labs.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class CompositeFunctionTest {

    private static final double DELTA = 0.00001;

    @Test
    public void testApply() {
        MathFunction firstFunction = new SqrFunction();
        MathFunction secondFunction = new SqrtFunction();
        CompositeFunction testFunction = new CompositeFunction(firstFunction, secondFunction);
        assertEquals(testFunction.apply(0), 0,DELTA);
        assertEquals(testFunction.apply(-1.0), 1.0,DELTA);
        assertNotEquals(testFunction.apply(2.0), 69,DELTA);
        assertNotEquals(testFunction.apply(12), 144,DELTA);
    }
}