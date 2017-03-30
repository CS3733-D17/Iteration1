package com.slackers.inc.sample;

import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;

/**
 * Created by SrinuL on 3/25/17.
 */
public class RadioToggleButton extends ToggleButton {

    // As in RadioButton.
    /**
     * Toggles the state of the radio button if and only if the RadioButton
     * has not already selected or is not part of a {@link ToggleGroup}.
     */
    @Override
    public void fire() {
        // we don't toggle from selected to not selected if part of a group
        if (getToggleGroup() == null || !isSelected()) {
            super.fire();
        }
    }
}