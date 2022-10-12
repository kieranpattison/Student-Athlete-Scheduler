package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScheduleEventTest {
    ScheduleEvent scheduleEventTest;

    @BeforeEach
    void runBefore() {
        scheduleEventTest = new ScheduleEvent("Workout", 12, 0, 13, 30);
    }

    @Test
    void testConstructor() {
        assertEquals("Workout", scheduleEventTest.getName());
        assertEquals(12, scheduleEventTest.getStartHour());
        assertEquals(13, scheduleEventTest.getEndHour());
        assertEquals(0, scheduleEventTest.getStartMinute());
        assertEquals(30, scheduleEventTest.getEndMinute());
    }

    @Test
    void testSetters() {
        scheduleEventTest.setName("test");
        scheduleEventTest.setEndHour(19);
        scheduleEventTest.setStartHour(13);
        scheduleEventTest.setStartMinute(30);
        scheduleEventTest.setEndMinute(23);
        assertEquals("test", scheduleEventTest.getName());
        assertEquals(19, scheduleEventTest.getEndHour());
        assertEquals(13, scheduleEventTest.getStartHour());
        assertEquals(30, scheduleEventTest.getStartMinute());
        assertEquals(23, scheduleEventTest.getEndMinute());
    }
}
