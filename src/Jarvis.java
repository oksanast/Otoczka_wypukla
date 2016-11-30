import java.util.*;

public class Jarvis {

    public static int nWrapPoints = 0;
    public static Point[] wrapPoints = new Point[1000];

    private boolean CCW(Point p, Point q, Point r) {
        double val = (q.y - p.y)*(r.x - q.x) - (q.x - p.x)*(r.y - q.y);
        return (val < 0);
    }

    public void convexHull(Point[] points) {
        int n = points.length;
        if (n < 3)
            return;
        int[] wrap = new int[n];
        Arrays.fill(wrap, -1);

        int leftMost = 0;
        for (int i = 1; i < n; i++)
            if (points[i].x < points[leftMost].x)
                leftMost = i;
        int p = leftMost, q;

        do
        {
            wrapPoints[nWrapPoints] = points[p];
            nWrapPoints++;
            q = (p + 1) % n;
            for (int i = 0; i < n; i++)
                if (CCW(points[p], points[i], points[q]))
                    q = i;

            p = q;
        } while (p != leftMost);

        display();

    }

    public void display() {
        System.out.println("\nOtoczka: ");
        for (int i = 0; i < nWrapPoints; i++)
                System.out.println("(" + wrapPoints[i].x + ", " + wrapPoints[i].y + ")");
    }

}
