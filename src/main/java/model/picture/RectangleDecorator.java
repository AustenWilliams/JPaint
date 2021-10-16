package model.picture;

import java.awt.Graphics2D;
import model.ShapeColor;
import model.interfaces.Shape;

public class RectangleDecorator implements Shape {

  private ShapeColor color;
  private int startX;
  private int startY;
  private int width;
  private int height;

  public RectangleDecorator(ShapeDecorator shapeDecorator){
    this.color = shapeDecorator.getColor();
    this.startX = shapeDecorator.getStartX();
    this.startY = shapeDecorator.getStartY();
    this.width = shapeDecorator.getWidth();
    this.height = shapeDecorator.getHeight();
  }

  @Override
  public void draw(Graphics2D graphics) {
    graphics.setColor(color.value);
    graphics.fillRect(startX, startY, width, height);
  }
}
