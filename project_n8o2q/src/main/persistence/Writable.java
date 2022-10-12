package persistence;

import org.json.JSONObject;

// Interface for a writable object
public interface Writable {
    //EFFECTS: returns this as JSON object
    JSONObject toJson();
}
