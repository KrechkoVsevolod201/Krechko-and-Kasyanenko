package ru.ssau.tk.chpok.labs.functions;

public class LinkedListTabulatedFunction extends AbstractTabulatedFunction {
    private Node head;

    protected int count;

    private static class Node {
        Node next;
        Node prev;
        double x;
        double y;
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

    public LinkedListTabulatedFunction(double[] xValues, double[] yValues) {
        this.count = xValues.length;
        for (int i = 0; i < count; i++) {
            this.addNode(xValues[i], yValues[i]);
        }
    }

    public LinkedListTabulatedFunction(MathFunction source, double xFrom, double xTo, int count) {
        double auxiliaryVar = xFrom;
        double step = (xTo - xFrom) / (count - 1);
        for (int i = 0; i < count; i++) {
            this.addNode(auxiliaryVar, source.apply(auxiliaryVar));
            auxiliaryVar += step;
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
        Node auxiliaryVar;
        auxiliaryVar = head;
        for (int i = 0; i < count; i++) {
            if (auxiliaryVar.x == x) {
                return i;
            } else {
                auxiliaryVar = auxiliaryVar.next;
            }
        }
        return -1;
    }

    @Override
    public int indexOfY(double y) {
        Node auxiliaryVar;
        auxiliaryVar = head;
        for (int i = 0; i < count; i++) {
            if (auxiliaryVar.y == y) {
                return i;
            } else {
                auxiliaryVar = auxiliaryVar.next;
            }
        }
        return -1;
    }

    @Override
    protected int floorIndexOfX(double x) {
        Node auxiliaryVar;
        auxiliaryVar = head;
        int flag = 0;
        for (int i = 0; i < count; i++) {
            if (auxiliaryVar.x < x) {
                flag += 1;
                auxiliaryVar = auxiliaryVar.next;
            }
            if (flag == 0) {
                return 0;
            } else if (flag == count) {
                return count;
            } else {
                return flag;
            }
        }
        return 0;
    }

    @Override
    protected double extrapolateLeft(double x) {
        return interpolate(x, head.x, head.next.x, head.y, head.next.y);
    }

    @Override
    protected double extrapolateRight(double x) {
        return interpolate(x, head.prev.prev.x, head.prev.x, head.prev.prev.y, head.prev.y);
    }

    @Override
    protected double interpolate(double x, int floorIndex) {
        Node floor = getNode(floorIndex);
        Node ceiling = floor.next;
        return interpolate(x, floor.x, ceiling.x, floor.y, ceiling.y);
    }
}