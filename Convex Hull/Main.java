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

    System.out.println("points set for test case 1: " + pointsCase1);
    NaiveHull nh1 = new NaiveHull(pointsCase1, true);
    nh1.computeHull();
    GrahamScan gs1 = new GrahamScan(pointsCase1.toArray(new Point[pointsCase1.size()]), true);
    gs1.computeHull();

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

    System.out.println("points set for test case 2: " + pointsCase2);
    NaiveHull nh2 = new NaiveHull(pointsCase2, true);
    nh2.computeHull();
    GrahamScan gs2 = new GrahamScan(pointsCase2.toArray(new Point[pointsCase2.size()]), true);
    gs2.computeHull();

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

    System.out.println("points set for test case 3: " + pointsCase3);
    NaiveHull nh3 = new NaiveHull(pointsCase3, true);
    nh3.computeHull();
    GrahamScan gs3 = new GrahamScan(pointsCase3.toArray(new Point[pointsCase3.size()]), true);
    gs3.computeHull();

    System.out.println("\n\n Random cases: \n\n");

    ArrayList<Point> pointsTestRandom1 = getRandomPoints(100);

    NaiveHull nh4 = new NaiveHull(pointsTestRandom1, false);
    nh4.computeHull();

    GrahamScan gs4 = new GrahamScan(pointsTestRandom1.toArray(new Point[pointsTestRandom1.size()]),false);
    gs4.computeHull();

    ArrayList<Point> pointsTestRandom2 = getRandomPoints(1000);
    NaiveHull nh5 = new NaiveHull(pointsTestRandom2, false);
    nh5.computeHull();
    GrahamScan gs5 = new GrahamScan(pointsTestRandom2.toArray(new Point[pointsTestRandom2.size()]),false);
    gs5.computeHull();

    ArrayList<Point> pointsTestRandom3 = getRandomPoints(10000);
    NaiveHull nh6 = new NaiveHull(pointsTestRandom3, false);
    nh6.computeHull();
    GrahamScan gs6 = new GrahamScan(pointsTestRandom3.toArray(new Point[pointsTestRandom3.size()]),false);
    gs6.computeHull();

    ArrayList<Point> pointsTestRandom4 = getRandomPoints(1000000);
    GrahamScan gs7 = new GrahamScan(pointsTestRandom4.toArray(new Point[pointsTestRandom4.size()]),false);
    gs7.computeHull();


  }

  public static ArrayList<Point> getRandomPoints(int numArgs){
    ArrayList<Point> points = new ArrayList<Point>();
    HashMap<String, Boolean> repeatsMap = new HashMap<String, Boolean>();

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

    }
    return points;
  }
}
