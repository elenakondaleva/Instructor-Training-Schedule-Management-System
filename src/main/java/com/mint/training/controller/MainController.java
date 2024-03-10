package com.mint.training.controller;

import com.mint.training.controller.layout.InstructorViewController;
import com.mint.training.controller.layout.MaintenanceScheduleController;
import com.mint.training.controller.layout.PrimaryController;
import com.mint.training.controller.schedule.EventActionController;
import javafx.fxml.Initializable;

public abstract class MainController implements Initializable {

    protected static PrimaryController primaryController;
    protected static InstructorViewController instructorViewController;
    protected static MaintenanceScheduleController maintenanceScheduleController;
    protected static EventActionController eventActionController;

    public static void setPrimaryController(PrimaryController primaryController) {
        MainController.primaryController = primaryController;
    }
    public static void setInstructorViewController(InstructorViewController instructorViewController) {
        MainController.instructorViewController = instructorViewController;
    }
    public static void setMaintenanceScheduleController(MaintenanceScheduleController maintenanceScheduleController) {
        MainController.maintenanceScheduleController = maintenanceScheduleController;
    }
    public static void setEventActionController(EventActionController eventActionController) {
        MainController.eventActionController = eventActionController;
    }
}
