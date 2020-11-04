package ru.ssau.tk.chpok.labs.operations;

import org.testng.annotations.Test;
import ru.ssau.tk.chpok.labs.exceptions.InconsistentFunctionsException;
import ru.ssau.tk.chpok.labs.functions.ArrayTabulatedFunction;
import ru.ssau.tk.chpok.labs.functions.LinkedListTabulatedFunction;
import ru.ssau.tk.chpok.labs.functions.Point;
import ru.ssau.tk.chpok.labs.functions.TabulatedFunction;
import ru.ssau.tk.chpok.labs.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.chpok.labs.functions.factory.LinkedListTabulatedFunctionFactory;

import static org.testng.Assert.*;

public class TabulatedFunctionOperationServiceTest {
    private final double[] valuesX = new double[]{-27, -8, -1, 0, 1, 8, 27};
    private final double[] valuesY = new double[]{-3, -2, -1, -0, 1, 2, 3};
    private final double[] valuesYSecond = new double[]{1, 2, 3, 4, 5, 6, 7};
    ArrayTabulatedFunction ArrayFunction = new ArrayTabulatedFunction(valuesX, valuesY);
    LinkedListTabulatedFunction ListFunction = new LinkedListTabulatedFunction(valuesX, valuesYSecond);
    TabulatedFunctionOperationService service = new TabulatedFunctionOperationService();
    TabulatedFunction arrayFunction = new ArrayTabulatedFunction(valuesX, valuesY);
    TabulatedFunction listFunction = new LinkedListTabulatedFunction(valuesX, valuesYSecond);
    @Test
    public void testAsPoints() {
        Point[] Points = TabulatedFunctionOperationService.asPoints(ArrayFunction);
        int i = 0;
        for (Point myPoint : Points) {
            assertEquals(myPoint.x, ArrayFunction.getX(i), 0.1);
            assertEquals(myPoint.y, ArrayFunction.getY(i++), 0.1);
        }
        assertEquals(ArrayFunction.getCount(), i);

        Points = TabulatedFunctionOperationService.asPoints(ListFunction);
        i = 0;
        for (Point myPoint : Points) {
            assertEquals(myPoint.x, ListFunction.getX(i), 0.1);
            assertEquals(myPoint.y, ListFunction.getY(i++), 0.1);
        }
        assertEquals(ListFunction.getCount(), i);
    }

    @Test
    public void testGetFactory() {
        assertTrue(service.getFactory() instanceof ArrayTabulatedFunctionFactory);
        assertTrue(new TabulatedFunctionOperationService(new LinkedListTabulatedFunctionFactory()).getFactory() instanceof LinkedListTabulatedFunctionFactory);    }

    @Test
    public void testSetFactory() {
        service.setFactory(new LinkedListTabulatedFunctionFactory());
        assertTrue(service.getFactory() instanceof LinkedListTabulatedFunctionFactory);
    }

    @Test
    public void testSum() {
        final double[] errorX = new double[]{0, 1, 2};
        final double[] errorY = new double[]{0, 1, 2};
        TabulatedFunction errorTest = new ArrayTabulatedFunction(errorX, errorY);
        assertThrows(InconsistentFunctionsException.class, () -> new TabulatedFunctionOperationService().sum(ListFunction, errorTest));

        final double[] errorX1 = new double[]{-27, -8, -1, 0, 1, 8, 28};
        final double[] errorY1 = new double[]{-3, -2, -1, -0, 1, 2, 3};
        TabulatedFunction errorTest1 = new ArrayTabulatedFunction(errorX1, errorY1);
        assertThrows(InconsistentFunctionsException.class, () -> new TabulatedFunctionOperationService().sum(ListFunction, errorTest1));

        TabulatedFunction testSumOfArrays = new TabulatedFunctionOperationService().sum(ArrayFunction, ArrayFunction);
        int i = 0;
        for (Point point : testSumOfArrays) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesY[i] + valuesY[i++]);
        }

        TabulatedFunction testSumOfLists = new TabulatedFunctionOperationService().sum(ListFunction, ListFunction);
        i = 0;
        for (Point point : testSumOfLists) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesYSecond[i] + valuesYSecond[i++]);
        }

        TabulatedFunction testSumOfArrayAndList = new TabulatedFunctionOperationService().sum(ArrayFunction, ListFunction);
        i = 0;
        for (Point point : testSumOfArrayAndList) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesY[i] + valuesYSecond[i++]);
        }
    }

    @Test
    public void testSubtract() {
        TabulatedFunction testSubtractOfArrays = new TabulatedFunctionOperationService().subtract(ArrayFunction, ArrayFunction);
        int i = 0;
        for (Point point : testSubtractOfArrays) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesY[i] - valuesY[i++]);
        }

        TabulatedFunction testSubtractOfLists = new TabulatedFunctionOperationService().subtract(ListFunction, ListFunction);
        i = 0;
        for (Point point : testSubtractOfLists) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesYSecond[i] - valuesYSecond[i++]);
        }

        TabulatedFunction testSubtractOfArrayAndList = new TabulatedFunctionOperationService().subtract(ArrayFunction, ListFunction);
        i = 0;
        for (Point point : testSubtractOfArrayAndList) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesY[i] - valuesYSecond[i++]);
        }
    }
    @Test
    public void testMultiply() {
        TabulatedFunction arrayTestMultiplyOfArrays = new TabulatedFunctionOperationService().multiply(arrayFunction, arrayFunction);
        TabulatedFunction listTestMultiplyOfArrays = new TabulatedFunctionOperationService().multiply(arrayFunction, arrayFunction);
        int i = 0;
        assertTrue(arrayTestMultiplyOfArrays instanceof ArrayTabulatedFunction);
        assertTrue(listTestMultiplyOfArrays instanceof LinkedListTabulatedFunction);
        for (Point point : arrayTestMultiplyOfArrays) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesY[i] * valuesY[i++]);
        }
        i = 0;
        for (Point point : listTestMultiplyOfArrays) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesY[i] * valuesY[i++]);
        }

        TabulatedFunction arrayTestMultiplyOfLists = new TabulatedFunctionOperationService().multiply(listFunction, listFunction);
        TabulatedFunction listTestMultiplyOfLists = new TabulatedFunctionOperationService().multiply(listFunction, listFunction);
        i = 0;
        assertTrue(arrayTestMultiplyOfLists instanceof ArrayTabulatedFunction);
        assertTrue(listTestMultiplyOfLists instanceof LinkedListTabulatedFunction);
        for (Point point : arrayTestMultiplyOfLists) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesYSecond[i] * valuesYSecond[i++]);
        }
        i = 0;
        for (Point point : listTestMultiplyOfLists) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesYSecond[i] * valuesYSecond[i++]);
        }

        TabulatedFunction ArrayTestMultiplyOfArrayAndList = new TabulatedFunctionOperationService().multiply(arrayFunction, listFunction);
        TabulatedFunction ListTestMultiplyOfArrayAndList = new TabulatedFunctionOperationService().multiply(arrayFunction, listFunction);
        i = 0;
        assertTrue(ArrayTestMultiplyOfArrayAndList instanceof ArrayTabulatedFunction);
        assertTrue(ListTestMultiplyOfArrayAndList instanceof LinkedListTabulatedFunction);
        for (Point point : ArrayTestMultiplyOfArrayAndList) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesY[i] * valuesYSecond[i++]);
        }
        i = 0;
        for (Point point : ListTestMultiplyOfArrayAndList) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesY[i] * valuesYSecond[i++]);
        }
    }

    @Test
    public void testDivision() {
        TabulatedFunction arrayTestDivisionOfArrays = new TabulatedFunctionOperationService().division(arrayFunction, arrayFunction);
        TabulatedFunction listTestDivisionOfArrays = new TabulatedFunctionOperationService().division(arrayFunction, arrayFunction);
        int i = 0;
        assertTrue(arrayTestDivisionOfArrays instanceof ArrayTabulatedFunction);
        assertTrue(listTestDivisionOfArrays instanceof LinkedListTabulatedFunction);
        for (Point point : arrayTestDivisionOfArrays) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesY[i] / valuesY[i++]);
        }
        i = 0;
        for (Point point : listTestDivisionOfArrays) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesY[i] / valuesY[i++]);
        }

        TabulatedFunction arrayTestDivisionOfLists = new TabulatedFunctionOperationService().division(listFunction, listFunction);
        TabulatedFunction listTestDivisionOfLists = new TabulatedFunctionOperationService().division(listFunction, listFunction);
        i = 0;
        assertTrue(arrayTestDivisionOfLists instanceof ArrayTabulatedFunction);
        assertTrue(listTestDivisionOfLists instanceof LinkedListTabulatedFunction);
        for (Point point : arrayTestDivisionOfLists) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesYSecond[i] / valuesYSecond[i++]);
        }
        i = 0;
        for (Point point : listTestDivisionOfLists) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesYSecond[i] / valuesYSecond[i++]);
        }

        TabulatedFunction ArrayTestDivisionOfArrayAndList = new TabulatedFunctionOperationService().division(arrayFunction, listFunction);
        TabulatedFunction ListTestDivisionOfArrayAndList = new TabulatedFunctionOperationService().division(arrayFunction, listFunction);
        i = 0;
        assertTrue(ArrayTestDivisionOfArrayAndList instanceof ArrayTabulatedFunction);
        assertTrue(ListTestDivisionOfArrayAndList instanceof LinkedListTabulatedFunction);
        for (Point point : ArrayTestDivisionOfArrayAndList) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesY[i] / valuesYSecond[i++]);
        }
        i = 0;
        for (Point point : ListTestDivisionOfArrayAndList) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesY[i] / valuesYSecond[i++]);
        }
    }
}
