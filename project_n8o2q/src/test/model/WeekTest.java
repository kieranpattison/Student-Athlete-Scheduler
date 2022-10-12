package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WeekTest {
    Week testWeek;

    @BeforeEach
    void runBefore() {
        testWeek = new Week();
    }

    @Test
    void testConstructor() {
        List<Day> days = testWeek.getDays();
        assertEquals("Monday",days.get(0).getDayName());
        assertEquals("Tuesday",days.get(1).getDayName());
        assertEquals("Wednesday",days.get(2).getDayName());
        assertEquals("Thursday",days.get(3).getDayName());
        assertEquals("Friday",days.get(4).getDayName());
        assertEquals("Saturday",days.get(5).getDayName());
        assertEquals("Sunday",days.get(6).getDayName());
    }
}
