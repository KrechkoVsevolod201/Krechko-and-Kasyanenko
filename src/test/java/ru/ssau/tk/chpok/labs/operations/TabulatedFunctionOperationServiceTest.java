package ru.ssau.tk.chpok.labs.operations;

import org.testng.annotations.Test;
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
    private final double[] valuesYForList = new double[]{100, 200, 300, 400, 500, 600, 700};
    private final TabulatedFunction arrayFunction = new ArrayTabulatedFunction(valuesX, valuesY);
    private final TabulatedFunction listFunction = new LinkedListTabulatedFunction(valuesX, valuesYForList);
    private static final TabulatedFunctionOperationService service = new TabulatedFunctionOperationService();
    private final TabulatedFunctionOperationService arrayService = new TabulatedFunctionOperationService(new ArrayTabulatedFunctionFactory());
    private final TabulatedFunctionOperationService listService = new TabulatedFunctionOperationService(new LinkedListTabulatedFunctionFactory());
    private static final TabulatedFunctionOperationService firstOperator = new TabulatedFunctionOperationService();
    private static final TabulatedFunctionOperationService secondOperator = new TabulatedFunctionOperationService(new ArrayTabulatedFunctionFactory());
    private static final TabulatedFunctionOperationService thirdOperator = new TabulatedFunctionOperationService(new LinkedListTabulatedFunctionFactory());
    private final double DELTA = 0.001;

    public ArrayTabulatedFunction getTestArray() {
        return new ArrayTabulatedFunction(valuesX, valuesY);
    }

    public LinkedListTabulatedFunction getTestList() {
        return new LinkedListTabulatedFunction(valuesX, valuesYForList);
    }

    @Test
    public void testAsPoints() {
        TabulatedFunction testArrayFunction = getTestArray();
        Point[] Points = TabulatedFunctionOperationService.asPoints(testArrayFunction);
        int i = 0;
        for (Point myPoint : Points) {
            assertEquals(myPoint.x, testArrayFunction.getX(i), DELTA);
            assertEquals(myPoint.y, testArrayFunction.getY(i++), DELTA);
        }
        assertEquals(testArrayFunction.getCount(), i);

        TabulatedFunction testListFunction = getTestList();
        Points = TabulatedFunctionOperationService.asPoints(testListFunction);
        i = 0;
        for (Point myPoint : Points) {
            assertEquals(myPoint.x, testListFunction.getX(i), DELTA);
            assertEquals(myPoint.y, testListFunction.getY(i++), DELTA);
        }
        assertEquals(testListFunction.getCount(), i);
    }

    @Test
    public void testGetFactory() {
        assertTrue(service.getFactory() instanceof ArrayTabulatedFunctionFactory);
        assertTrue(new TabulatedFunctionOperationService(new LinkedListTabulatedFunctionFactory()).getFactory() instanceof LinkedListTabulatedFunctionFactory);
        assertTrue(firstOperator.getFactory() instanceof ArrayTabulatedFunctionFactory);
        assertTrue(secondOperator.getFactory() instanceof ArrayTabulatedFunctionFactory);
        assertTrue(thirdOperator.getFactory() instanceof LinkedListTabulatedFunctionFactory);
    }

    @Test
    public void testSetFactory() {
        service.setFactory(new LinkedListTabulatedFunctionFactory());
        assertTrue(service.getFactory() instanceof LinkedListTabulatedFunctionFactory);
        firstOperator.setFactory(new LinkedListTabulatedFunctionFactory());
        secondOperator.setFactory(new LinkedListTabulatedFunctionFactory());
        thirdOperator.setFactory(new ArrayTabulatedFunctionFactory());
        assertTrue(firstOperator.getFactory() instanceof LinkedListTabulatedFunctionFactory);
        assertTrue(secondOperator.getFactory() instanceof LinkedListTabulatedFunctionFactory);
        assertTrue(thirdOperator.getFactory() instanceof ArrayTabulatedFunctionFactory);
    }

    @Test
    public void testSum() {
        TabulatedFunction arrayTestSumOfArrays = arrayService.sum(arrayFunction, arrayFunction);
        TabulatedFunction listTestSumOfArrays = listService.sum(arrayFunction, arrayFunction);
        int i = 0;
        assertTrue(arrayTestSumOfArrays instanceof ArrayTabulatedFunction);
        assertTrue(listTestSumOfArrays instanceof LinkedListTabulatedFunction);
        for (Point point : arrayTestSumOfArrays) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesY[i] + valuesY[i++]);
        }
        i = 0;
        for (Point point : listTestSumOfArrays) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesY[i] + valuesY[i++]);
        }

        TabulatedFunction arrayTestSumOfLists = arrayService.sum(listFunction, listFunction);
        TabulatedFunction listTestSumOfLists = listService.sum(listFunction, listFunction);
        i = 0;
        assertTrue(arrayTestSumOfLists instanceof ArrayTabulatedFunction);
        assertTrue(listTestSumOfLists instanceof LinkedListTabulatedFunction);
        for (Point point : arrayTestSumOfLists) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesYForList[i] + valuesYForList[i++]);
        }
        i = 0;
        for (Point point : listTestSumOfLists) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesYForList[i] + valuesYForList[i++]);
        }

        TabulatedFunction arrayTestSumOfArrayAndList = arrayService.sum(arrayFunction, listFunction);
        TabulatedFunction listTestSumOfArrayAndList = listService.sum(arrayFunction, listFunction);
        i = 0;
        assertTrue(arrayTestSumOfArrayAndList instanceof ArrayTabulatedFunction);
        assertTrue(listTestSumOfArrayAndList instanceof LinkedListTabulatedFunction);
        for (Point point : arrayTestSumOfArrayAndList) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesY[i] + valuesYForList[i++]);
        }
        i = 0;
        for (Point point : listTestSumOfArrayAndList) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesY[i] + valuesYForList[i++]);
        }
    }

    @Test
    public void testSubtract() {
        TabulatedFunction arrayTestSubtractOfArrays = arrayService.subtract(arrayFunction, arrayFunction);
        TabulatedFunction listTestSubtractOfArrays = listService.subtract(arrayFunction, arrayFunction);
        int i = 0;
        assertTrue(arrayTestSubtractOfArrays instanceof ArrayTabulatedFunction);
        assertTrue(listTestSubtractOfArrays instanceof LinkedListTabulatedFunction);
        for (Point point : arrayTestSubtractOfArrays) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesY[i] - valuesY[i++]);
        }
        i = 0;
        for (Point point : listTestSubtractOfArrays) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesY[i] - valuesY[i++]);
        }

        TabulatedFunction arrayTestSubtractOfLists = arrayService.subtract(listFunction, listFunction);
        TabulatedFunction listTestSubtractOfLists = listService.subtract(listFunction, listFunction);
        i = 0;
        assertTrue(arrayTestSubtractOfLists instanceof ArrayTabulatedFunction);
        assertTrue(listTestSubtractOfLists instanceof LinkedListTabulatedFunction);
        for (Point point : arrayTestSubtractOfLists) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesYForList[i] - valuesYForList[i++]);
        }
        i = 0;
        for (Point point : listTestSubtractOfLists) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesYForList[i] - valuesYForList[i++]);
        }

        TabulatedFunction arrayTestSubtractOfArrayAndList = arrayService.subtract(arrayFunction, listFunction);
        TabulatedFunction listTestSubtractOfArrayAndList = listService.subtract(arrayFunction, listFunction);
        i = 0;
        assertTrue(arrayTestSubtractOfArrayAndList instanceof ArrayTabulatedFunction);
        assertTrue(listTestSubtractOfArrayAndList instanceof LinkedListTabulatedFunction);
        for (Point point : arrayTestSubtractOfArrayAndList) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesY[i] - valuesYForList[i++]);
        }
        i = 0;
        for (Point point : listTestSubtractOfArrayAndList) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesY[i] - valuesYForList[i++]);
        }
    }

    @Test
    public void testMultiply() {
        TabulatedFunction arrayTestMultiplyOfArrays = arrayService.multiply(arrayFunction, arrayFunction);
        TabulatedFunction listTestMultiplyOfArrays = listService.multiply(arrayFunction, arrayFunction);
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

        TabulatedFunction arrayTestMultiplyOfLists = arrayService.multiply(listFunction, listFunction);
        TabulatedFunction listTestMultiplyOfLists = listService.multiply(listFunction, listFunction);
        i = 0;
        assertTrue(arrayTestMultiplyOfLists instanceof ArrayTabulatedFunction);
        assertTrue(listTestMultiplyOfLists instanceof LinkedListTabulatedFunction);
        for (Point point : arrayTestMultiplyOfLists) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesYForList[i] * valuesYForList[i++]);
        }
        i = 0;
        for (Point point : listTestMultiplyOfLists) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesYForList[i] * valuesYForList[i++]);
        }

        TabulatedFunction arrayTestMultiplyOfArrayAndList = arrayService.multiply(arrayFunction, listFunction);
        TabulatedFunction listTestMultiplyOfArrayAndList = listService.multiply(arrayFunction, listFunction);
        i = 0;
        assertTrue(arrayTestMultiplyOfArrayAndList instanceof ArrayTabulatedFunction);
        assertTrue(listTestMultiplyOfArrayAndList instanceof LinkedListTabulatedFunction);
        for (Point point : arrayTestMultiplyOfArrayAndList) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesY[i] * valuesYForList[i++]);
        }
        i = 0;
        for (Point point : listTestMultiplyOfArrayAndList) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesY[i] * valuesYForList[i++]);
        }
    }

    @Test
    public void testDivide() {
        TabulatedFunction arrayTestDivisionOfArrays = arrayService.division(arrayFunction, arrayFunction);
        TabulatedFunction listTestDivisionOfArrays = listService.division(arrayFunction, arrayFunction);
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

        TabulatedFunction arrayTestDivisionOfLists = arrayService.division(listFunction, listFunction);
        TabulatedFunction listTestDivisionOfLists = listService.division(listFunction, listFunction);
        i = 0;
        assertTrue(arrayTestDivisionOfLists instanceof ArrayTabulatedFunction);
        assertTrue(listTestDivisionOfLists instanceof LinkedListTabulatedFunction);
        for (Point point : arrayTestDivisionOfLists) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesYForList[i] / valuesYForList[i++]);
        }
        i = 0;
        for (Point point : listTestDivisionOfLists) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesYForList[i] / valuesYForList[i++]);
        }

        TabulatedFunction arrayTestDivisionOfArrayAndList = arrayService.division(arrayFunction, listFunction);
        TabulatedFunction listTestDivisionOfArrayAndList = listService.division(arrayFunction, listFunction);
        i = 0;
        assertTrue(arrayTestDivisionOfArrayAndList instanceof ArrayTabulatedFunction);
        assertTrue(listTestDivisionOfArrayAndList instanceof LinkedListTabulatedFunction);
        for (Point point : arrayTestDivisionOfArrayAndList) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesY[i] / valuesYForList[i++]);
        }
        i = 0;
        for (Point point : listTestDivisionOfArrayAndList) {
            assertEquals(point.x, valuesX[i]);
            assertEquals(point.y, valuesY[i] / valuesYForList[i++]);
        }
    }
}