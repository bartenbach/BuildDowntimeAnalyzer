package mil.af.weather;

import com.offbytwo.jenkins.JenkinsServer;
import com.offbytwo.jenkins.model.Build;
import com.offbytwo.jenkins.model.BuildResult;
import com.offbytwo.jenkins.model.BuildWithDetails;
import com.offbytwo.jenkins.model.Job;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class JenkinsHandlerTest {
    
    @Test
    public void testGetJenkinsUriNull() {
        String bogusUri = "!@#$%^&*()-+=[]{}|<>";
        URI uri = JenkinsHandler.getJenkinsURI(bogusUri);
        assertNull(uri);
    }
    
    @Test
    public void testGetRealUri() {
        String realUri = "http://localhost:8080/jenkins";
        URI uri = JenkinsHandler.getJenkinsURI(realUri);
        assertNotNull(uri);
    }
    
    @Test
    public void testGetJenkinsServerNull() {
        JenkinsServer server = JenkinsHandler.getJenkins(null, null, null);
        assertNull(server);
    }
    
    @Test
    public void testGetJenkinsWorks() {
        JenkinsServer server = JenkinsHandler.getJenkins("http://localhost:8080/jenkins", "zaphod", "beeblebrox".toCharArray());
        assertNotNull(server);
    }
    
    @Test
    public void testGetJobsNull() throws URISyntaxException {
        JenkinsServer server = JenkinsHandler.getJenkins(null, null, null);
        assertNull(server);
    }
    
    /* This actually tried to connect which is not good, and JenkinsServer cannot be mocked by Mockto.
       I'm not sure how to test this.
    @Test
    public void testGetJenkinsJobsWorks() throws URISyntaxException, IOException {
        JenkinsServer server = JenkinsHandler.getJenkins("http://localhost:8080/jenkins", "user", "credential".toCharArray());
        Mockito.when(server.getJobs()).thenReturn(new HashMap<>());
        Map<String,Job> jobMap = JenkinsHandler.getJobs(server);
        assertEquals(0, jobMap.size());
    }
    */
    
    @Test
    public void testGetJobsNullServer() {
        JenkinsServer server = null;
        Map<String,Job> jobMap = JenkinsHandler.getJobs(server);
        assertNull(jobMap);
    }
    
    @Test
    public void testGetBuildsWithDetails() throws IOException {
        // create build list and indexes first (these indices correspond to successful builds)
        int indices[] = { 1, 3 };
        List<Build> buildList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Build build = Mockito.mock(Build.class);
            BuildWithDetails buildDetails = Mockito.mock(BuildWithDetails.class);
            Mockito.when(build.details()).thenReturn(buildDetails);
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
            buildList.add(build);
        }
        // now call the function.  we should get back builds 1 and 3 but not 2 and 4
        List<BuildWithDetails> buildDetails = JenkinsHandler.getBuildsWithDetails(indices, buildList);
        
        for (BuildWithDetails x : buildDetails) {
            assertTrue(x.getResult() == BuildResult.SUCCESS);
        }
    }
}
