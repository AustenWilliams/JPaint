/*
 * Assignment: 1
 * Topic: JPaint
 * Author: Dan Walker
 */
package controller;

import controller.command.CommandController;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import model.MouseMode;
import model.picture.Point;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * MouseHandler is responsible for propagating mouse coordinates into our application
 * classes. This is a boundary class so very little code should be added here.
 */
public class MouseHandler extends MouseAdapter {

  private Point start;
  private Point end;
  private CommandController commandController;
  private MouseMode mouseMode;

  private static final Logger log = LoggerFactory.getLogger(MouseHandler.class);

  public MouseHandler(CommandController commandController) {
    this.commandController = commandController;
  }

  @Override
  public void mousePressed(MouseEvent e) {
    start = new Point( e.getX(),  e.getY());

  }

  @Override
  public void mouseReleased(MouseEvent e) {
    mouseMode = commandController.getMouseMode();
    end = new Point(e.getX(), e.getY());
    switch (mouseMode) {
      case DRAW:
        commandController.onDraw(start, end);
        break;
      case SELECT:
        commandController.onSelect(start, end);
        break;
      case MOVE:
        commandController.onMove(start, end);
        System.out.println("Mouse in move mode.");
        break;
    }
  }

}
