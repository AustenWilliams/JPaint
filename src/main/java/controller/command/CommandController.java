/*
 * Assignment: 1
 * Topic: JPaint
 * Author: Dan Walker
 */
package controller.command;

import java.util.ArrayList;
import java.util.List;
import model.MouseMode;
import model.ShapeColor;
import model.ShapeShadingType;
import model.ShapeType;
import model.interfaces.Shape;
import model.interfaces.UserChoices;
import model.picture.PictureImpl;
import model.picture.Point;
import model.interfaces.Picture;
import model.picture.ShapeImpl;
import view.gui.PaintCanvas;
import controller.interfaces.Command;

/**
 * CommandController is responsible for creating new Commands based on
 * current user input.  The new Command is normally executed as well.
 */
public class CommandController {
  private final PaintCanvas canvas;
  private final UserChoices choices;
  private final Picture picture;
  List<Shape> selectedList;

  public CommandController(PaintCanvas canvas, UserChoices choices, Picture picture) {
    this.choices = choices;
    this.canvas = canvas;
    this.picture = picture;
    selectedList = new ArrayList<>();
  }

  public void onDraw(Point start, Point end) {

    Command cmd = new CreateShapeCommand(choices, canvas, picture, start, end);

    cmd.run();

    canvas.repaint();
  }

  public void onSelect(Point start, Point end){
    selectedList.clear();
    PictureImpl pictureImpl = (PictureImpl) picture;
    List<Shape> shapeList = pictureImpl.getElements();
    SelectShapeCommand cmd = new SelectShapeCommand(shapeList, start, end);
    cmd.run();
    selectedList = cmd.getSelectList();
    System.out.println(" In Onselect Method selected: " + selectedList.size() + " shapes");
  }

  public void onMove(Point start, Point end){
    List<Shape> tempList = new ArrayList<Shape>();
    for (Shape s: selectedList){
      ShapeImpl shapeImpl = (ShapeImpl) s;
      ShapeColor color = shapeImpl.getColor();
      ShapeType shapeType = shapeImpl.getType();
      ShapeShadingType shadingType = shapeImpl.getShadingType();
      int deltaX = end.getX() - start.getX();
      int deltaY = end.getY() - start.getY();
      Point newStart = new Point(shapeImpl.getStart().getX() + deltaX, shapeImpl.getStart().getY() + deltaY);
      Point newEnd = new Point(shapeImpl.getEnd().getX() + deltaX, shapeImpl.getEnd().getY() + deltaY);
      picture.remove(s);
      Shape shape = new ShapeImpl(newStart, newEnd, color, shapeType, shadingType);
      picture.add(shape);
      tempList.add(shape);
      System.out.println(selectedList.toString());
    }
    selectedList = tempList;
    canvas.repaint();
  }


  public MouseMode getMouseMode() {
    return choices.getActiveMouseMode();
  }

  public void onUndo() {
    CommandHistory.undo();
    canvas.repaint();
  }
  public void onRedo() {
    CommandHistory.redo();
    canvas.repaint();
  }
}

