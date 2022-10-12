package persistence;

import model.Day;
import model.Week;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class JsonReaderWeekTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReaderWeek reader = new JsonReaderWeek("./data/noSuchFile.json");
        try {
            Week week = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWeek() {
        JsonReaderWeek reader = new JsonReaderWeek("./data/testReaderEmptyWeek.json");
        try {
            Week week = reader.read();
            assertEquals(7,week.getDays().size());
            for (Day day : week.getDays()) {
                assertEquals(0, day.numberOfEvents());
            }
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWeek() {
        JsonReaderWeek reader = new JsonReaderWeek("./data/testReaderGeneralWeek.json");
        try {
            Week week = reader.read();
            assertEquals(1, week.getDays().get(2).numberOfEvents());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
