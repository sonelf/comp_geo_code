import java.lang.Math;
import java.util.Comparator;

class PointComparator implements Comparator<Point>{

  //returns positive value if a's angle is greater than b's
  int compare(Point anchor, Point a, Point b){
    //casting do double so integer division
    // doesn't make everything 0

    double anchorX = (double) anchor.getX();
    double anchorY = (double) anchor.getY();

    double aX = (double) a.getX();
    double aY = (double) a.getY();

    double bX = (double) b.getX();
    double bY = (double) b.getY();

    double angleA = Math.atan((aY-anchorY)/(anchorX-aX));
    double angleB = Math.atan((bY-anchorY)/(anchorX-bX));

    return Math.ceil(angleA-angleB);
  }
}
