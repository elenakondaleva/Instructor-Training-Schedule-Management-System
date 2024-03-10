package com.mint.training.controller.layout;

import com.mint.training.controller.instructor.InstructorDto;
import com.mint.training.controller.MainController;
import com.mint.training.controller.schedule.ScheduleController;
import com.mint.training.controller.schedule.ScheduleEventDto;
import com.mint.training.domain.event.model.EventType;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

@Controller
public class MaintenanceScheduleController extends MainController {

    @FXML
    private Label eventDuration;
    @FXML
    private TableView<ScheduleEventDto> eventTableView;
    private final ScheduleController scheduleController;

    public MaintenanceScheduleController(ScheduleController scheduleController) {
        this.scheduleController = scheduleController;
    }

    @FXML
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        MainController.setMaintenanceScheduleController(this);
    }

    public void findMonthAndInstructorSelection() {
        String selectedMonth = primaryController.getCalendar();
        InstructorDto selectedInstructor = instructorViewController.getSelectedInstructor();
        if (selectedMonth != null && selectedInstructor != null) {
            populateScheduleTable(selectedMonth, selectedInstructor.instructorId());
        }
    }

    public void populateScheduleTable(String month, long instructorId) {
        List<ScheduleEventDto> scheduleList = scheduleController.getScheduleByInstructor(month, instructorId);
        eventTableView.getItems().clear();

        TableColumn<ScheduleEventDto, EventType> eventTypeColumn
                = (TableColumn<ScheduleEventDto, EventType>) eventTableView.getColumns().get(0);
        eventTypeColumn.setCellValueFactory(new PropertyValueFactory<>("eventType"));
        TableColumn<ScheduleEventDto, String> descriptionColumn
                = (TableColumn<ScheduleEventDto, String>) eventTableView.getColumns().get(1);
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        TableColumn<ScheduleEventDto, LocalDate> startDateColumn
                = (TableColumn<ScheduleEventDto, LocalDate>) eventTableView.getColumns().get(2);
        startDateColumn.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        TableColumn<ScheduleEventDto, LocalDate> endDateColumn
                = (TableColumn<ScheduleEventDto, LocalDate>) eventTableView.getColumns().get(3);
        endDateColumn.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        eventTableView.getItems().addAll(scheduleList);

        String summuryEventDays = String.valueOf(scheduleController.getSummuryScheduleByInstructor(month, instructorId));
        eventDuration.setText(summuryEventDays);
    }

    public TableView<ScheduleEventDto> getEventTableView() {
        return eventTableView;
    }
    @FXML
    private void addEvent() {
        eventActionController.addEvent();
    }
    @FXML
    private void editEvent() {
        eventActionController.editEvent();
    }
    @FXML
    private void deleteEvent() {
        eventActionController.deleteEvent();
    }
}
