import java.lang.System;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Stack;

/*
  resource for sorting with a comparator (accessed feb 23rd):
  https://stackoverflow.com/questions/2839137/how-to-use-comparator-in-java-to-sort
*/
public class GrahamScan {

  public static void computeHull(Point[] points, boolean printHull){
    long startTime = System.currentTimeMillis();

    Stack<Point> hullStack = getHullStack(points);

    long endTime = System.currentTimeMillis();

    System.out.println("Graham Scan: ");
    System.out.println("Size of point set: "+ points.length);
    System.out.println("Size of hull: "+ hullStack.size());
    System.out.println("Time to calculate Graham Scan hull: "+ (endTime-startTime)+ " ms");

    if(printHull){
        System.out.println(hullStack);
    }

  }

  // takes an array of points
  // and computes the convex hull
  public static Stack<Point> getHullStack (Point[] points){
    Point anchor = getBottomRight(points);
    Arrays.sort(points,  new PointComparator(anchor)); //anchor should be in here

    return generateHull(points,anchor);
  }

  public static Stack<Point> generateHull(Point[] points, Point anchor){
    Stack<Point> hullPoints = new Stack<Point>();

    hullPoints.push(anchor);
    int startIdx = 0;
    //if the bottomost point is also the bottomrightmost
    // point, then the anchor will be added twice  without this guard
    // because zero will be the largest angle
    if(points[startIdx].equals(anchor)){
      startIdx++;
    }
    hullPoints.push(points[startIdx]); //there are at least two elements in the convex hull

    Point lineA = anchor; //to keep track of line segment AB
    Point lineB = points[startIdx];
    for(int i = startIdx+1; i < points.length; i ++){
      if(points[i].equals(anchor)) continue;

      if(isLeftOf(lineA,lineB,points[i])){
        hullPoints.push(points[i]);
        lineA = lineB;
        lineB = points[i];
      }
      else{ //is to the right
          //pop things from the stack until it is no longer to the left
          lineB = points[i];
          Point testElement = hullPoints.pop();

          while (isLeftOf(lineA, lineB, testElement)) {
            testElement = hullPoints.pop();
            lineA = hullPoints.peek();
          }

          hullPoints.push(testElement);
          lineA = testElement;
          hullPoints.push(points[i]);
        }
      }
      return hullPoints;
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
