import java.lang.Math;
import java.util.Comparator;

public class PointComparator implements Comparator<Point>{
// help with angular sort: https://en.wikipedia.org/wiki/Graham_scan
  private Point anchor; //= new Point(7,2);

  public PointComparator(Point anchor){
    this.anchor = anchor;
  }
  //returns positive value if a's angle is greater than b's (want it in reverse order)
  //@Override

  public int compare(Point a, Point b){

   double anchorX = (double) anchor.getX();
   double anchorY = (double) anchor.getY();

   double aX = (double) a.getX();
   double aY = (double) a.getY();

   double bX = (double) b.getX();
   double bY = (double) b.getY();

   //double angleA = Math.atan((aY-anchorY)/(anchorX-aX));
   // double angleB = Math.atan((bY-anchorY)/(anchorX-bX));

    double angleA;
    if(a.equals(anchor)){
      angleA = 0;
    }
    else{
      angleA = ((aX-anchorX)/(aY-anchorY));
    }

    double angleB;
    if(b.equals(anchor)){
      angleB = 0;
    }
    else{
      angleB =((bX-anchorX)/(bY-anchorY));
    }

  //  System.out.println("angle between " + a + "is: "+ angleA);
  //  System.out.println("angle between " + b + "is: "+ angleB);

    if(angleA > angleB){
      System.out.println(angleA + "is greater than " + angleB);
      return 1;
    }
    else if (angleB < angleA){
      System.out.println(angleB + "is greater than " + angleA);
      return -1;
    }
    return 0;
  }

  public Point getAnchor(){
    return anchor;
  }

}
