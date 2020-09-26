package ru.ssau.tk.chpok.labs.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class MathFunctionTest {
    MathFunction sqrX = new SqrFunction();
    MathFunction sinX = new SinFunction();
    MathFunction unit = new UnitFunction();
    MathFunction testAndThen = sqrX.andThen(sqrX);

    @Test
    public void testAndThen() {
            assertEquals(testAndThen.apply(1.0), 1,0);
            assertEquals(testAndThen.andThen(unit).apply(10), 1.0,0);
            assertEquals(testAndThen.andThen(sqrX).apply(2), 64,0);
            assertEquals(testAndThen.andThen(sinX).apply(0), 0,0);

    }
}
