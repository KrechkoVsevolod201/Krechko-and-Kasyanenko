package ru.ssau.tk.chpok.labs.functions.factory;

import org.testng.annotations.Test;
import ru.ssau.tk.chpok.labs.functions.ArrayTabulatedFunction;
import ru.ssau.tk.chpok.labs.functions.LinkedListTabulatedFunction;

import static org.testng.Assert.*;

public class LinkedListTabulatedFunctionFactoryTest {
    private final double[] valuesX = new double[]{1, 3, 5, 7, 9};
    private final double[] valuesY = new double[]{1, 3, 5, 7, 9};
    private final double[] valuesX1 = new double[]{0, 0, 0};
    private final double[] valuesY1 = new double[]{0, 0, 0};
    private final static TabulatedFunctionFactory factory = new LinkedListTabulatedFunctionFactory();
    @Test
    public void testCreate() {
        assertTrue(factory.create(valuesX, valuesY) instanceof LinkedListTabulatedFunction);
        assertTrue(factory.create(valuesX1, valuesY1) instanceof LinkedListTabulatedFunction);
    }
}