import java.util.Arrays;
import java.util.ArrayList;
import java.util.Stack;

/*
  resource for sorting with a comparator (accessed feb 23rd):
  https://stackoverflow.com/questions/2839137/how-to-use-comparator-in-java-to-sort
*/
public class GrahamScan {

  //static Point anchor = null;

  public static void main(String[] args){


    Point [] points = new Point[18];
    points[0] = new Point(7,16);
    points[12] = new Point(2,10);
    points[1] = new Point(4,15);
    points[2] = new Point(5,7);
    points[3] = new Point(6,11);
    points[4] = new Point(12,18);
    points[5] = new Point(12,5);
    points[6] = new Point(17,11);
    points[7] = new Point(8,5);
    points[8] = new Point(9,9);
    points[9] = new Point(13,13);
    points[10] = new Point(7,2);
    points[11] = new Point(3,3);

    points[13] = new Point(10,17);
    points[14] = new Point(15,15);
    points[15] = new Point(15,8);
    points[16] = new Point(13,3);
    points[17] = new Point(10,13);

    Point anchor = getBottomRight(points);
    System.out.println("anchor: "+ anchor);
    PointComparator pc = new PointComparator(anchor);
    Arrays.sort(points, pc); //anchor should be in here

    Stack<Point> hullPoints = new Stack<Point>();

    hullPoints.push(anchor);
    hullPoints.push(points[0]); //there are at least two elements in the convex hull

    //System.out.println(hullPoints.size());

    Point lineA = anchor;
    Point lineB = points[0];
    for(int i = 1; i < points.length; i ++){
      if(points[i].equals(anchor)) continue;

      if(isLeftOf(lineA,lineB,points[i])){
        hullPoints.push(points[i]);
        lineA = lineB;
        lineB = points[i];
      }
      else if(isOnLine(lineA,lineB,points[i])){
        continue;
      }
      else{ //is to the right
          //System.out.println("to the right: "+points[i]);
          //pop things from the stack until we it is no longer to the isLeft
          lineB = points[i];
          Point testElement = hullPoints.pop();

          while (isLeftOf(lineA, lineB, testElement)) {
            testElement = hullPoints.pop();
            lineA = hullPoints.peek();
          }
          hullPoints.push(testElement);
          //reset the A line segment
          lineA = testElement;
          hullPoints.push(points[i]);
        }
      }
      System.out.println(hullPoints);
      System.out.println(hullPoints.size());
    }



  public static Point getBottomRight(Point[] points){
  //  Array of points [points]
    Point anchor = points[0]; //bottommost right
    int brY = anchor.getY();

    for(int i = 1; i <points.length;i++){
      Point ptTmp = points[i];
      int ptTmpY = ptTmp.getY();

      if(ptTmpY < brY){
        anchor = ptTmp;
        brY = ptTmpY;
      }
    }
    return anchor;
  }
  //return true if p is to the left of the line segment ab
  public static boolean isLeftOf(Point a, Point b, Point p){
    int aX = a.getX();
    int aY = a.getY();
    int bX = b.getX();
    int bY = b.getY();
    int pX = p.getX();
    int pY = p.getY();

    double run = (double)(bX-aX); //how far away are a and b in the x-direction?

    if(run == 0){ // if slope is zero (ie it's just x = c), then just say whether point is to the left of that line
      if(aY > bY){
        return pX > aX;
      }
      return pX < aX;
    }

    // creating the line we use to compare p against
    double rise = (double)(bY-aY); //delta Y for line
    double slope = rise/run;

    double yIntercept = (bY - (slope * bX));
    double tmpY = (slope*pX)+yIntercept;

    // since "left" of the line depends on the orientation of the points,
    // there are several cases to consider.
    //removed >= or <= because checking whether it is actually
    // on the line is a separate case.

    if(bX > aX && bY > aY){
      return pY > tmpY;
    }
    if(bX < aX && bY < aY){
      return pY < tmpY;
    }
    if(bY < aY && bX > aX){
      return pY > tmpY;
    }
    return pY < tmpY;
  }

  //return true if p is on the line segment ab
  public static boolean isOnLine(Point a, Point b, Point p){
    int aX = a.getX();
    int aY = a.getY();
    int bX = b.getX();
    int bY = b.getY();
    int pX = p.getX();
    int pY = p.getY();

    double run = (double)(bX-aX); //how far away are a and b in the x-direction?

    if(run == 0){ // if slope is zero (ie it's just x = c),
    //then check if the x-coordinate of p is the same
      return pX == aX;

    }

    // creating the line we use to compare p against
    double rise = (double)(bY-aY); //delta Y for line
    double slope = rise/run;

    double yIntercept = (bY - (slope * bX));
    double tmpY = (slope*pX)+yIntercept;

    return pY == tmpY;
  }
}
