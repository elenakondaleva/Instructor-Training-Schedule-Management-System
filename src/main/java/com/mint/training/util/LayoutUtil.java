package com.mint.training.util;

import com.mint.training.domain.event.model.EventType;
import com.mint.training.domain.schedule.model.MonthCalendar;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class LayoutUtil {

    public static ObservableList<String>  populateCalendar() {
        ObservableList<String> months = FXCollections.observableArrayList();
        for (MonthCalendar month : MonthCalendar.values()) {
            months.add(month.toString());
        }
        return months;
    }

    public static ObservableList<String> populateEventType() {
        ObservableList<String> eventTypes = FXCollections.observableArrayList();
        for (EventType type : EventType.values()) {
            eventTypes.add(type.toString());
        }
        return eventTypes;
    }
}
