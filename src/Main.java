import java.util.*;

public class Main {

    public static void main (String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of points: ");
        int n = sc.nextInt();
        Point[] points = new Point[n];

        System.out.print("Random? ");
        if (sc.next().equals("yes")) {
            Random r = new Random();
            for (int i = 0; i < n; i++) {
                points[i] = new Point(r.nextInt(100), r.nextInt(100));
            }
        }
        else {
            System.out.println("Enter " + n + " x,y coordinates");
            for (int i = 0; i < n; i++) {
                points[i] = new Point(sc.nextInt(), sc.nextInt());
            }
        }
        sc.close();

        for (Point p:points)
            System.out.println("("+p.x+", "+p.y+")");

        Jarvis j = new Jarvis();
        j.convexHull(points);

        MonteCarlo mc = new MonteCarlo();
        System.out.println("Area: " + mc.calculatedArea());

    }

}
