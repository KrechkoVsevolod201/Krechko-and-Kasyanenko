package ru.ssau.tk.chpok.labs.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class SinFunctionTest {

    @Test
    public void testApply() {
        SinFunction testFunction = new SinFunction();
        assertEquals(testFunction.apply(0), 0,0);
        assertNotEquals(testFunction.apply(2.0), 1,0);
        assertNotEquals(testFunction.apply(2.0), 4.0,0);
        assertNotEquals(testFunction.apply(12), 144,0);
    }
}