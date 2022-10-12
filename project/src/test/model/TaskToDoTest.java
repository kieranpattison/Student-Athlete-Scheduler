package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TaskToDoTest {
    TaskToDo testTask;

    @BeforeEach
    void runBefore() {
        testTask = new TaskToDo("Homework", 5);
    }

    @Test
    void testConstructor() {
        assertEquals("Homework", testTask.getName());
        assertEquals(5, testTask.getDuration());
    }

    @Test
    void workOnTaskTestIncomplete() {
        assertFalse(testTask.workOnTask(2));
        assertEquals(3, testTask.getDuration());
    }

    @Test
    void workOnTaskTestComplete() {
        assertTrue(testTask.workOnTask(5));
        assertEquals(0, testTask.getDuration());
    }
}
