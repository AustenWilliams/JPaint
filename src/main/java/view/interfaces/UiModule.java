/*
 * Assignment: 1
 * Topic: JPaint
 * Author: Austen Williams
 */
package view.interfaces;

import view.EventName;

public interface UiModule {
    void addEvent(EventName eventName, EventCallback command);
    <T> T getDialogResponse(DialogChoice dialogChoice);
}
