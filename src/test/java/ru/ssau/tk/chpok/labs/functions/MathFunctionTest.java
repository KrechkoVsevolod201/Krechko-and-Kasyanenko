package ru.ssau.tk.chpok.labs.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class MathFunctionTest {

    private static final double DELTA = 0.00001;
    private final MathFunction sqrX = new SqrFunction();
    private final MathFunction sqrtX = new SqrtFunction();
    private final MathFunction unit = new UnitFunction();
    private final MathFunction testAndThen = sqrX.andThen(sqrtX);

    @Test
    public void testAndThen() {
        assertEquals(testAndThen.apply(1.0), 1, DELTA);
        assertEquals(testAndThen.andThen(unit).apply(10), 1.0, DELTA);
        assertEquals(testAndThen.andThen(sqrX).apply(2), 4, DELTA);
        assertEquals(testAndThen.andThen(sqrtX).apply(0), 0, DELTA);
    }
}
