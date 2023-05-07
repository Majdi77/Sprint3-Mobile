package com.mycompany.gui;

import com.codename1.ui.events.DataChangedListener;
import com.codename1.ui.TextField;
import com.codename1.ui.Dialog;

public class ValidationListener implements DataChangedListener {
    private TextField descriptionField;
    private TextField disponibiliteField;
    private TextField coursField;
    private TextField imgField;

    public ValidationListener(TextField descriptionField, TextField disponibiliteField, TextField coursField) {
        this.descriptionField = descriptionField;
        this.disponibiliteField = disponibiliteField;
        this.coursField = coursField;
    }

    @Override
    public void dataChanged(int type, int index) {
        if (type == DataChangedListener.CHANGED) {
            if (index == descriptionField.getConstraint()) {
                validateDescription();
            } else if (index == disponibiliteField.getConstraint()) {
                validateDisponibilite();
            } else if (index == coursField.getConstraint()) {
                validateCours();
            }
        }
    }

    private void validateDescription() {
        String description = descriptionField.getText();
        if (description.isEmpty()) {
            Dialog.show("Error", "Description cannot be empty", "OK", null);
        }
        // Add any additional validation logic for the description field here
    }

    private void validateDisponibilite() {
        String disponibilite = disponibiliteField.getText();
        if (disponibilite.isEmpty()) {
            Dialog.show("Error", "Disponibilite cannot be empty", "OK", null);
        }
        // Add any additional validation logic for the disponibilite field here
    }

    private void validateCours() {
        String cours = coursField.getText();
        if (cours.isEmpty()) {
            Dialog.show("Error", "Cours cannot be empty", "OK", null);
        }
        // Add any additional validation logic for the cours field here
    }
}