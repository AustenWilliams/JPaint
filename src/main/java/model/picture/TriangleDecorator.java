package model.picture;

import java.awt.Color;
import java.awt.Graphics2D;
import model.ShapeColor;
import model.ShapeShadingType;
import model.interfaces.Shape;

public class TriangleDecorator implements Shape {

  private ShapeShadingType shadingType;
  private ShapeColor color;
  private int[] xCoord;
  private int[] yCoord;
  private int points;


  public TriangleDecorator(ShapeDecorator shapeDecorator){
    this.xCoord = shapeDecorator.getXCoord();
    this.yCoord = shapeDecorator.getYCoord();
    this.points = 3;
    this.color = shapeDecorator.getColor();
    this.shadingType = shapeDecorator.getShadingType();
  }


  @Override
  public void draw(Graphics2D graphics) {
    graphics.setColor(color.value);
    switch (shadingType){
      case FILLED_IN:
        graphics.fillPolygon(xCoord, yCoord, points);
        break;
      case OUTLINE:
        graphics.drawPolygon(xCoord, yCoord, points);
        break;
      case OUTLINE_AND_FILLED_IN:
        graphics.fillPolygon(xCoord, yCoord, points);
        graphics.setColor(Color.red);
        graphics.drawPolygon(xCoord, yCoord, points);
        break;
      default:
        graphics.fillPolygon(xCoord, yCoord, points);
        break;
    }

  }
}
