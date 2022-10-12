package persistence;

import model.Day;
import model.Week;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

// Reader for reading from a Json file to a Week object
public class JsonReaderWeek extends JsonReader {

    public JsonReaderWeek(String source) {
        super(source);
    }

    // EFFECTS: reads week from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Week read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseWeek(jsonObject);
    }

    // EFFECTS: parses Week from JSON object and returns it
    private Week parseWeek(JSONObject jsonObject) {
        Week week = new Week();
        addEvents(week, jsonObject);
        return week;
    }

    // MODIFIES: week
    // EFFECTS: parses events from JSON object and adds them to the week
    private void addEvents(Week week, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("events");
        for (Object json : jsonArray) {
            JSONObject nextEvent = (JSONObject) json;
            addEvent(week, nextEvent);
        }
    }

    // MODIFIES: week
    // EFFECTS: parses event from JSON object and adds it to its respective day of the week
    private void addEvent(Week week, JSONObject jsonObject) {
        String dayName = jsonObject.getString("day");
        String name = jsonObject.getString("name");
        int startHour = jsonObject.getInt("startHour");
        int startMinute = jsonObject.getInt("startMinute");
        int endHour = jsonObject.getInt("endHour");
        int endMinute = jsonObject.getInt("endMinute");
        for (Day day : week.getDays()) {
            if (day.getDayName().equals(dayName)) {
                day.addEvent(name, startHour, startMinute, endHour, endMinute);
            }
        }


    }
}
