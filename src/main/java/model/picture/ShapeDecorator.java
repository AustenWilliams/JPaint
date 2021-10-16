package model.picture;

import java.awt.Color;
import java.awt.Graphics2D;
import model.ShapeColor;
import model.ShapeType;
import model.interfaces.Shape;

public class ShapeDecorator implements Shape {

  private ShapeImpl shape;
  private ShapeType shapeType;
  private ShapeColor color;
  private int startX;
  private int startY;
  private int width;
  private int height;
  private int[] xCoord;
  private int[] yCoord;
  Shape decorator;

  public ShapeDecorator(ShapeImpl shape, ShapeColor color, ShapeType type){
    this.shape = shape;
    this.color = color;
    this.shapeType = type;
    this.startX = shape.getStart().getX();
    this.startY = shape.getStart().getY();
    this.width = shape.getWidth();
    this.height = shape.getHeight();
    this.xCoord = new int[]{shape.getStart().getX(), shape.getEnd().getX(), shape.getEnd().getX()};
    this.yCoord = new int[]{shape.getStart().getY(), shape.getStart().getY(), shape.getEnd().getY()};
    determineShape();
  }

  @Override
  public void draw(Graphics2D graphics) {
    System.out.println("In ShapeDecorator draw method and shape is " + shapeType+
        " and the color is " + color);
    decorator.draw(graphics);
    //graphics.setColor(color.value);
    //graphics.fillRect(startX, startY, width, height);
  }

  public ShapeColor getColor(){return color;}
  public int getStartX(){ return startX;}
  public int getStartY(){ return startY;}
  public int getWidth(){return width;}
  public int getHeight(){return height;}
  public int[] getXCoord(){return xCoord;}
  public int[] getYCoord(){return yCoord;}

  private void determineShape(){

    System.out.println(shapeType);

    switch (shapeType){
      case RECTANGLE:
        decorator = new RectangleDecorator(this);
        break;
      case TRIANGLE:
        decorator = new TriangleDecorator(this);
        System.out.println("In Triangle Case");
        break;
      case ELLIPSE:
        decorator = new EllipseDecorator(this);
        System.out.println("in Ellipse case");
        break;
      default:
        System.out.println("Error");
        break;
    }
  }


}
