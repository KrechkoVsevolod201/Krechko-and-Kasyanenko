package ru.ssau.tk.chpok.labs.functions;

public class LinkedListTabulatedFunction extends AbstractTabulatedFunction {
    private Node head;

    private int count;

    private static class Node {
        public Node next;
        public Node prev;
        public double x;
        public double y;
    }

    private void IsIndexOutOfBounds(int index) {
        if (index < 0 || index > count - 1) {
            throw new IllegalArgumentException("Index is out of bounds");
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

    public LinkedListTabulatedFunction(double[] xValues, double[] yValues) {
        if (xValues.length < 2) {
            throw new IllegalArgumentException("Error array size is smaller than 2");
        } else if (yValues.length < 2) {
            throw new IllegalArgumentException("Error array size is smaller than 2");
        }
        this.count = xValues.length;
        for (int i = 0; i < count; i++) {
            this.addNode(xValues[i], yValues[i]);
        }
    }

    public LinkedListTabulatedFunction(MathFunction source, double xFrom, double xTo, int count) {
        if (count < 2) {
            throw new IllegalArgumentException("Error array size is smaller than 2");
        }
        double addVar = xFrom;
        double step = (xTo - xFrom) / (count - 1);
        for (int i = 0; i < count; i++) {
            this.addNode(addVar, source.apply(addVar));
            addVar += step;
        }
    }

    private Node getNode(int index) {
        Node node;
        IsIndexOutOfBounds(index);
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
        IsIndexOutOfBounds(index);
        return getNode(index).x;
    }

    @Override
    public double getY(int index) {
        IsIndexOutOfBounds(index);
        return getNode(index).y;
    }

    @Override
    public void setY(int index, double value) {
        IsIndexOutOfBounds(index);
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
        Node addVar;
        addVar = head;
        for (int i = 0; i < count; i++) {
            if (!Double.isNaN(addVar.x) && (addVar.x == x)) {
                return i;
            } else if (Double.isNaN(addVar.x) && Double.isNaN(x)) {
                return i;
            } else {
                addVar = addVar.next;
            }
        }
        return -1;
    }

    @Override
    public int indexOfY(double y) {
        Node addVar;
        addVar = head;
        for (int i = 0; i < count; i++) {
            if (!Double.isNaN(addVar.y) && (addVar.y == y)) {
                return i;
            } else if (Double.isNaN(addVar.y) && Double.isNaN(y)) {
                return i;
            } else {
                addVar = addVar.next;
            }
        }
        return -1;
    }

    @Override
    protected int floorIndexOfX(double x) {
        Node addVar;
        addVar = head;
        int flag = 0;
        for (int i = 0; i < count; i++) {
            if (addVar.x < x) {
                flag += 1;
                addVar = addVar.next;
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