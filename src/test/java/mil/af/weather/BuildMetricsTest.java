package mil.af.weather;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class BuildMetricsTest {
    
    @Test
    public void testGetUptime() {
        BuildMetrics metrics = new BuildMetrics();
        metrics.addUptime(100L);
        assertEquals(100L, metrics.getUptime());
    }
    
    @Test
    public void testGetDowntime() {
        BuildMetrics metrics = new BuildMetrics();
        metrics.addDowntime(100L);
        assertEquals(100L, metrics.getDowntime());
    }
    
    @Test
    public void testGetTotalTime() {
        BuildMetrics metrics = new BuildMetrics();
        metrics.addDowntime(150L);
        metrics.addUptime(150L);
        assertEquals(300L, metrics.getTotalTime());
    }
    
    @Test
    public void testGetUptimePercentage() {
        BuildMetrics metrics = new BuildMetrics();
        // down 600 seconds, uptime was 85,800, total time is 86,400 - 99.32% uptime
        String expected = "99.32%";
        metrics.addDowntime(600000L);
        metrics.addUptime(87000000L); 
        assertEquals(expected, metrics.getUptimePercentage());
    }
}
