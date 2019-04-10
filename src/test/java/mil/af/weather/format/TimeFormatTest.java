package mil.af.weather.format;

import mil.af.weather.BuildMetrics;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class TimeFormatTest {
    
    @Test
    public void testFormatTime() {
        String expected = "00 days, 04 hours, 02 minutes, 32 seconds";
        BuildMetrics metrics = new BuildMetrics();
        // 4 hours  + 2 min  + 32 seconds
        // 14400000 + 120000 + 32000
        metrics.addUptime(14552000L);
        assertEquals(expected, TimeFormat.formatMillisecondsToReadableTime(metrics.getUptime()));
    }
    
}
