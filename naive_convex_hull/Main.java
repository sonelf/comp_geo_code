import java.lang.Integer;
import java.lang.System;
import java.util.ArrayList;
import java.util.Random;

public class Main {

  public static final int N = 100000000;
  public static final int FIRST_ELEMENT_IDX = 0;

  public static void main (String[] args){
    ArrayList<Point> points = new ArrayList<Point>();

    int numArgs = Integer.parseInt(args[FIRST_ELEMENT_IDX]);
    Random ranNumGenerator = new Random();

    for(int i = 0; i < numArgs; i++){
      int coord1 = ranNumGenerator.nextInt(N)+1; //adding 1 to ensure non-zero values
      int coord2 = ranNumGenerator.nextInt(N)+1;
      points.add(new Point(coord1, coord2));

    }

    //set up timer
    long startTime = System.currentTimeMillis();

    ArrayList<Pair> hullPairs = new ArrayList<Pair>();

    for(int i = 0; i < points.size(); i++){
      Point a = points.get(i);
      for(int j = 0; j < points.size(); j++){
        Point b = points.get(j);
          if(isOnHull(a,b,points)){
            hullPairs.add(new Pair(a,b));
          }
      }
    }

    System.out.println("Size of point set: "+ points.size());
    System.out.println("Size of hull: "+ hullPairs.size());

    long endTime = System.currentTimeMillis();
    System.out.println("Time to calculate hull: "+ (endTime-startTime)+ " ms");

    ArrayList<Point> pointsSorted = new ArrayList<Point>();
    Pair firstPair = hullPairs.get(FIRST_ELEMENT_IDX);

    Point nextStart = firstPair.getSecond();

    //adding first two points in counter clock-wise order
    pointsSorted.add(firstPair.getFirst());
    pointsSorted.add(nextStart);

    hullPairs.remove(firstPair);

    while(!hullPairs.isEmpty()){
      for(int i = 0; i < hullPairs.size(); i ++){
        Pair tmpPair = hullPairs.get(i);
        if(tmpPair.getFirst().equals(nextStart)){
          pointsSorted.add(tmpPair.getSecond());
          nextStart = tmpPair.getSecond();
          hullPairs.remove(tmpPair);
        }
      }
    }

    // removing double counted last element
    pointsSorted.remove(pointsSorted.size()-1);
    System.out.println(pointsSorted);

  }

  public static boolean isOnHull(Point a, Point b, ArrayList<Point>points){
    for(int i = 0; i < points.size(); i++){
      Point tmp = points.get(i);
      if(a.equals(b) || a.equals(tmp) || b.equals(tmp)){
        if(a.equals(b)) return false;
        continue;
      }
      else{
        if(!isLeftOf(a,b,tmp)){
          return false;
        }
      }
    }
    return true;
  }

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
    // there are several cases to consider

    if(bX > aX && bY > aY){
      return pY >= tmpY;
    }
    if(bX < aX && bY < aY){
      return pY < tmpY;
    }
    if(bY < aY && bX > aX){
      return pY >= tmpY;
    }
    return pY < tmpY;
  }

}
