package persistence;

import model.TaskToDo;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//Json reader for TaskToDoList object
public class JsonReaderTaskToDoList extends JsonReader {

    public JsonReaderTaskToDoList(String source) {
        super(source);
    }

    // EFFECTS: reads tasks from file and returns it;
    // throws IOException if an error occurs reading data from file
    public List<TaskToDo> read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return makeTaskList(jsonObject);
    }


    // EFFECTS: parses tasks from JSON object and returns it
    private List<TaskToDo> makeTaskList(JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("tasks");
        ArrayList<TaskToDo> tasks = new ArrayList<>();
        for (Object json: jsonArray) {
            JSONObject nextTask = (JSONObject) json;
            addTask(tasks, nextTask);
        }
        return tasks;
    }

    //MODIFIES: tasks
    //EFFECTS: parses Task from JSON object and adds it to tasks
    private void addTask(List<TaskToDo> tasks, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int duration = jsonObject.getInt("duration");
        tasks.add(new TaskToDo(name, duration));
    }
}
