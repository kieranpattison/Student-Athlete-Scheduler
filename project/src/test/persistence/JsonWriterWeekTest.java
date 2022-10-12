package persistence;

import model.Day;
import model.Week;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterWeekTest {

    @Test
    void testWriterInvalidFile() {
        try {
            Week week = new Week();
            JsonWriterWeek writer = new JsonWriterWeek("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWeek() {
        try {
            Week week = new Week();
            JsonWriterWeek writer = new JsonWriterWeek("./data/testWriterEmptyWeek.json");
            writer.open();
            writer.write(week);
            writer.close();

            JsonReaderWeek reader = new JsonReaderWeek("./data/testWriterEmptyWeek.json");
            week = reader.read();
            for (Day day : week.getDays()) {
                assertEquals(0, day.numberOfEvents());
            }
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWeek() {
        try {
            Week week = new Week();
            week.getDays().get(0).addEvent("test", 8, 0, 9, 9);
            week.getDays().get(1).addEvent("test", 15, 0, 17, 9);
            JsonWriterWeek writer = new JsonWriterWeek("./data/testWriterGeneralWeek.json");
            writer.open();
            writer.write(week);
            writer.close();

            JsonReaderWeek reader = new JsonReaderWeek("./data/testWriterGeneralWeek.json");
            week = reader.read();
            assertEquals(1, week.getDays().get(0).numberOfEvents());
            assertEquals(1, week.getDays().get(1).numberOfEvents());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
