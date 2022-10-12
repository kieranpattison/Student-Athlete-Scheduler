package persistence;

import model.TaskToDo;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

//JsonWriter for TaskToDo Class
public class JsonWriterTaskToDo extends JsonWriter {

    public JsonWriterTaskToDo(String destination) {
        super(destination);
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of TaskToDo to file
    public void write(List<TaskToDo> tasks) {
        JSONArray jsonArray = new JSONArray();
        for (TaskToDo task : tasks) {
            jsonArray.put(task.toJson());
        }
        JSONObject json = new JSONObject();
        json.put("tasks", jsonArray);
        saveToFile(json.toString(TAB));
    }
}
