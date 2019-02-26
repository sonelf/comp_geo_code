import java.util.ArrayList;
import java.util.HashMap;
public class Main{


  public static final int R = 100000000;
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

    NaiveHull.computeHull(pointsCase1, true);
    GrahamScan.computeHull(pointsCase1.toArray(new Point[pointsCase1.size()]), true);

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

  //  NaiveHull.computeHull(pointsCase2, true);
    //GrahamScan.computeHull(pointsCase2.toArray(new Point[pointsCase2.size()]), true);

    ArrayList<Point> pointsCase3 = new ArrayList<Point>();
    pointsCase3.add(new Point(2, 2));
    pointsCase3.add(new Point(-2, 2));
    pointsCase3.add(new Point(4, 4));
    pointsCase3.add(new Point(-4, 4));
    pointsCase3.add(new Point(6, 6));
    pointsCase3.add(new Point(-6, 6));
    pointsCase3.add(new Point(8, 8));
    pointsCase3.add(new Point(-8, 8));
    pointsCase3.add(new Point(10, 1));
    pointsCase3.add(new Point(0, 0));


    //NaiveHull.computeHull(pointsCase3, true);
    //GrahamScan.computeHull(pointsCase3.toArray(new Point[pointsCase3.size()]), true);

    ArrayList<Point> pointsTestRandom1 = getRandomPoints(100);
    //NaiveHull.computeHull(pointsTestRandom1, false);
    //GrahamScan.computeHull(pointsTestRandom1.toArray(new Point[pointsTestRandom1.size()]), false);

    ArrayList<Point> pointsTestRandom2 = getRandomPoints(1000);
    //NaiveHull.computeHull(pointsTestRandom2, false);
    //GrahamScan.computeHull(pointsTestRandom2.toArray(new Point[pointsTestRandom2.size()]), false);

    ArrayList<Point> pointsTestRandom3 = getRandomPoints(10000);
    //NaiveHull.computeHull(pointsTestRandom3, false);
    //GrahamScan.computeHull(pointsTestRandom3.toArray(new Point[pointsTestRandom3.size()]), false);

    ArrayList<Point> pointsTestRandom4 = getRandomPoints(20000);
    //NaiveHull.computeHull(pointsTestRandom4, false);
  //  GrahamScan.computeHull(pointsTestRandom4.toArray(new Point[pointsTestRandom4.size()]), false);

    ArrayList<Point> pointsTestRandom5 = getRandomPoints(100000);
  //  GrahamScan.computeHull(pointsTestRandom5.toArray(new Point[pointsTestRandom5.size()]), false);

    ArrayList<Point> pointsTestRandom6 = getRandomPoints(1000000);
    //GrahamScan.computeHull(pointsTestRandom6.toArray(new Point[pointsTestRandom6.size()]), false);


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
}
