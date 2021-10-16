package model.picture;

import java.awt.Color;
import java.awt.Graphics2D;
import model.ShapeColor;
import model.ShapeShadingType;
import model.interfaces.Shape;

public class EllipseDecorator implements Shape {

  private ShapeShadingType shadingType;
  private ShapeColor color;
  private int startX;
  private int startY;
  private int width;
  private int height;

  public EllipseDecorator(ShapeDecorator shapeDecorator){
    this.color = shapeDecorator.getColor();
    this.startX = shapeDecorator.getStartX();
    this.startY = shapeDecorator.getStartY();
    this.width = shapeDecorator.getWidth();
    this.height = shapeDecorator.getHeight();
    this.shadingType = shapeDecorator.getShadingType();
  }


  @Override
  public void draw(Graphics2D graphics) {
    graphics.setColor(color.value);
    switch (shadingType){
      case FILLED_IN:
        graphics.fillOval(startX, startY, width, height);
        break;
      case OUTLINE:
        graphics.drawOval(startX, startY, width, height);
        break;
      case OUTLINE_AND_FILLED_IN:
        graphics.fillOval(startX, startY, width, height);
        graphics.setColor(Color.red);
        graphics.drawOval(startX, startY, width, height);
        break;
      default:
        graphics.fillOval(startX, startY, width, height);
        break;
    }

  }
}
