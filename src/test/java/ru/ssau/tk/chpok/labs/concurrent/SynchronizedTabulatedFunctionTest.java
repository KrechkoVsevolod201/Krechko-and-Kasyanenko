package ru.ssau.tk.chpok.labs.concurrent;

import org.testng.annotations.Test;
import ru.ssau.tk.chpok.labs.functions.*;

import java.util.Iterator;

import static org.testng.Assert.*;

public class SynchronizedTabulatedFunctionTest {
    private static final MathFunction sqrFunction = new  SqrFunction();
    private static final TabulatedFunction linkedListTabulatedFunction = new LinkedListTabulatedFunction(sqrFunction, -5, 5, 11);
    private static final TabulatedFunction arrayTabulatedFunction = new ArrayTabulatedFunction(sqrFunction, -5, 5, 11);

    @Test
    public void testGetCount() {
        TabulatedFunction synchronizedLinkedList = new SynchronizedTabulatedFunction(linkedListTabulatedFunction);
        TabulatedFunction synchronizedArray = new SynchronizedTabulatedFunction(arrayTabulatedFunction);
        assertEquals(synchronizedArray.getCount(), 11);
        assertEquals(synchronizedLinkedList.getCount(), 11);
    }

    @Test
    public void testGetX() {
        TabulatedFunction synchronizedLinkedList = new SynchronizedTabulatedFunction(linkedListTabulatedFunction);
        TabulatedFunction synchronizedArray = new SynchronizedTabulatedFunction(arrayTabulatedFunction);
        assertEquals(synchronizedArray.getX(0), -5.);
        assertEquals(synchronizedArray.getX(5), 0.);
        assertEquals(synchronizedLinkedList.getX(0), -5.);
        assertEquals(synchronizedLinkedList.getX(5), 0.);
    }

    @Test
    public void testGetY() {
        TabulatedFunction synchronizedLinkedList = new SynchronizedTabulatedFunction(linkedListTabulatedFunction);
        TabulatedFunction synchronizedArray = new SynchronizedTabulatedFunction(arrayTabulatedFunction);
        assertEquals(synchronizedArray.getY(0), 25.);
        assertEquals(synchronizedArray.getY(3), 4.);
        assertEquals(synchronizedArray.getY(10), 25.);
        assertEquals(synchronizedLinkedList.getY(0), 25.);
        assertEquals(synchronizedLinkedList.getY(3), 4.);
        assertEquals(synchronizedLinkedList.getY(10), 25.);
    }

    @Test
    public void testSetY() {
        TabulatedFunction synchronizedLinkedList = new SynchronizedTabulatedFunction(linkedListTabulatedFunction);
        TabulatedFunction synchronizedArray = new SynchronizedTabulatedFunction(arrayTabulatedFunction);
        synchronizedArray.setY(0, 1.);
        assertEquals(synchronizedArray.getY(0), 1.);
        synchronizedLinkedList.setY(10, 1.);
        assertEquals(synchronizedLinkedList.getY(10), 1.);
    }

    @Test
    public void testIndexOfX() {
        TabulatedFunction synchronizedLinkedList = new SynchronizedTabulatedFunction(linkedListTabulatedFunction);
        TabulatedFunction synchronizedArray = new SynchronizedTabulatedFunction(arrayTabulatedFunction);
        assertEquals(synchronizedArray.indexOfX(-4.), 1);
        assertEquals(synchronizedArray.indexOfX(3.), 8);
        assertEquals(synchronizedLinkedList.indexOfX(-4.), 1);
        assertEquals(synchronizedLinkedList.indexOfX(3.), 8);
    }

    @Test
    public void testIndexOfY() {
        TabulatedFunction synchronizedLinkedList = new SynchronizedTabulatedFunction(linkedListTabulatedFunction);
        TabulatedFunction synchronizedArray = new SynchronizedTabulatedFunction(arrayTabulatedFunction);
        assertEquals(synchronizedArray.indexOfY(25.), 0);
        assertEquals(synchronizedArray.indexOfY(0.), 5);
        assertEquals(synchronizedLinkedList.indexOfY(25.), 0);
        assertEquals(synchronizedLinkedList.indexOfY(0.), 5);
    }

    @Test
    public void testLeftBound() {
        TabulatedFunction synchronizedLinkedList = new SynchronizedTabulatedFunction(linkedListTabulatedFunction);
        TabulatedFunction synchronizedArray = new SynchronizedTabulatedFunction(arrayTabulatedFunction);
        assertEquals(synchronizedArray.leftBound(), -5.);
        assertEquals(synchronizedLinkedList.leftBound(), -5.);
    }

    @Test
    public void testRightBound() {
        TabulatedFunction synchronizedLinkedList = new SynchronizedTabulatedFunction(linkedListTabulatedFunction);
        TabulatedFunction synchronizedArray = new SynchronizedTabulatedFunction(arrayTabulatedFunction);
        assertEquals(synchronizedArray.rightBound(), 5.);
        assertEquals(synchronizedLinkedList.rightBound(), 5.);
    }

    @Test
    public void testIteratorThroughWile() {
        TabulatedFunction synchronizedLinkedList = new SynchronizedTabulatedFunction(linkedListTabulatedFunction);
        TabulatedFunction synchronizedArray = new SynchronizedTabulatedFunction(arrayTabulatedFunction);
        Iterator<Point> iteratorOfArray = synchronizedArray.iterator();
        int i = 0;
        while (iteratorOfArray.hasNext()) {
            Point currentPoint = iteratorOfArray.next();
            assertEquals(synchronizedArray.getX(i), currentPoint.x, 0.001);
            assertEquals(synchronizedArray.getY(i++), currentPoint.y, 0.001);
        }
        assertEquals(synchronizedArray.getCount(), i);
        Iterator<Point> iteratorOfLinkedList = synchronizedArray.iterator();
        i = 0;
        while (iteratorOfLinkedList.hasNext()) {
            Point currentPoint = iteratorOfLinkedList.next();
            assertEquals(synchronizedLinkedList.getX(i), currentPoint.x, 0.001);
            assertEquals(synchronizedLinkedList.getY(i++), currentPoint.y, 0.001);
        }
        assertEquals(synchronizedLinkedList.getCount(), i);
    }

    @Test
    public void testIteratorThroughForEach() {
        TabulatedFunction synchronizedLinkedList = new SynchronizedTabulatedFunction(linkedListTabulatedFunction);
        TabulatedFunction synchronizedArray = new SynchronizedTabulatedFunction(arrayTabulatedFunction);
        int i = 0;
        for (Point currentPoint : synchronizedArray) {
            assertEquals(currentPoint.x, synchronizedArray.getX(i), 0.001);
            assertEquals(currentPoint.y, synchronizedArray.getY(i++), 0.001);
        }
        assertEquals(synchronizedArray.getCount(), i);
        i = 0;
        for (Point currentPoint : synchronizedLinkedList) {
            assertEquals(currentPoint.x, synchronizedLinkedList.getX(i), 0.001);
            assertEquals(currentPoint.y, synchronizedLinkedList.getY(i++), 0.001);
        }
        assertEquals(synchronizedLinkedList.getCount(), i);
    }

    @Test
    public void testApply() {
        TabulatedFunction synchronizedLinkedList = new SynchronizedTabulatedFunction(linkedListTabulatedFunction);
        TabulatedFunction synchronizedArray = new SynchronizedTabulatedFunction(arrayTabulatedFunction);
        assertEquals(synchronizedLinkedList.apply(-3), 9.);
        assertEquals(synchronizedLinkedList.apply(1), 1.);
        assertEquals(synchronizedLinkedList.apply(6), 34.);
        assertEquals(synchronizedArray.apply(-3), 9.);
        assertEquals(synchronizedArray.apply(1), 1.);
        assertEquals(synchronizedArray.apply(6), 34.);
    }

    @Test
    public void testDoSynchronously() {
        SynchronizedTabulatedFunction synchronizedTabulatedFunction = new SynchronizedTabulatedFunction(linkedListTabulatedFunction);
        assertEquals((int) synchronizedTabulatedFunction.doSynchronously(SynchronizedTabulatedFunction::getCount), 11);
        assertEquals((double) synchronizedTabulatedFunction.doSynchronously(SynchronizedTabulatedFunction::rightBound), 5.0);
    }
}