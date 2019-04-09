package mil.af.weather;

import mil.af.weather.format.TimeFormat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class TimeFormatTest {
    
    @Test
    public void testFormatTime() {
        String expected = "04 hours 02 minutes 32 seconds";
        BuildMetrics metrics = new BuildMetrics();
        // 4 hours  + 2 min  + 32 seconds
        // 14400000 + 120000 + 32000
        metrics.addUptime(14552000L);
        assertEquals(expected, TimeFormat.formatMillisecondsToReadableTime(metrics.getUptime()));
    }
    
}
