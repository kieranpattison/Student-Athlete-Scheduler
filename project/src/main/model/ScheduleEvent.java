package model;

//Represents an event with a name, and its start hour and minute, and end hour and minute
public class ScheduleEvent {
    private String name;
    private int startMinute;
    private int startHour;
    private int endHour;
    private int endMinute;

    //REQUIRES: 0 < hour < 25, -1 < minute < 60
    //MODIFIES: this
    //EFFECTS: takes a name, duration, hour and minute and assigns them to the correct fields
    public ScheduleEvent(String name, int startHour, int startMinute, int endHour, int endMinute) {
        this.name = name;
        this.startHour = startHour;
        this.startMinute = startMinute;
        this.endHour = endHour;
        this.endMinute = endMinute;
    }

    public int getStartHour() {
        return startHour;
    }

    public int getEndHour() {
        return endHour;
    }

    public int getStartMinute() {
        return startMinute;
    }

    public int getEndMinute() {
        return endMinute;
    }

    public String getName() {
        return name;
    }

    public void setStartHour(int hour) {
        this.startHour = hour;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEndHour(int hour) {
        this.endHour = hour;
    }

    public void setStartMinute(int minute) {
        this.startMinute = minute;
    }

    public void setEndMinute(int minute) {
        this.endMinute = minute;
    }
}
