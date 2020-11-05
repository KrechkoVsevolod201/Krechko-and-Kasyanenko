package ru.ssau.tk.chpok.labs.operations;

import org.testng.annotations.Test;
import ru.ssau.tk.chpok.labs.functions.SqrFunction;

import static org.testng.Assert.*;

public class LeftSteppingDifferentialOperatorTest {

    @Test
    public void testDerive() {
        final double step = 0.001;
        SteppingDifferentialOperator differentialOperator = new LeftSteppingDifferentialOperator(step);
        assertEquals(differentialOperator.derive(new SqrFunction()).apply(1), 1.99, 0.1);
        assertEquals(differentialOperator.derive(new SqrFunction()).apply(2), 3.99, 0.1);
    }
}