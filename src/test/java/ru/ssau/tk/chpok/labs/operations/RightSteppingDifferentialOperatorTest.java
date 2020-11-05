package ru.ssau.tk.chpok.labs.operations;

import org.testng.annotations.Test;
import ru.ssau.tk.chpok.labs.functions.SqrFunction;

import static org.testng.Assert.*;

public class RightSteppingDifferentialOperatorTest {

    @Test
    public void testDerive() {
        final double step = 0.01;
        SteppingDifferentialOperator differentialOperator = new RightSteppingDifferentialOperator(step);
        assertEquals(differentialOperator.derive(new SqrFunction()).apply(1), 2.01, 0.01);
        assertEquals(differentialOperator.derive(new SqrFunction()).apply(2), 4.01, 0.01);
    }
}