import java.lang.Math;

public class Point {
  private int x;
  private int y;

  public Point(int x, int y){
    this.x = x;
    this.y = y;
  }

  public int getX(){
    return x;
  }
  public int getY(){
    return y;
  }
  public String toString(){
    return "("+x+", "+y+")";
  }
  public boolean equals(Point p){
    return p.getX() == x && p.getY() == y;
  }

  public double distance(Point p){
    int pX = p.getX();
    int pY = p.getY();

    return Math.sqrt(Math.pow(pX-x,2)+Math.pow(pY-y,2));
  }

  public int dotProduct(Point p){
    return (x*p.getX())+(y*p.getY());
  }

  public double magnitude(){
    return Math.sqrt(Math.pow(x,2)+Math.pow(y,2));
  }

}
