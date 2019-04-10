package mil.af.weather;

import com.offbytwo.jenkins.model.BuildResult;
import com.offbytwo.jenkins.model.BuildWithDetails;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

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

    @Test
    public void testCalculateMetrics() {
        List<BuildWithDetails> buildDetailsList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            BuildWithDetails buildDetails = Mockito.mock(BuildWithDetails.class);
            Mockito.when(buildDetails.isBuilding()).thenReturn(false);
            // 0 Failure 0L
            // 1 Success 10000L
            // 2 Failure 20000L
            // 3 Success 30000L
            // so we get a downtime of 10000 + 10000
            // and an uptime of 10000L
            if (i % 2 == 0) {
                Mockito.when(buildDetails.getResult()).thenReturn(BuildResult.FAILURE);
                Mockito.when(buildDetails.getTimestamp()).thenReturn(10000L * i);
            } else {
                Mockito.when(buildDetails.getResult()).thenReturn(BuildResult.SUCCESS);
                Mockito.when(buildDetails.getTimestamp()).thenReturn(10000L * i);
            }
            buildDetailsList.add(buildDetails);
        }
        // This needs to be in reverse order like Jenkins build lists
        Collections.reverse(buildDetailsList);
        BuildMetrics metrics = new BuildMetrics().buildMetrics(buildDetailsList, false);
        assertEquals(10000L, metrics.getUptime());
        assertEquals(20000L, metrics.getDowntime());
    }

    @Test
    public void testCalculateMetricsWithUnrecognizedStatus() {
        List<BuildWithDetails> buildDetailsList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            BuildWithDetails buildDetails = Mockito.mock(BuildWithDetails.class);
            Mockito.when(buildDetails.isBuilding()).thenReturn(false);
            // 0 Failure 0L
            // 1 Success 10000L
            // 2 Failure 20000L
            // 3 Success 30000L
            // so we get a downtime of 10000 + 10000
            // and an uptime of 10000L
            if (i % 2 == 0) {
                Mockito.when(buildDetails.getResult()).thenReturn(BuildResult.FAILURE);
                Mockito.when(buildDetails.getTimestamp()).thenReturn(10000L * i);
            } else {
                Mockito.when(buildDetails.getResult()).thenReturn(BuildResult.SUCCESS);
                Mockito.when(buildDetails.getTimestamp()).thenReturn(10000L * i);
            }
            buildDetailsList.add(buildDetails);
        }
        // IMPORTANT PART OF THIS TEST
        BuildWithDetails weirdBuild = Mockito.mock(BuildWithDetails.class);
        Mockito.when(weirdBuild.isBuilding()).thenReturn(false);
        // cancelled builds shouldn't affect the metrics
        Mockito.when(weirdBuild.getResult()).thenReturn(BuildResult.CANCELLED);
        Mockito.when(weirdBuild.getTimestamp()).thenReturn(40000L); // next build in list

        // This needs to be in reverse order like Jenkins build lists
        Collections.reverse(buildDetailsList);
        BuildMetrics metrics = new BuildMetrics().buildMetrics(buildDetailsList, false);
        assertEquals(10000L, metrics.getUptime());
        assertEquals(20000L, metrics.getDowntime());
    }

    /* Testing this causes GUI windows to pop up.  Need to research how to test this...
    @Test
    public void testCalculateMetricsWithBuildBuilding() {
        List<BuildWithDetails> buildDetailsList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            BuildWithDetails buildDetails = Mockito.mock(BuildWithDetails.class);
            Mockito.when(buildDetails.isBuilding()).thenReturn(true);
            // 0 Failure 0L
            // 1 Success 10000L
            // 2 Failure 20000L
            // 3 Success 30000L
            // so we get a downtime of 10000 + 10000
            // and an uptime of 10000L
            if (i % 2 == 0) {
                Mockito.when(buildDetails.getResult()).thenReturn(BuildResult.FAILURE);
                Mockito.when(buildDetails.getTimestamp()).thenReturn(10000L * i);
            } else {
                Mockito.when(buildDetails.getResult()).thenReturn(BuildResult.SUCCESS);
                Mockito.when(buildDetails.getTimestamp()).thenReturn(10000L * i);
            }
            buildDetailsList.add(buildDetails);
        }
        // This needs to be in reverse order like Jenkins build lists
        Collections.reverse(buildDetailsList);
        BuildMetrics metrics = new BuildMetrics().buildMetrics(buildDetailsList);
        assertEquals(10000L, metrics.getUptime());
        assertEquals(20000L, metrics.getDowntime());
    }
    */
    
     @Test
    public void testSucessfulAndFailedBuildsCalculateCorrectly() {
        List<BuildWithDetails> buildDetailsList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            BuildWithDetails buildDetails = Mockito.mock(BuildWithDetails.class);
            Mockito.when(buildDetails.isBuilding()).thenReturn(false);
            // 0 Failure 0L
            // 1 Success 10000L
            // 2 Failure 20000L
            // 3 Success 30000L
            // so we get a downtime of 10000 + 10000
            // and an uptime of 10000L
            if (i % 2 == 0) {
                Mockito.when(buildDetails.getResult()).thenReturn(BuildResult.FAILURE);
                Mockito.when(buildDetails.getTimestamp()).thenReturn(10000L * i);
            } else {
                Mockito.when(buildDetails.getResult()).thenReturn(BuildResult.SUCCESS);
                Mockito.when(buildDetails.getTimestamp()).thenReturn(10000L * i);
            }
            buildDetailsList.add(buildDetails);
        }
        BuildWithDetails weirdBuild = Mockito.mock(BuildWithDetails.class);
        Mockito.when(weirdBuild.isBuilding()).thenReturn(false);
        // cancelled builds shouldn't affect the metrics
        Mockito.when(weirdBuild.getResult()).thenReturn(BuildResult.CANCELLED);
        Mockito.when(weirdBuild.getTimestamp()).thenReturn(40000L); // next build in list

        // This needs to be in reverse order like Jenkins build lists
        Collections.reverse(buildDetailsList);
        BuildMetrics metrics = new BuildMetrics().buildMetrics(buildDetailsList, false);
        assertEquals(2, metrics.getFailedBuilds());
        assertEquals(2, metrics.getSuccessfulBuilds());
    }
}
