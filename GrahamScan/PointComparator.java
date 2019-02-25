import java.lang.Math;
import java.util.Comparator;

public class PointComparator implements Comparator<Point>{
// help with angular sort: https://en.wikipedia.org/wiki/Graham_scan
// https://stackoverflow.com/questions/16509100/sorting-points-by-their-polar-angle-in-java
  private Point anchor; //= new Point(7,2);

  public PointComparator(Point anchor){
    this.anchor = anchor;
  }

  // returns positive value if a's angle is
  // greater than b's (want it in reverse order)

  @Override
  public int compare(Point a, Point b){
    double anchorX = (double) anchor.getX();
    double anchorY = (double) anchor.getY();

    double aX = (double) a.getX();
    double aY = (double) a.getY();

    double bX = (double) b.getX();
    double bY = (double) b.getY();

    //setting up angles:
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
    //comparing the angles
    if(angleA - angleB > 0){
      return -1;
    }
    else if (angleA - angleB < 0){
      return 1;
    }
    return 0;
 }

  public Point getAnchor(){
    return anchor;
  }

}
