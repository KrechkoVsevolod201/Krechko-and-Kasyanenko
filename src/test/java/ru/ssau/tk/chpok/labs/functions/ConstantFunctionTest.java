package ru.ssau.tk.chpok.labs.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ConstantFunctionTest {

    @Test
    public void testGetConstant() {
    }

    @Test
    public void testApply() {
        ConstantFunction testFunction = new ConstantFunction(69);
        assertEquals(testFunction.apply(0), 69,0);
        assertEquals(testFunction.apply(0.785), 69,0);
        assertNotEquals(testFunction.apply(2.0), 96,0);
        assertNotEquals(testFunction.apply(12), 169,0);
    }
}