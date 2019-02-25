public class GrahamScan {
  public static void main(String[] args){

    Point [] points = new Point[10];
    Point br = getBottomRight(points);

  }

  public static Point getBottomRight(Point[] points){
    Array of points [points]
    Point br = points[0]; //bottommost right
    brX = br.getX();
    brY = br.getY();

    for(int i = 1; i <points.length;i++){
      Point ptTmp = points.get(i);
      Point ptTmpX = ptTmp.getX();
      Point ptTmpY = ptTmp.getY();

      if(ptTmpX > brX && ptTmpY < brY){
        br = ptTmp;
        brX = ptTmpX;
        brY = ptTmpY;
      }
    }
  }




}
