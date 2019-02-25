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

    Point [] pointsCase1 = new Point[18];
    pointsCase1[0] = new Point(7,16);
    pointsCase1[1] = new Point(4,15);
    pointsCase1[2] = new Point(5,7);
    pointsCase1[3] = new Point(6,11);
    pointsCase1[4] = new Point(12,18);
    pointsCase1[5] = new Point(12,5);
    pointsCase1[6] = new Point(17,11);
    pointsCase1[7] = new Point(8,5);
    pointsCase1[8] = new Point(9,9);
    pointsCase1[9] = new Point(13,13);
    pointsCase1[10] = new Point(7,2);
    pointsCase1[11] = new Point(3,3);
    pointsCase1[12] = new Point(2,10);
    pointsCase1[13] = new Point(10,17);
    pointsCase1[14] = new Point(15,15);
    pointsCase1[15] = new Point(15,8);
    pointsCase1[16] = new Point(13,3);
    pointsCase1[17] = new Point(10,13);
    //TODO

    Stack<Point> hullCase1 = getHull(pointsCase1);
    System.out.println(hullCase1);

    Point[] pointsCase2 = new Point[8];
    pointsCase2[0] = new Point(2,10);
    pointsCase2[1] = new Point(3,3);
    pointsCase2[2] = new Point(4,15);
    pointsCase2[3] = new Point(7,2);
    pointsCase2[4] = new Point(12,18);
    pointsCase2[5] = new Point(13,3);
    pointsCase2[6] = new Point(15,15);
    pointsCase2[7] = new Point(17,11);

    Stack<Point> hullCase2 = getHull(pointsCase2); //should be same hull as case 1
    System.out.println(hullCase2);

    Point[] pointsCase3 = new Point[8];
    pointsCase3[0] = new Point(1,1);
    pointsCase3[1] = new Point(21,1);
    pointsCase3[2] = new Point(11,23);
    pointsCase3[3] = new Point(5,5);
    pointsCase3[4] = new Point(2,2);
    pointsCase3[5] = new Point(3,3);
    pointsCase3[6] = new Point(15,5);
    pointsCase3[7] = new Point(6,12);

    Stack<Point> hullCase3 = getHull(pointsCase3); //should be same hull as case 1
    System.out.println(hullCase3);

    //TODO: do random cases

  }

  public static Stack<Point> getHull (Point[] points){
    Point anchor = getBottomRight(points);
    Arrays.sort(points,  new PointComparator(anchor)); //anchor should be in here
    return generateHull(points,anchor);
  }

  public static Stack<Point> generateHull(Point[] points, Point anchor){
    Stack<Point> hullPoints = new Stack<Point>();

    points = removeDuplicateAngles(points, anchor);
    hullPoints.push(anchor);
    hullPoints.push(points[0]); //there are at least two elements in the convex hull

    Point lineA = anchor; //to keep track of line segment AB
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

  public static Point[] removeDuplicateAngles(Point[] points, Point anchor){
    ArrayList<Point> tmp = new ArrayList<Point>();
    PointComparator pc = new PointComparator(anchor);
    for(int i = 0; i < points.length -1 ; i++){
      Point pt1 = points[i];
      Point pt2 = points[i+1];
      if(pc.compare(pt1, pt2) == 0){
        if(pt1.magnitude() > pt2.magnitude()){
          tmp.add(pt1);
        }
        else{
          tmp.add(pt2);
        }
        i++;
      }
      else{
        tmp.add(pt1);
        if(i == points.length -2){ //TODO: this fix is too hacky
          tmp.add(pt2);
        }
      }
    }
    //System.out.println("new array: "); //TODO: delete
    //System.out.println(tmp);
    //System.out.println("end");
    //return points;
    return tmp.toArray(new Point[tmp.size()]);
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
