package com.mint.training.controller.schedule;

import com.mint.training.controller.event.CreateEventDto;
import com.mint.training.controller.event.EventController;
import com.mint.training.controller.event.EventDto;
import com.mint.training.controller.instructor.InstructorDto;
import com.mint.training.controller.MainController;
import com.mint.training.domain.event.model.EventType;
import com.mint.training.util.LayoutUtil;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

@Controller
public class EventActionController extends MainController {

    @FXML
    private VBox extraFieldsBox;
    @FXML
    private ComboBox<String> eventTypeComboBox;
    @FXML
    private TextArea descriptionTextArea;
    @FXML
    private DatePicker startDatePicker;
    @FXML
    private DatePicker endDatePicker;
    private Integer eventId;
    private final ScheduleController scheduleController;
    private final EventController eventController;

    public EventActionController(ScheduleController scheduleController, EventController eventController) {
        this.scheduleController = scheduleController;
        this.eventController = eventController;
    }

    @FXML
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        MainController.setEventActionController(this);
    }
    public void addEvent() {
        InstructorDto selectedInstructor = instructorViewController.getSelectedInstructor();
        if (selectedInstructor == null) {
            notificationMessage(Alert.AlertType.ERROR, "Please choose instructor", "Instructor is needed!");
            return;
        }
        clearAddEvent(true);
    }
    public void editEvent() {
        ScheduleEventDto selectedEvent
                = maintenanceScheduleController.getEventTableView().getSelectionModel().getSelectedItem();
        if (selectedEvent == null) {
            notificationMessage(Alert.AlertType.WARNING,"No Event Selected", "Please select an event to delete.");
            return;
        }
        clearAddEvent(true);
        eventId = Math.toIntExact(selectedEvent.getEventId());
        eventTypeComboBox.getSelectionModel().select(selectedEvent.getEventType().ordinal());
        descriptionTextArea.setText(selectedEvent.getDescription());
        startDatePicker.setValue(selectedEvent.getStartDate());
        endDatePicker.setValue(selectedEvent.getEndDate());
        setDisabled(true);
    }
    public void deleteEvent() {
        ScheduleEventDto selectedEvent
                = maintenanceScheduleController.getEventTableView().getSelectionModel().getSelectedItem();
        if (selectedEvent == null) {
            notificationMessage(Alert.AlertType.WARNING,"No Event Selected", "Please select an event to delete.");
            return;
        }
        Optional<ButtonType> result = notificationMessage(Alert.AlertType.CONFIRMATION,
                "Confirm Deletion", "Are you sure you want to delete the selected event?");
        if (result.isPresent() && result.get() == ButtonType.OK) {
            maintenanceScheduleController.getEventTableView().getItems().remove(selectedEvent);
            scheduleController.removeSchedule(selectedEvent.getInstructorId(), selectedEvent.getEventId());
        }
    }
    @FXML
    private void saveEvent() {
        InstructorDto selectedInstructor = instructorViewController.getSelectedInstructor();
        EventType eventType = EventType.valueOf(eventTypeComboBox.getValue());
        String description = descriptionTextArea.getText();
        LocalDate startDate = startDatePicker.getValue();
        LocalDate endDate = endDatePicker.getValue();

        if (eventType == null || description.isEmpty() || startDate == null || endDate == null) {
            notificationMessage(Alert.AlertType.ERROR, "All information should be entered", "Please enter.");
            return;
        }
        long tempEventId;
        if (eventId == null) {
            CreateEventDto event = new CreateEventDto(eventType, description);
            EventDto newEvent = eventController.addEvent(event);
            tempEventId = newEvent.eventId();
        }
        else {
            tempEventId = eventId;
        }

        CreateScheduleDto newSchedule = new CreateScheduleDto(tempEventId, startDate, endDate);
        maintenanceScheduleController.getEventTableView().getItems()
                .add(new ScheduleEventDto(selectedInstructor.instructorId(),
                    tempEventId, eventType, description, startDate, endDate));
        scheduleController.createOrUpdateSchedule(selectedInstructor.instructorId(), newSchedule);

        setDisabled(false);
        clearAddEvent(false);
        maintenanceScheduleController.populateScheduleTable(primaryController.getCalendar(),
                selectedInstructor.instructorId());
        eventId = null;
    }

    @FXML
    private void cancelEvent() {
        eventId = null;
        clearAddEvent(false);
    }

    private void setDisabled(boolean flag) {
        eventTypeComboBox.setDisable(flag);
        descriptionTextArea.setDisable(flag);
    }
    private void clearAddEvent(boolean isVisible) {
        extraFieldsBox.setVisible(isVisible);
        eventTypeComboBox.setItems(LayoutUtil.populateEventType());
        eventTypeComboBox.getSelectionModel().clearSelection();
        descriptionTextArea.clear();
        startDatePicker.setValue(null);
        endDatePicker.setValue(null);
    }

    private Optional<ButtonType> notificationMessage(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        if (Alert.AlertType.CONFIRMATION.equals(type)) {
            return alert.showAndWait();
        }
        else {
            alert.showAndWait();
        }
        return null;
    }
}
