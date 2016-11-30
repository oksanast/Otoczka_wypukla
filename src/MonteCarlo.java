import java.util.*;

public class MonteCarlo {

    Point[] wrapPoints = Jarvis.wrapPoints;
    int nWrapPoints = Jarvis.nWrapPoints;
    //number of thrown points
    int pointsThrown = 100;
    Point[] thrownPoints = new Point[pointsThrown];
    //number of points inside the polygon
    static int pointsInside = 0;

    public double calculatedArea() {
        double xmin = wrapPoints[0].x;
        double xmax = wrapPoints[0].x;
        double ymin = wrapPoints[0].y;
        double ymax = wrapPoints[0].y;
        for (int i = 1; i < nWrapPoints; i++) {
            if (wrapPoints[i].x < xmin) xmin = wrapPoints[i].x;
            if (wrapPoints[i].x > xmax) xmax = wrapPoints[i].x;
            if (wrapPoints[i].y < ymin) ymin = wrapPoints[i].y;
            if (wrapPoints[i].y > ymax) ymax = wrapPoints[i].y;
        }

        Random r = new Random();
        for (int i = 0; i < pointsThrown; i++) {
            thrownPoints[i] = new Point(xmin + (xmax - xmin)*r.nextDouble(), ymin + (ymax - ymin)*r.nextDouble());
            if (contains(thrownPoints[i]))
                pointsInside++;
        }

        double squareArea = (xmax - xmin) * (ymax - ymin);
        return (double)(pointsInside)/(double)(pointsThrown)*squareArea;
    }

    public boolean contains(Point test) {
        int i, j;
        boolean result = false;
        for (i = 0, j = nWrapPoints - 1; i < nWrapPoints; j = i++)
             if (((wrapPoints[i].y <= test.y && wrapPoints[j].y > test.y)
                     || (wrapPoints[j].y <= test.y && wrapPoints[i].y > test.y))
                 && (wrapPoints[i].x + (test.y - wrapPoints[i].y)/(wrapPoints[j].y - wrapPoints[i].y)*(wrapPoints[j].x - wrapPoints[i].x) > test.x))
                result = !result;
        return result;
    }

}
