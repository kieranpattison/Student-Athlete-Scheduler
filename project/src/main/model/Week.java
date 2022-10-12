package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

//Represents a week of a schedule with seven days
public class Week implements Writable {
    private List<Day> days;

    //MODIFIES: this
    //EFFECTS: Instantiates a new week and assigns each day of the week to days
    public Week() {
        days = new ArrayList<>();
        days.add(new Day("Monday"));
        days.add(new Day("Tuesday"));
        days.add(new Day("Wednesday"));
        days.add(new Day("Thursday"));
        days.add(new Day("Friday"));
        days.add(new Day("Saturday"));
        days.add(new Day("Sunday"));
    }

    public List<Day> getDays() {
        return days;
    }

    //EFFECTS: returns a JsonObject for Week
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("events", eventsToJson());
        return json;
    }

    //EFFECTS: parses days into a JSONArray
    public JSONArray eventsToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Day day : days) {
            for (ScheduleEvent scheduleEvent : day.getEvents()) {
                JSONObject json = new JSONObject();
                json.put("day", day.getDayName());
                json.put("name", scheduleEvent.getName());
                json.put("startHour", scheduleEvent.getStartHour());
                json.put("startMinute", scheduleEvent.getStartMinute());
                json.put("endHour", scheduleEvent.getEndHour());
                json.put("endMinute", scheduleEvent.getEndMinute());
                jsonArray.put(json);
            }
        }
        return jsonArray;
    }
}
