package ru.ssau.tk.chpok.labs.operations;

import org.testng.annotations.Test;
import ru.ssau.tk.chpok.labs.functions.ArrayTabulatedFunction;
import ru.ssau.tk.chpok.labs.functions.LinkedListTabulatedFunction;
import ru.ssau.tk.chpok.labs.functions.TabulatedFunction;
import ru.ssau.tk.chpok.labs.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.chpok.labs.functions.factory.LinkedListTabulatedFunctionFactory;
import ru.ssau.tk.chpok.labs.functions.factory.TabulatedFunctionFactory;

import static org.testng.Assert.*;

public class TabulatedDifferentialOperatorTest {
    private static final TabulatedFunctionFactory arrayTabulatedFunctionFactory = new ArrayTabulatedFunctionFactory();
    private static final TabulatedFunctionFactory linkedListTabulatedFunctionFactory = new LinkedListTabulatedFunctionFactory();
    private static final double[] xValues = new double[]{-3., 5., 7., 9.};
    private static final double[] yValues = new double[]{9., 25., 49., 81.};
    private static final double[] derivedYValues = new double[]{2., 12., 16., 16., 3., 5., 5.};
    private static final double[] newXValues = new double[]{4., 9., 16., 25., 36.};
    private static final double[] newYValues = new double[]{8., 27., 64., 125., 216.};
    private static final double[] newDerivedYValues = new double[]{3.8, 5.28, 6.77, 8.27, 8.27};
    private final static TabulatedFunction firstFunction = arrayTabulatedFunctionFactory.create(xValues, yValues);
    private final static TabulatedFunction secondFunction = linkedListTabulatedFunctionFactory.create(xValues, yValues);
    private final static TabulatedFunction newFirstFunction = arrayTabulatedFunctionFactory.create(newXValues, newYValues);
    private final static TabulatedFunction newSecondFunction = linkedListTabulatedFunctionFactory.create(newXValues, newYValues);
    private static final TabulatedDifferentialOperator firstOperator = new TabulatedDifferentialOperator();
    private static final TabulatedDifferentialOperator secondOperator = new TabulatedDifferentialOperator(new ArrayTabulatedFunctionFactory());
    private static final TabulatedDifferentialOperator thirdOperator = new TabulatedDifferentialOperator(new LinkedListTabulatedFunctionFactory());

    @Test
    public void testGetFactory() {
        assertTrue(firstOperator.getFactory() instanceof ArrayTabulatedFunctionFactory);
        assertTrue(secondOperator.getFactory() instanceof ArrayTabulatedFunctionFactory);
        assertTrue(thirdOperator.getFactory() instanceof LinkedListTabulatedFunctionFactory);
    }

    @Test
    public void testSetFactory() {
        firstOperator.setFactory(new LinkedListTabulatedFunctionFactory());
        secondOperator.setFactory(new LinkedListTabulatedFunctionFactory());
        thirdOperator.setFactory(new ArrayTabulatedFunctionFactory());
        assertTrue(firstOperator.getFactory() instanceof LinkedListTabulatedFunctionFactory);
        assertTrue(secondOperator.getFactory() instanceof LinkedListTabulatedFunctionFactory);
        assertTrue(thirdOperator.getFactory() instanceof ArrayTabulatedFunctionFactory);
    }

    @Test
    public void testDerive() {
        TabulatedFunction derivedDefaultArrayFunction = firstOperator.derive(firstFunction);
        TabulatedFunction derivedArrayFunction = secondOperator.derive(firstFunction);
        TabulatedFunction derivedLinkedListFunction = thirdOperator.derive(secondFunction);
        assertTrue(derivedDefaultArrayFunction instanceof ArrayTabulatedFunction);
        assertTrue(derivedArrayFunction instanceof ArrayTabulatedFunction);
        assertTrue(derivedLinkedListFunction instanceof LinkedListTabulatedFunction);
        int count = derivedArrayFunction.getCount();
        for (int i = 0; i < count; i++) {
            assertEquals(derivedArrayFunction.getY(i), derivedYValues[i], 0.01);
            assertEquals(derivedLinkedListFunction.getY(i), derivedYValues[i], 0.01);
        }
        derivedDefaultArrayFunction = firstOperator.derive(newFirstFunction);
        derivedArrayFunction = secondOperator.derive(newFirstFunction);
        derivedLinkedListFunction = thirdOperator.derive(newSecondFunction);
        assertTrue(derivedDefaultArrayFunction instanceof ArrayTabulatedFunction);
        assertTrue(derivedArrayFunction instanceof ArrayTabulatedFunction);
        assertTrue(derivedLinkedListFunction instanceof LinkedListTabulatedFunction);
        count = derivedArrayFunction.getCount();
        for (int i = 0; i < count; i++) {
            assertEquals(derivedArrayFunction.getY(i), newDerivedYValues[i], 0.01);
            assertEquals(derivedLinkedListFunction.getY(i), newDerivedYValues[i], 0.01);
        }
    }
}