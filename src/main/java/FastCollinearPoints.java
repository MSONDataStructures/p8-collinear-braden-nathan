import java.util.Arrays;
/*************************************************************************
 *   Given a point p, the following method determines
 *   whether p participates in a set of 4 or more collinear points.
 *   Think of p as the origin.
 *   - For each other point q, determine the slope it makes with p.
 *   - Sort the points according to the slopes they makes with p.
 *   - Check if any 3 (or more) adjacent points in 
 *      the sorted order have equal slopes with respect to p. 
 *      If so, these points, together with p, are collinear.
 *************************************************************************/
public class FastCollinearPoints {

    private Point[] segments;
    private int numberOfSegmentsHolder = 0;
    public FastCollinearPoints(Point[] points) {
        // finds all line segments containing 4 points
        // TODO: YOUR CODE HERE
        for (int i = 0; i < points.length; i++) {
            Point mainPoint = points[i];
            Point[] toBeSorted = duplicateArray(points);
            Arrays.sort(toBeSorted, mainPoint.slopeOrder());

            double slopeCheck = mainPoint.slopeTo(toBeSorted[0]);
            int count = 0;
            for (int j = i + 1; j < toBeSorted.length; j++) {
                double slope = mainPoint.slopeTo(toBeSorted[j]);
                if (slope == slopeCheck) {
                    count++;
                }
                if (slope != slopeCheck) {
                    if (count >= 2) {
                        numberOfSegmentsHolder++;
                        count = 0;
                    }
                }
                slopeCheck = slope;
            }
            //deals with edge case of a collinear segment being on the end of the for loop
            if (count >= 2) {
                numberOfSegmentsHolder++;
                count = 0;
            }
        }
    }

    public int numberOfSegments() {
        // the number of line segments
        // TODO: YOUR CODE HERE
        return numberOfSegmentsHolder;
    }

    public LineSegment[] segments() {
        // the line segments
        // TODO: YOUR CODE HERE
        return null;
    }

    public Point[] duplicateArray(Point[] arr) {
        Point[] newArr = new Point[arr.length];
        for (int i = 0; i < arr.length; i++) {
            newArr[i] = arr[i];
        }
        return newArr;
    }
}
