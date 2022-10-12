package model;

import java.util.ArrayList;
import java.util.List;

// Represents a specific day in a week and what Event(s) are scheduled for it.
public class Day {
    private String dayName;
    private List<ScheduleEvent> scheduleEvents;

    //REQUIRES: name to be one of the days of the week
    //MODIFIES: this
    //EFFECTS: assigns name to the field dayName, makes a new empty array list and assigns it to events
    public Day(String name) {
        scheduleEvents = new ArrayList<>();
        dayName = name;
    }

    //REQUIRES: valid values for the startMinute, endMinute, startHour, and endHour and start time must be earlier than
    //   end time.
    //MODIFIES: this
    //EFFECTS: if there isn't a time conflict, adds a new Event to events with a name, start minute, end minute, start
    //   hour and end hour. If there isn't a time conflict, return true, otherwise return false
    public boolean addEvent(String name, int startHour, int startMinute, int endHour, int endMinute) {
        for (ScheduleEvent i : scheduleEvents) {
            if (conflict(i, startHour, startMinute, endHour, endMinute)) {
                return false;
            }
        }
        ScheduleEvent scheduleEvent = new ScheduleEvent(name, startHour, startMinute, endHour, endMinute);
        scheduleEvents.add(scheduleEvent);
        EventLog.getInstance().logEvent(new Event("Added event " + name + " for " + dayName));
        return true;
    }

    //EFFECTS: returns true if there is a time conflict, false if otherwise
    public boolean conflict(ScheduleEvent scheduleEvent, int startHour, int startMinute, int endHour, int endMinute) {
        if ((scheduleEvent.getStartHour() < endHour) && (scheduleEvent.getEndHour() > startHour)) {
            return true;
        } else {
            if (startHour == scheduleEvent.getEndHour()) {
                return (scheduleEvent.getEndMinute() > startMinute);
            } else if (endHour == scheduleEvent.getStartHour()) {
                return (scheduleEvent.getStartMinute() < endMinute);
            } else {
                return false;
            }
        }

    }



    public List<ScheduleEvent> getEvents() {
        return scheduleEvents;
    }

    public String getDayName() {
        return dayName;
    }

    public int numberOfEvents() {
        return scheduleEvents.size();
    }



}
