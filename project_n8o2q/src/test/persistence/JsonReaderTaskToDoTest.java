package persistence;
import model.Day;
import model.TaskToDo;
import model.Week;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTaskToDoTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReaderTaskToDoList reader = new JsonReaderTaskToDoList("./data/noSuchFile.json");
        try {
            List<TaskToDo> tasks = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyToDo() {
        JsonReaderTaskToDoList reader = new JsonReaderTaskToDoList("./data/testReaderEmptyTaskList.json");
        try {
            List<TaskToDo> tasks = reader.read();
            assertEquals(0, tasks.size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralToDO() {
        JsonReaderTaskToDoList reader = new JsonReaderTaskToDoList("./data/testReaderGeneralToDo.json");
        try {
            List<TaskToDo> tasks = reader.read();
            assertEquals(2, tasks.size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
