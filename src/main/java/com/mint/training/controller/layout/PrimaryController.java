package com.mint.training.controller.layout;

import com.mint.training.controller.MainController;
import com.mint.training.util.LayoutUtil;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

@Controller
public class PrimaryController extends MainController implements Initializable {
    @FXML
    private ComboBox<String> monthComboBox;
    public String getCalendar() {
        return monthComboBox.getValue();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        MainController.setPrimaryController(this);
        populateCalendar();

    }
    private void populateCalendar() {
        monthComboBox.setItems(LayoutUtil.populateCalendar());
        monthComboBox.getSelectionModel().select(LocalDate.now().getMonth().getValue() - 1);
        monthComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                maintenanceScheduleController.findMonthAndInstructorSelection();
            }
        });
        }
}
