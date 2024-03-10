package com.mint.training.controller.layout;

import com.mint.training.controller.instructor.InstructorController;
import com.mint.training.controller.instructor.InstructorDto;
import com.mint.training.controller.MainController;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import javafx.util.StringConverter;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


@Controller
public class InstructorViewController extends MainController {

    @FXML
    private ComboBox<InstructorDto> instructorComboBox;

    @FXML
    private Label firstNameLabel;

    @FXML
    private Label lastNameLabel;

    @FXML
    private Label birthdayLabel;

    private final InstructorController instructorController;

    public InstructorViewController(InstructorController instructorController) {
        this.instructorController = instructorController;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        MainController.setInstructorViewController(this);
        populateInstructorList();
    }

    public InstructorDto getSelectedInstructor() {
        return instructorComboBox.getValue();
    }

    public void populateInstructorList() {
        List<InstructorDto> instructors = instructorController.getAll();
        instructorComboBox.getItems().addAll(instructors);

        instructorComboBox.setCellFactory(new Callback<ListView<InstructorDto>, ListCell<InstructorDto>>() {
            @Override
            public ListCell<InstructorDto> call(ListView<InstructorDto> param) {
                return new ListCell<>() {
                    @Override
                    protected void updateItem(InstructorDto instructor, boolean empty) {
                        super.updateItem(instructor, empty);
                        if (empty || instructor == null) {
                            setText(null);
                        } else {
                            setText(instructor.firstName() + " " + instructor.lastName());
                        }
                    }
                };
            }
        });

        instructorComboBox.setConverter(new StringConverter<InstructorDto>() {
            @Override
            public String toString(InstructorDto instructor) {
                return instructor == null ? null : instructor.firstName() + " " + instructor.lastName();
            }

            @Override
            public InstructorDto fromString(String string) {
                return null;
            }
        });

        instructorComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {

                String calendarValue =  primaryController.getCalendar();
                if (calendarValue != null) {
                    maintenanceScheduleController.findMonthAndInstructorSelection();
                }
                firstNameLabel.setText(newValue.firstName());
                lastNameLabel.setText(newValue.lastName());
                birthdayLabel.setText(newValue.birthday().toString());

            } else {
                firstNameLabel.setText("");
                lastNameLabel.setText("");
                birthdayLabel.setText("");
            }
        });
    }
}
