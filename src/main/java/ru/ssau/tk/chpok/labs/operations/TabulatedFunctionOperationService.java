package ru.ssau.tk.chpok.labs.operations;

import ru.ssau.tk.chpok.labs.functions.Point;
import ru.ssau.tk.chpok.labs.functions.TabulatedFunction;

public class TabulatedFunctionOperationService {
    public static Point[] asPoints(TabulatedFunction tabulatedFunction) {
        int i = 0;
        Point[] points = new Point[tabulatedFunction.getCount()];
        for (Point newPoint : tabulatedFunction) {
            points[i++] = newPoint;
        }
        return points;
    }
}
