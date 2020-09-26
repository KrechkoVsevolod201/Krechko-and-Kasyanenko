package ru.ssau.tk.chpok.labs.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class MathFunctionTest {
    private final MathFunction sqrX = new SqrFunction();
    private final MathFunction sqrtX = new SqrtFunction();
    private final MathFunction unit = new UnitFunction();
    private final MathFunction testAndThen = sqrX.andThen(sqrX);

    @Test
    public void testAndThen() {
            assertEquals(testAndThen.apply(1.0), 1,0);
            assertEquals(testAndThen.andThen(unit).apply(10), 1.0,0);
            assertEquals(testAndThen.andThen(sqrX).apply(2), 2,0);
            assertEquals(testAndThen.andThen(sqrtX).apply(0), 0,0);
    }
}
