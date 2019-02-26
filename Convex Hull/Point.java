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
    return Math.sqrt(Math.pow(x-p.getX(),2)+Math.pow(y-p.getY(),2));
  }

  public double magnitude(){
    return this.distance(new Point(0,0));
  }
}
