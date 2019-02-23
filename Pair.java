public class Pair{
  Point first;
  Point second;

  public Pair(Point first, Point second){
    this.first = first;
    this.second = second;
  }
  Point getFirst(){
    return first;
  }
  Point getSecond(){
    return second;
  }

  @Override
  public String toString(){
    return "\n["+first.toString() +" , "+ second.toString()+ "]";
  }

  public boolean isFirst(Point element){
    return first.equals(element);
  }

  // public boolean isSecond(Point element){
  //   return second.equals(element);
  // }


}
