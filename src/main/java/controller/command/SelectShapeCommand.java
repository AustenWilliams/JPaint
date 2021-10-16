package controller.command;

import controller.interfaces.Command;
import controller.interfaces.Undoable;
import java.util.List;
import model.interfaces.Shape;
import model.picture.Point;

public class SelectShapeCommand implements Command{

  private List<Shape> shapeList;
  private Point start;
  private Point end;
  private List<Shape> selectList;

  public SelectShapeCommand(List<Shape> shapeList, Point start, Point end){
    this.shapeList = shapeList;
    this.start = start;
    this.end = end;
  }

  public List<Shape> getSelectList(){
    return selectList;
  }

  @Override
  public void run() {
    SelectImpl selectImpl = new SelectImpl(shapeList, start, end);
    selectList = selectImpl.getList();
  }


}
