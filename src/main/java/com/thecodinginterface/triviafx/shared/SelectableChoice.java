package com.thecodinginterface.triviafx.shared;

import javafx.event.EventHandler;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TouchEvent;
import javafx.scene.layout.Region;

public class SelectableChoice extends ToggleButton {

    public SelectableChoice(String text, ToggleGroup toggleGroup, Object userData, Region container) {
        super(text);
        getStyleClass().add("characteristic-toggle");
        setToggleGroup(toggleGroup);

        setUserData(userData);

        EventHandler<MouseEvent> mouseHandler = (mouseEvt) -> {
            if (isSelected()) {
                mouseEvt.consume();
            }
        };

        EventHandler<TouchEvent> touchHandler = (touchEvt) -> {
            if (isSelected()) {
                touchEvt.consume();
            }
        };

        addEventFilter(MouseEvent.MOUSE_PRESSED, mouseHandler);
        addEventFilter(MouseEvent.MOUSE_CLICKED, mouseHandler);
        addEventFilter(MouseEvent.MOUSE_RELEASED, mouseHandler);

        addEventFilter(TouchEvent.TOUCH_PRESSED, touchHandler);
        addEventFilter(TouchEvent.TOUCH_RELEASED, touchHandler);

        prefWidthProperty().bind(container.widthProperty().multiply(0.8));
        
        setWrapText(true);
    }
}
