package controller.command;

import controller.interfaces.Select;
import java.util.ArrayList;
import java.util.List;
import model.interfaces.Shape;
import model.picture.Point;
import model.picture.ShapeImpl;

public class SelectImpl implements Select {

  private List<Shape> shapeList;
  private List<Shape> selectList;
  private Point start;
  private Point end;

  public SelectImpl(List<Shape> shapeList, Point start, Point end){
    this.normalizePoints(start, end);
    this.shapeList = shapeList;
    this.selectList = new ArrayList<Shape>();
  }



  @Override
  public List<Shape> getList() {
    findSelections();
    return this.selectList;
  }

  private void normalizePoints(Point firstPoint, Point endPoint) {
    int newStartX;
    int newStartY;
    int newEndX;
    int newEndY;

    // Calculated new X values
    if (firstPoint.getX() > endPoint.getX()) {
      newStartX = endPoint.getX();
      newEndX = firstPoint.getX();
    } else {
      newStartX = firstPoint.getX();
      newEndX = endPoint.getX();
    }

    // Calculate new Y values
    if (firstPoint.getY() > endPoint.getY()) {
      newStartY = endPoint.getY();
      newEndY = firstPoint.getY();
    } else {
      newStartY = firstPoint.getY();
      newEndY = endPoint.getY();
    }

    start = new Point(newStartX, newStartY);
    end = new Point(newEndX, newEndY);
  }

  private void findSelections(){
    for (Shape s: shapeList){
      ShapeImpl shape = (ShapeImpl) s;
      Point shapeStart = shape.getStart();
      Point shapeEnd = shape.getEnd();
      int shapeStartX = shapeStart.getX();
      int shapeStartY = shapeStart.getY();
      boolean xBounds = (start.getX() < (shapeStartX + shape.getWidth())) && ((start.getX()  + (end.getX() - start.getX())) > shapeStartX);
      boolean yBounds = ((start.getY() < (shapeStartY + shape.getHeight())) && ((start.getY() + (end.getY() - start.getY())) > shapeStartY));
      if (xBounds && yBounds) {
        selectList.add(shape);
        System.out.println("Within x and y bounds lIST = " + selectList.toString());
        }
      else{
        System.out.println("Not in bounds");
      }

      //System.out.println("In findSelections method StartX : " + shape.getStart().getX() + " StartY: " + shape.getStart().getY() + " EndX: " + shape.getEnd().getX() + " EndY: " + shape.getEnd().getY());
    }
    //System.out.println("Mouse in select mode. StartPoint: " + start + " EndPoint: " + end);
  }

}
