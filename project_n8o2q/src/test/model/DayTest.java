package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DayTest {
    Day testDay;

    @BeforeEach
    void runBefore() {
        testDay = new Day("Wednesday");
    }

    @Test
    void testConstructor() {
        assertEquals("Wednesday", testDay.getDayName());
        assertEquals(0, testDay.numberOfEvents());
    }

    @Test
    void testAddEventNoConflictNoHourOverlap() {
        assertTrue(testDay.addEvent("Workout", 9, 30, 10, 35));
        assertEquals(1, testDay.numberOfEvents());

        assertTrue(testDay.addEvent("Practice", 8,30,9,0));
        assertTrue(testDay.addEvent("Class", 10, 50, 12, 30));
        assertTrue(testDay.addEvent("Recovery", 16, 0, 18, 0));
        assertTrue(testDay.addEvent("test", 4, 30, 5, 20));
        assertTrue(testDay.addEvent("test", 9, 0, 9, 20));
        assertTrue(testDay.addEvent("test", 9, 20, 9, 30));
        assertTrue(testDay.addEvent("test", 10, 35, 10, 50));
        assertEquals(8, testDay.numberOfEvents());


    }

    @Test
    void testAddEventConflict() {
        testDay.addEvent("Workout", 9,30, 11, 35);
        assertFalse(testDay.addEvent("Class", 10, 0, 11, 0));
        assertEquals(1, testDay.numberOfEvents());
        assertFalse(testDay.addEvent("Recovery", 11, 34, 12, 34));
        assertFalse(testDay.addEvent("Meeting", 9, 0 , 9, 31));
        assertFalse(testDay.addEvent("Practice", 8, 0, 12, 0));
        assertFalse(testDay.addEvent("test", 8, 0, 10, 0));
        assertFalse(testDay.addEvent("test", 10, 0, 12, 0));
        assertEquals(1, testDay.numberOfEvents());
    }
}
