package ru.ssau.tk.chpok.labs.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class CompositeFunctionTest {

    @Test
    public void testApply() {
        MathFunction firstFunction = new IdentityFunction();
        MathFunction secondFunction = new IdentityFunction();
        CompositeFunction testFunction = new CompositeFunction(firstFunction, secondFunction);
        assertEquals(testFunction.apply(0), 0,0);
        assertEquals(testFunction.apply(0.785), 1.0,0);
        assertNotEquals(testFunction.apply(2.0), 69,0);
        assertNotEquals(testFunction.apply(12), 144,0);
    }
}