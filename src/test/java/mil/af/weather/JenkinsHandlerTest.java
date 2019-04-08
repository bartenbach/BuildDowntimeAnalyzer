package mil.af.weather;

import com.offbytwo.jenkins.JenkinsServer;
import java.net.URI;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;

public class JenkinsHandlerTest {
    
    @Test
    public void testGetJenkinsUriNull() {
        String bogusUri = "!@#$%^&*()-+=[]{}|<>";
        URI uri = JenkinsHandler.getJenkinsURI(bogusUri);
        assertNull(uri);
    }
    
    @Test
    public void testGetJenkinsServerNull() {
        JenkinsServer server = JenkinsHandler.getJenkins(null, null, null);
        assertNull(server);
    }
}
