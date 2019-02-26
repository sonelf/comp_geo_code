import java.util.ArrayList;
public class PointHelper{

  /*
  Naive part to implemeting whether two points are colinear
  Returns whether point p is in between the two points a and b.
  We assume that when we call this function that all three points are colinear

  if a point is in the middle of two points on a line, there are only so many options it could be,
  depending on the where the a and b are and how the line is oriented.

  We determine which point is the upper point and then compare it against the lower point.
  Sometimes if the point is not in the middle it will not apply to any of these cases,
  in which case we return false.

  */
  public static boolean isInMiddle(Point a, Point b, Point p){
    int aX = a.getX();
    int aY = a.getY();
    int bX = b.getX();
    int bY = b.getY();
    int pX = p.getX();
    int pY = p.getY();

    if(aX -pX >=0  && aY - pY >= 0){
      return (pX - bX >= 0) && (pY - bY >= 0);
    }
    else if(bX - pX >= 0 && bY - pY >=0 ){
      return (pX - aX >= 0)&&(pY - aY >= 0);
    }
    else if((aY -pY )>= 0 && (aX - pX) <= 0){
      return ((pX - bX) <=0) && (pY - bY >= 0);
    }
    else if((bX - pX <=0) && (bY > pY)){
      return (pY - aY) >= 0 && (pX - aX <= 0);
    }
    return false;
  }

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

  public static ArrayList<Point> eliminateLinearPoints(ArrayList<Point> items){
    for(int i = 0; i < items.size()-2;i++){
      Point ptA = items.get(i);
      Point ptB = items.get(i+1);
      Point ptC = items.get(i+2);
      if(PointHelper.isOnLine(ptA,ptC,ptB)){
        if( !ptA.equals(ptB) && !ptA.equals(ptC) && !ptB.equals(ptC) && PointHelper.isInMiddle(ptA,ptC,ptB)){
          items.remove(ptB);
        }
      }
    }

    //manually have to check whether we need to delete the first and last one
    //this assumes valid input, ie, there must be at least 3 points in items
    Point last = items.get(items.size()-1);
    Point secondLast = items.get(items.size()-2);
    Point first = items.get(0);

    if(isOnLine(secondLast,first,last) && isInMiddle(secondLast,first,last)){
      items.remove(last);
    }

    //updating last
    last = items.get(items.size()-1);
    Point second = items.get(1);
    if(isOnLine(last,second,first) && isInMiddle(last,second,first)){
      items.remove(first);
    }
    return items;
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
}
