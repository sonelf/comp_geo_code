import java.lang.Math;
import java.util.Comparator;

public class PointComparator implements Comparator<Point>{
// help with angular sort: https://en.wikipedia.org/wiki/Graham_scan
  private Point anchor;

  public PointComparator(Point anchor){
    this.anchor = anchor;
  }
  //returns positive value if a's angle is greater than b's (want it in reverse order)
  @Override
  public int compare(Point a, Point b){

    double angleA = getAngleWithAnchor(a);
    double angleB = getAngleWithAnchor(b);

    System.out.println("angle between " + a + "is: "+ angleA);
    System.out.println("angle between " + b + "is: "+ angleB);

    return (int) (angleA-angleB); //TODO: not correct
  }

  //calculates the angle between the line between the p and the anchor,
  // with y = b, where b = the y coordinate of the anchor
  public double getAngleWithAnchor(Point p){
    if(p.getX() == anchor.getX()){
      return 0;
    }

    else if(p.getX() < anchor.getX()){
      Point tmpPoint = new Point(p.getX(),anchor.getY());
      double adj = anchor.distance(tmpPoint);
      double opp = p.distance(tmpPoint);

      //System.out.println("distances: ");
      //System.out.println(opp);
      //System.out.println(adj);
      return Math.atan(Math.tan(opp/adj));
      //return Math.atan(opp/adj);
    }

    double t1Hyp = anchor.distance(p);
    //kind of hard to explain without the geometry,
    //but basically, i am breaking up the angle into two with right triangles,
    //and then adding them together

    Point tmpAdjPt = new Point(anchor.getX(),p.getY());
    double t1Adj = anchor.distance(tmpAdjPt);

    //System.out.println(t1Adj/t1Hyp);
    double angle1 = Math.acos(Math.cos(t1Adj/t1Hyp));
    //System.out.println("angle1 arc cos: "+ angle1);
    return (double) angle1 + 1.5708;


  }


}
