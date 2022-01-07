/*
 * Assignment: 1
 * Topic: JPaint
 * Author: Austen Williams
 */
package controller.interfaces;

/**
 * Support for the user's ability to undo or redo in the editor
 * Some Commands will also support Undoable
 */
public interface Undoable {
  void undo();
  void redo();
}
