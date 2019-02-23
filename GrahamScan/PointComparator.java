import java.lang.Math;
import java.util.Comparator;

public class PointComparator implements Comparator<Point>{

  //returns positive value if a's angle is greater than b's (want it in reverse order)
  @Override
  int compare(Point a, Point b){
    //casting do double so integer division
    // doesn't make everything 0
    Point anchor = Main.getAnchor();

    double anchorX = (double) anchor.getX();
    double anchorY = (double) anchor.getY();

    double aX = (double) a.getX();
    double aY = (double) a.getY();

    double bX = (double) b.getX();
    double bY = (double) b.getY();

    double angleA = Math.atan((aY-anchorY)/(anchorX-aX));
    double angleB = Math.atan((bY-anchorY)/(anchorX-bX));

    return Math.ceil(angleB-angleA); //TODO: not correct
  }


}
