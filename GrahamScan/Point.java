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

  public double magnitude(){
    return Math.sqrt(Math.pow(x,2)+Math.pow(y,2));
  }

}
