import java.util.Arrays;
import java.util.ArrayList;

/*
  resource for sorting with a comparator (accessed feb 23rd):
  https://stackoverflow.com/questions/2839137/how-to-use-comparator-in-java-to-sort
*/
public class GrahamScan {

  public static void main(String[] args){

    //TODO: populate points using command line args/circle

    Point [] points = new Point[10];

    Point anchor = getBottomRight(points);

    Arrays.sort(points, new PointComparator()); //anchor should be in here
    ArrayList<Point> hullPoints = new ArrayList<Point>();
    hullPoints.add(anchor); //adding the first point
    hullPoints.add(points[1]); //hull has this point as well TODO: is this right?

    for(i = 2; i < points.length; i ++){
      int size = hullPoints.size();
      if(isLeftOf(hullPoints.get(size-2),hullPoints.get(size-1),points[i])){
        hullPoints.add(points[i]);
      }
      else if(isOnLine(hullPoints.get(size-2),hullPoints.get(size-1),points[i])){
        continue;
      }
      else{
        //go back and delete everything that should not be in the hull
        // rightPoint = points[i]
        // delete everything from hullPoints not to the right of line segment rightPointa
      }
    }

  }

  public Point getAnchor(){ //......
    return anchor;
  }

  public static Point getBottomRight(Point[] points){
    Array of points [points]
    Point anchor = points[0]; //bottommost right
    brX = anchor.getX();
    brY = anchor.getY();

    for(int i = 1; i <points.length;i++){
      Point ptTmp = points.get(i);
      Point ptTmpX = ptTmp.getX();
      Point ptTmpY = ptTmp.getY();

      if(ptTmpX > brX && ptTmpY < brY){
        anchor = ptTmp;
        anchor = ptTmpX;
        anchor = ptTmpY;
      }
    }
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
