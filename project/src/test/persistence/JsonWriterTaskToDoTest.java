package persistence;

import model.Day;
import model.TaskToDo;
import model.Week;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTaskToDoTest {

    @Test
    void testWriterInvalidFile() {
        try {
            List<TaskToDo> tasks = new ArrayList<>();
            JsonWriterTaskToDo writer = new JsonWriterTaskToDo("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyList() {
        try {
            List<TaskToDo> tasks = new ArrayList<>();
            JsonWriterTaskToDo writer = new JsonWriterTaskToDo("./data/testWriterEmptyToDoList.json");
            writer.open();
            writer.write(tasks);
            writer.close();

            JsonReaderTaskToDoList reader = new JsonReaderTaskToDoList("./data/testWriterEmptyToDoList.json");
            tasks = reader.read();
            assertEquals(0, tasks.size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralTodoList() {
        try {
            List<TaskToDo> tasks = new ArrayList<>();
            tasks.add(new TaskToDo("math", 4));
            tasks.add(new TaskToDo("test", 7));
            JsonWriterTaskToDo writer = new JsonWriterTaskToDo("./data/testWriterGeneralList.json");
            writer.open();
            writer.write(tasks);
            writer.close();

            JsonReaderTaskToDoList reader = new JsonReaderTaskToDoList("./data/testWriterGeneralList.json");
            tasks = reader.read();
            assertEquals(2, tasks.size());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }


}
