package mil.af.weather;

import com.offbytwo.jenkins.JenkinsServer;
import com.offbytwo.jenkins.model.Build;
import com.offbytwo.jenkins.model.BuildWithDetails;
import com.offbytwo.jenkins.model.Job;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import mil.af.weather.ui.BuildDowntimeAnalyzer;

/**
 * Provides various functions for handling interaction with the jenkins-client
 * API.
 */
public class JenkinsHandler {

    /**
     * Gets a Jenkins instance object from the jenkins-client API.
     *
     * @param jenkinsUrl - The URL of the jenkins server
     * @param username - The username to authenticate with.
     * @param password - The password to authenticate with.
     * @return the JenkinsServer object from the jenkins-client API or null.
     */
    public static JenkinsServer getJenkins(String jenkinsUrl, String username, char[] password) {
        URI jenkinsUri = getJenkinsURI(jenkinsUrl);
        if (jenkinsUri == null || username == null || password == null) {
            return null;
        }
        return new JenkinsServer(jenkinsUri, username, new String(password));
    }

    /**
     * Parses a URL into a URI object.
     *
     * @param url - The URL to parse into a URI object.
     * @return A URI object or null if unable to parse into a URI object.
     */
    public static URI getJenkinsURI(String url) {
        if (url != null) {
            try {
                return new URI(url);
            } catch (URISyntaxException ex) {
                Logger.getLogger(JenkinsHandler.class.getName()).log(Level.SEVERE, "Failed to parse uri: {0}", url);
            }
        }
        return null;
    }

    /**
     * Returns a list of jobs from the supplied Jenkins server in a map.
     *
     * @param server - the server to poll for projects
     * @return a HashMap containing all of the projects and their jobs.
     */
    public static Map<String, Job> getJobs(JenkinsServer server) {
        try {
            return server.getJobs();
        } catch (IOException ex) {
            Logger.getLogger(JenkinsHandler.class.getName()).log(Level.SEVERE, "Failed to retrieve jobs from Jenkins server.");
            return null;
        }
    }

    /**
     * Accepts indexes from the build list and returns a list of BuildWithDetails objects comprised of the 
     * selected builds.
     * @param selectedIndices - The indexes to get details from within the build list.
     * @param buildListContents - The contents of the build list.
     * @return A list containing BuildWithDetails (build details objects) corresponding to the provided
     * build list and indexes.
     */
    public static List<BuildWithDetails> getBuildsWithDetails(int[] selectedIndices, List<Build> buildListContents) {
        List<BuildWithDetails> selectedBuilds = new ArrayList<>();
        for (int i : selectedIndices) {
            try {
                selectedBuilds.add(buildListContents.get(i).details());
            } catch (IOException ex) {
                Logger.getLogger(BuildDowntimeAnalyzer.class.getName()).log(Level.SEVERE,
                        "Failed to get build details for build with number: {0}", buildListContents.get(i).getNumber());
            }
        }
        return selectedBuilds;
    }

}
