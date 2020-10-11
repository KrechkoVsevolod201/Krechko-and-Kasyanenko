package ru.ssau.tk.chpok.labs.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class CompositeFunctionTest {

    private static final double DELTA = 0.00001;

    protected final static double[] xArr = new double[]{0, 1, 2, 3};
    protected final static double[] yArr = new double[]{4,5,6,7,8};
    protected final static double[] yArr2 = new double[]{0, 1, 8, 27};
    protected final static MathFunction firstTabFunc = new LinkedListTabulatedFunction(xArr, yArr);
    protected final static MathFunction secondTabFunc = new ArrayTabulatedFunction(xArr, yArr2);


    @Test
    public void testApply() {
        MathFunction sqr = new SqrFunction();
        MathFunction sqrt = new SqrtFunction();
        MathFunction sin = new SinFunction();
        CompositeFunction testFunction = new CompositeFunction(sqr, sqrt);
        assertEquals(testFunction.apply(0), 0,DELTA);
        assertEquals(testFunction.apply(-1.0), 1.0,DELTA);
        assertNotEquals(testFunction.apply(2.0), 69,DELTA);
        assertNotEquals(testFunction.apply(12), 144,DELTA);


        MathFunction sinSqr = new CompositeFunction(sin, sqr);
        MathFunction sqrSqrt = new CompositeFunction(sqr, sqrt);
        MathFunction doubleSqr = new CompositeFunction(sqr, sqr);
        MathFunction sinSqrt = new CompositeFunction(sin, sqrt);

        assertEquals(sinSqr.apply(0), 0, 0.001);
        assertEquals(sinSqr.apply((Math.PI / 2) *(-1)), 1, 0.001);
        assertEquals(sqrSqrt.apply(5), 5, 0.001);
        assertEquals(doubleSqr.apply(2), 16, 0.001);
        assertEquals(sinSqrt.apply(0), 0, 0.001);
        assertEquals(firstTabFunc.andThen(secondTabFunc).andThen(sqr).apply(2),7056, 0.001);
        assertEquals(firstTabFunc.andThen(secondTabFunc).andThen(sqr).apply(1),4225, 0.001);
        assertEquals(firstTabFunc.andThen(secondTabFunc).andThen(sqr).apply(0),2116, 0.001);
    }
}