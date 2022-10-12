package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents a task that does not have specified sessions for working on it, with a name and duration of time in hours
//   until completion.
public class TaskToDo implements Writable {
    private String name;
    private int duration;

    //REQUIRES: duration > 0
    //MODIFIES: this
    //EFFECTS: assigns the parameters name and duration and due date to their respective fields
    public TaskToDo(String name, int duration) {
        this.name = name;
        this.duration = duration;
        EventLog.getInstance().logEvent(new Event("Task " + name + " was added to task list"));
    }

    //REQUIRES: 0 < hours <= duration
    //MODIFIES: this
    //EFFECTS: subtracts hours from duration and returns true if task is complete and false otherwise
    public boolean workOnTask(int hours) {
        duration -= hours;
        EventLog.getInstance().logEvent(new Event("Task " + name + " was worked on for " + hours + " hours"));
        return (duration <= 0);
    }

    public String getName() {
        return name;
    }

    public int getDuration() {
        return duration;
    }


    //EFFECTS: returns a Json object representing a TaskToDo
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("duration", duration);
        return json;
    }
}
