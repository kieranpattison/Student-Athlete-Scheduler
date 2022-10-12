package persistence;

import model.Week;
import org.json.JSONObject;

//Json writer for Week class
public class JsonWriterWeek extends JsonWriter {

    public JsonWriterWeek(String destination) {
        super(destination);
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of Week to file
    public void write(Week week) {
        JSONObject json = week.toJson();
        saveToFile(json.toString(TAB));
    }
}
