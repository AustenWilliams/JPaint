/*
 * Assignment: 1
 * Topic: JPaint
 * Author: Austen Williams
 */
package model.picture;

import java.awt.Graphics2D;
import model.ShapeColor;
import model.ShapeShadingType;
import model.ShapeType;
import model.interfaces.Shape;
import model.interfaces.UserChoices;

/**
 * @see model.interfaces.Shape
 */
public class ShapeImpl implements Shape {

  private Point start;
  private Point end;
  private ShapeColor color;
  private ShapeType type;
  private ShapeShadingType shadingType;

  public ShapeImpl(Point start, Point end, ShapeColor color, ShapeType type, ShapeShadingType shadingType) {
    normalizePoints(start, end);
    this.color = color;
    this.type = type;
    this.shadingType = shadingType;
  }

  public ShapeColor getColor() {
    return color;
  }

  public ShapeType getType() {
    return type;
  }

  public ShapeShadingType getShadingType() {
    return shadingType;
  }

  public Point getStart() {
    return start;
  }

  public Point getEnd() {
    return end;
  }

  @Override
  public void draw(Graphics2D graphics) {
    ShapeDecorator decorator = new ShapeDecorator(this, color, type, shadingType);
    decorator.draw(graphics);
  }

  public int getWidth() {
    return end.getX() - start.getX();
  }

  public int getHeight() {
    return end.getY() - start.getY();
  }



  /**
   * The beginning and ending points are not necessarily the points needed when drawing.
   * This function figures out the better drawing points.
   * @param firstPoint location of the mouse when first clicked
   * @param endPoint location of mouse when finally released
   */
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
}
