import java.lang.Integer;
import java.lang.Math;
import java.lang.System;
import java.util.ArrayList;
import java.util.Random;
import java.util.HashMap;
import java.lang.Double;
import java.lang.Boolean;

public class Main {

  public static final int R = 10000000;
  public static final int FIRST_ELEMENT_IDX = 0;

  public static void main (String[] args){

    ArrayList<Point> pointsCase1 = new ArrayList<Point>();
    pointsCase1.add(new Point(7,16));
    pointsCase1.add(new Point(4,15));
    pointsCase1.add(new Point(5,7));
    pointsCase1.add(new Point(6,11));
    pointsCase1.add(new Point(12,18));
    pointsCase1.add(new Point(12,5));
    pointsCase1.add(new Point(17,11));
    pointsCase1.add(new Point(8,5));
    pointsCase1.add(new Point(9,9));
    pointsCase1.add(new Point(13,13));
    pointsCase1.add(new Point(7,2));
    pointsCase1.add(new Point(3,3));
    pointsCase1.add(new Point(2,10));
    pointsCase1.add(new Point(10,17));
    pointsCase1.add(new Point(15,15));
    pointsCase1.add( new Point(15,8));
    pointsCase1.add(new Point(13,3));
    pointsCase1.add(new Point(10,13));

    computeHull(pointsCase1);


    ArrayList<Point> pointsCase2 = new ArrayList<Point>();
    pointsCase2.add(new Point(8, 5));
    pointsCase2.add(new Point(-9, 3));
    pointsCase2.add(new Point(-9, 4));
    pointsCase2.add(new Point(-1, 9));
    pointsCase2.add(new Point(4, 9));
    pointsCase2.add(new Point(6, 7));
    pointsCase2.add(new Point(8, -4));
    pointsCase2.add(new Point(-4, 8));
    pointsCase2.add(new Point(3, -9));

    computeHull(pointsCase2);

    ArrayList<Point> pointsTestRandom1 = getRandomPoints(10);
    System.out.println(pointsTestRandom1);
    computeHull(pointsTestRandom1);

    ArrayList<Point> pointsTestRandom2 = getRandomPoints(10000);
    computeHull(pointsTestRandom2);

    ArrayList<Point> pointsTestRandom3 = getRandomPoints(40000);
    computeHull(pointsTestRandom3);


  }

  public static ArrayList<Point> getRandomPoints(int numArgs){
    ArrayList<Point> points = new ArrayList<Point>();
    HashMap<String, Boolean> repeatsMap = new HashMap<String, Boolean>();

    //TODO: ensure all points are unique
    //https://stackoverflow.com/questions/9879258/how-can-i-generate-random-points-on-a-circles-circumference-in-javascript
    int i = 0;
    while(i < numArgs){
      double angle = (double)(Math.random()*Math.PI*2);
      int x = (int)(Math.cos(angle)*R);
      int y = (int)(Math.sin(angle)*R);
      Point tmpPoint = new Point(x,y);
      String tmpPointStr = tmpPoint.toString();
      if(!repeatsMap.containsKey(tmpPointStr)){
        i++;
        points.add(tmpPoint);
        repeatsMap.put(tmpPointStr,true);
      }
      //System.out.println(i);

    }

    return points;
  }

  public static ArrayList<Point> getSortedHull(ArrayList<Point> points){
    ArrayList<Pair> hullPairs = new ArrayList<Pair>();

    long startTime = System.currentTimeMillis();
    for(int i = 0; i < points.size(); i++){
      Point a = points.get(i);
      for(int j = 0; j < points.size(); j++){
        Point b = points.get(j);
          if(isOnHull(a,b,points)){
            hullPairs.add(new Pair(a,b));
          }
      }
    }

    hullPairs = removeDuplicates(hullPairs);

    ArrayList<Point> pointsSorted = new ArrayList<Point>();
    Pair firstPair = hullPairs.get(FIRST_ELEMENT_IDX);

    Point nextStart = firstPair.getSecond();

    //adding first two points in counter clock-wise order
    Point firstStart = firstPair.getFirst();
    pointsSorted.add(firstStart);
    pointsSorted.add(nextStart);

    hullPairs.remove(firstPair);

    while(!hullPairs.isEmpty()){
    //for(int j = 0; j< hullPairs.size(); j++){
      for(int i = 0; i < hullPairs.size(); i ++){
        Pair tmpPair = hullPairs.get(i);

        if(tmpPair.getFirst().equals(nextStart)){
          pointsSorted.add(tmpPair.getSecond());
          nextStart = tmpPair.getSecond();
          hullPairs.remove(tmpPair);
        }
      }
      //  System.out.println(pointsSorted);
      //System.out.println(hullPairs);
    }

    // removing double counted last element
    pointsSorted.remove(pointsSorted.size()-1);


    long endTime = System.currentTimeMillis();

    System.out.println("Size of point set: "+ points.size());
    System.out.println("Size of hull: "+ pointsSorted.size());
    System.out.println("Time to calculate hull: "+ (endTime-startTime)+ " ms");
    //System.out.println("hull" +pointsSorted);
    return pointsSorted;
  }

  public static void computeHull(ArrayList<Point> points){
    //long startTime = System.currentTimeMillis();
    ArrayList<Point> hullSorted = getSortedHull(points);
    //long endTime = System.currentTimeMillis();

    //System.out.println("Size of point set: "+ points.size());
    //System.out.println("Size of hull: "+ hullSorted.size());
    //System.out.println("Time to calculate hull: "+ (endTime-startTime)+ " ms");
    //System.out.println(hullSorted);
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
        //  System.out.println(a + " , "+ b + "is not a pair on the hull: tmp: " + tmp);
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
    // if slope == 0 (ie x=c), then return whether
    // the point is to the left of ab
    if(run == 0){
      if(aY > bY){
        return pX > aX;
      }
      return pX < aX;
    }

    // creating the line we use to compare p against
    double rise = (double)(bY-aY); //delta Y for line
    double slope = rise/run;

    if(slope == 0){
      if(aX > bX){
        return pY <= aY;
      }
      return pY >= aY;
    }

    double yIntercept = (bY - (slope * bX));
    double tmpY = (slope*pX)+yIntercept;
    double pyDouble = (double) pY;
    // since "left" of the line depends on the orientation of the points,
    // there are several cases to consider

    if(bX > aX && bY > aY){
      return pyDouble - tmpY >=0 ;
    }
    if(bX < aX && bY < aY){
      return pyDouble - tmpY <= 0;
    }
    if(bY < aY && bX > aX){
      return pyDouble - tmpY >= 0;
    }
    return pyDouble - tmpY <= 0;
  }

  public static ArrayList<Pair> removeDuplicates(ArrayList<Pair> items){
    ArrayList<Pair> tmp = new ArrayList<Pair>();
    HashMap<String, Double> repeatsMap = new HashMap<String, Double>();
    for(int i = 0; i < items.size(); i++){
      Pair tmpPair = items.get(i);
      String firstPoint = tmpPair.getFirst().toString();
      double tmpDist = tmpPair.getFirst().distance(tmpPair.getSecond());
      if(!repeatsMap.containsKey(firstPoint)){
        repeatsMap.put(firstPoint, tmpDist);
      }else{
        if(repeatsMap.get(firstPoint) > tmpDist ){
          repeatsMap.put(firstPoint, tmpDist);
        }
      }

    }

    for(int i = 0; i < items.size(); i++){
      Pair tmpPair = items.get(i);
      String firstPoint = tmpPair.getFirst().toString();
      double tmpDist = tmpPair.getFirst().distance(tmpPair.getSecond());

      if(repeatsMap.get(firstPoint)==tmpDist){
        tmp.add(tmpPair);
      }
    }

    return tmp;
  }

}
