package controller.command;

import controller.interfaces.Command;
import controller.interfaces.Undoable;
import java.util.ArrayList;
import java.util.List;
import view.gui.PaintCanvas;
import view.interfaces.Picture;
import view.interfaces.Shape;

public class DeleteShapesCommand implements Command, Undoable {
  private final Picture picture;
  private List<Shape> selected;
  private final PaintCanvas canvas;

  private List<Shape> deletedShapes = new ArrayList<>();


  public DeleteShapesCommand(Picture picture, List<Shape> clipBoardContents, PaintCanvas canvas){
    this.picture = picture;
    this.selected = picture.getSelected();
    this.canvas = canvas;
    CommandHistory.add(this);
  }

  @Override
  public void undo() {
    picture.add(deletedShapes);
  }

  @Override
  public void redo() {
    for(Shape shape: selected) {
      picture.remove(shape);
    }
  }

  @Override
  public void run() {
    selected = copySelected(picture);
    System.out.println("Beggining Run");
    int count = 0;
    for (Shape shape : selected) {
      System.out.println(count+1);
      picture.remove(shape);
      deletedShapes.add(shape);
      canvas.repaint();
    }
  }
  private List<Shape> copySelected(Picture picture) {
    List<Shape> copies = new ArrayList<>();
    copies.addAll(picture.getSelected());
    return  copies;
  }
}
