package ru.ssau.tk.chpok.labs.functions;

import ru.ssau.tk.chpok.labs.exceptions.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedListTabulatedFunction extends AbstractTabulatedFunction {
    private Node head;

    private int count;

    public LinkedListTabulatedFunction(double[] xValues, double[] yValues) {
        if (xValues.length < 2) {
            throw new IllegalArgumentException("Array size is smaller than 2");
        } else if (yValues.length < 2) {
            throw new IllegalArgumentException("Array size is smaller than 2");
        }
        this.count = xValues.length;
        for (int i = 0; i < count; i++) {
            this.addNode(xValues[i], yValues[i]);
        }
    }

    public LinkedListTabulatedFunction(MathFunction source, double xFrom, double xTo, int count) {
        if (count < 2) {
            throw new IllegalArgumentException("Array size is smaller than 2");
        }
        double AddVar = xFrom;
        double step = (xTo - xFrom) / (count - 1);
        for (int i = 0; i < count; i++) {
            this.addNode(AddVar, source.apply(AddVar));
            AddVar += step;
        }
    }

    private void addNode(double x, double y) {
        Node newNode = new Node();
        if (head == null) {
            head = newNode;
            newNode.next = newNode;
            newNode.prev = newNode;
            newNode.x = x;
            newNode.y = y;
        } else {
            newNode.next = head;
            newNode.prev = head.prev;
            newNode.x = x;
            newNode.y = y;
            head.prev.next = newNode;
            head.prev = newNode;
        }

    }

    private Node getNode(int index) {
        Node node;

        if (index > (count / 2)) {
            node = head.prev;
            for (int i = count - 1; i > 0; i--) {
                if (i == index) {
                    break;
                } else {
                    node = node.prev;
                }
            }
        } else {
            node = head;
            for (int i = 0; i < count; i++) {
                if (i == index) {
                    break;
                } else {
                    node = node.next;
                }
            }
        }
        return node;
    }

    @Override
    public double getX(int index) {
        return getNode(index).x;
    }

    @Override
    public double getY(int index) {
        return getNode(index).y;
    }

    @Override
    public void setY(int index, double value) {
        getNode(index).y = value;
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public double leftBound() {
        return head.x;
    }

    @Override
    public double rightBound() {
        return head.prev.x;
    }

    @Override
    public int indexOfX(double x) {
        Node AddVar;
        AddVar = head;
        for (int i = 0; i < count; i++) {
            if (!Double.isNaN(AddVar.x) && (AddVar.x == x)) {
                return i;
            } else if (Double.isNaN(AddVar.x) && Double.isNaN(x)) {
                return i;
            } else {
                AddVar = AddVar.next;
            }
        }
        return -1;
    }

    @Override
    public int indexOfY(double y) {
        Node AddVar;
        AddVar = head;
        for (int i = 0; i < count; i++) {
            if (!Double.isNaN(AddVar.y) && (AddVar.y == y)) {
                return i;
            } else if (Double.isNaN(AddVar.y) && Double.isNaN(y)) {
                return i;
            } else {
                AddVar = AddVar.next;
            }
        }
        return -1;
    }

    @Override
    protected int floorIndexOfX(double x) {
        Node AddVar;
        AddVar = head;
        int flag = 0;
        for (int i = 0; i < count; i++) {
            if (AddVar.x < x) {
                flag += 1;
                AddVar = AddVar.next;
            }
        }
        if (flag == 0) {
            return 0;
        } else if (flag == count) {
            return count;
        } else {
            return flag;
        }
    }

    @Override
    protected double extrapolateLeft(double x) {
        if (count == 1) {
            return x;
        }
        return interpolate(x, head.x, head.next.x, head.y, head.next.y);
    }

    @Override
    protected double extrapolateRight(double x) {
        if (count == 1) {
            return x;
        }
        return interpolate(x, head.prev.prev.x, head.prev.x, head.prev.prev.y, head.prev.y);
    }

    @Override
    protected double interpolate(double x, int floorIndex) {
        Node floor = getNode(floorIndex);
        Node ceiling = floor.next;
        if (x < floor.x || x > ceiling.x) {
            throw new InterpolationException("x is out of bounds of interpolation");
        }
        return interpolate(x, floor.x, ceiling.x, floor.y, ceiling.y);
    }


    @Override
    public Iterator<Point> iterator() {
        return new Iterator<>() {
            private Node node = head;

            public boolean hasNext() {
                return (node != null);
            }

            @Override
            public Point next() {
                if (hasNext()) {
                    Point point = new Point(node.x, node.y);
                    node = (node != head.prev) ? node.next : null;
                    return point;
                } else {
                    throw new NoSuchElementException();
                }
            }
        };
    }


    private static class Node {
        private Node next;
        private Node prev;
        private double x;
        private double y;
    }
}