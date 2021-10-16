package model.picture;

import java.awt.Graphics2D;
import model.ShapeColor;
import model.interfaces.Shape;

public class TriangleDecorator implements Shape {

  private ShapeColor color;
  private int[] xCoord;
  private int[] yCoord;
  private int points;

  public TriangleDecorator(ShapeDecorator shapeDecorator){
    this.xCoord = shapeDecorator.getXCoord();
    this.yCoord = shapeDecorator.getYCoord();
    this.points = 3;
    this.color = shapeDecorator.getColor();
  }


  @Override
  public void draw(Graphics2D graphics) {
    graphics.setColor(color.value);
    graphics.fillPolygon(xCoord, yCoord, points);
  }
}
