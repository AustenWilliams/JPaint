/*
 * Assignment: 1
 * Topic: JPaint
 * Author: Austen Williams
 */
package view.interfaces;

public interface DialogChoice<T> {
    String getDialogTitle();

    String getDialogText();

    T[] getDialogOptions();

    T getCurrentSelection();
}
