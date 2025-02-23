import java.util.ArrayList;
/*************************************************************************
 *   A program that examines 4 points at a time
 *   and checks whether they all lie on the same line segment, 
 *   returning all such line segments. 
 *   To check whether the 4 points p, q, r, and s are collinear, 
 *   check whether the three slopes between p and q, 
 *   between p and r, and between p and s are all equal.
 *************************************************************************/
public class BruteCollinearPoints {

    private int numberOfSegmentsHolder = 0;
    private ArrayList<LineSegment> lineSegmentsHolder = new ArrayList<>();

    public BruteCollinearPoints(Point[] points) {
        // finds all line segments containing 4 points
        for (int p = 0; p < points.length; p++) {
            for (int q = p + 1; q < points.length; q++) {
                for (int r = q + 1; r < points.length; r++) {
                    for (int s = r + 1; s < points.length; s++) {
                        if (points[p].slopeTo(points[q]) == points[p].slopeTo(points[r]) && points[p].slopeTo(points[s]) == points[q].slopeTo(points[r])) {
                            numberOfSegmentsHolder++;
                            LineSegment temp = new LineSegment(LineSegmentSortLow(points[p], points[q], points[r], points[s]), LineSegmentSortHigh(points[p], points[q], points[r], points[s]));
                            lineSegmentsHolder.add(temp);
                        }
                    }
                }
            }
        }
    }

    public int numberOfSegments() {
        // the number of line segments
        return numberOfSegmentsHolder;
    }

    public LineSegment[] segments() {
        // the line segments
        LineSegment[] segments = new LineSegment[numberOfSegmentsHolder];
        for (int i = 0; i < numberOfSegmentsHolder; i++) {
            segments[i] = lineSegmentsHolder.get(i);
        }
        return segments;
    }
    public Point LineSegmentSortHigh(Point a, Point b, Point c, Point d) {
        Point big_Holder1;
        Point big_Holder2;
        if (a.compareTo(b) == 1) {
            big_Holder1 = a;
        } else {
            big_Holder1 = b;
        }
        if (c.compareTo(d) == 1)  {
            big_Holder2 = c;
        } else {
            big_Holder2 = d;
        }
        if (big_Holder1.compareTo(big_Holder2) == 1) {
            return big_Holder1;
        } else {
            return big_Holder2;
        }
    }
    public Point LineSegmentSortLow(Point a, Point b, Point c, Point d) {
        Point small_Holder1;
        Point small_Holder2;
        if (a.compareTo(b) == -1) {
            small_Holder1 = a;
        } else {
            small_Holder1 = b;
        }
        if (c.compareTo(d) == -1)  {
            small_Holder2 = c;
        } else {
            small_Holder2 = d;
        }
        if (small_Holder1.compareTo(small_Holder2) == -1) {
            return small_Holder1;
        } else {
            return small_Holder2;
        }
    }
}
