import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints {

    private final ArrayList<LineSegment> lineSegments = new ArrayList<>();

    public FastCollinearPoints(Point[] points) {
        // check for null
        if (points == null) {
            throw new IllegalArgumentException("array is null");
        }

        // copy array and check for nulls
        Point[] pointsCopy = new Point[points.length];
        for (int i = 0; i < points.length; i++) {
            if (points[i] == null) {
                throw new IllegalArgumentException("point is null");
            }
            pointsCopy[i] = points[i];
        }

        // check for duplicates
        Point[] sortedPoints = pointsCopy.clone();
        Arrays.sort(sortedPoints);
        for (int i = 0; i < sortedPoints.length - 1; i++) {
            if (sortedPoints[i].compareTo(sortedPoints[i + 1]) == 0) {
                throw new IllegalArgumentException("duplicate points");
            }
        }

        // find collinear points for each point
        for (int i = 0; i < pointsCopy.length; i++) {
            Point p = pointsCopy[i];

            // get other points and sort by slope
            Point[] otherPoints = new Point[pointsCopy.length - 1];
            int idx = 0;
            for (int j = 0; j < pointsCopy.length; j++) {
                if (j != i) {
                    otherPoints[idx++] = pointsCopy[j];
                }
            }

            Arrays.sort(otherPoints, p.slopeOrder());

            // find segments
            int j = 0;
            while (j < otherPoints.length) {
                double slope = p.slopeTo(otherPoints[j]);

                ArrayList<Point> collinear = new ArrayList<>();
                collinear.add(p);
                do {
                    collinear.add(otherPoints[j]);
                    j++;
                } while (j < otherPoints.length && p.slopeTo(otherPoints[j]) == slope);

                // if 4+ points
                if (collinear.size() >= 4) {
                    collinear.sort(null);

                    // only add if p is min point to avoid duplicates
                    if (collinear.get(0).compareTo(p) == 0) {
                        lineSegments.add(new LineSegment(
                                collinear.get(0),
                                collinear.get(collinear.size() - 1)
                        ));
                    }
                }
            }
        }
    }

    public int numberOfSegments() {
        return lineSegments.size();
    }

    public LineSegment[] segments() {
        return lineSegments.toArray(new LineSegment[0]);
    }
}