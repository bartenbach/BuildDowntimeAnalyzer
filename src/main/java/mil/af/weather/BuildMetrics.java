package mil.af.weather;

import com.offbytwo.jenkins.model.BuildResult;
import com.offbytwo.jenkins.model.BuildWithDetails;
import java.text.NumberFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * A simple container object and functions related to build metrics.
 */
public class BuildMetrics {

    /**
     * The total downtime of the build in milliseconds.
     */
    private long buildDowntime = 0;
    /**
     * The total uptime of the build in milliseconds.
     */
    private long buildUptime = 0;
    /**
     * The total number of failed builds.
     */
    private int failedBuilds = 0;
    /**
     * The total number of successful builds.
     */
    private int successfulBuilds = 0;

    /**
     * Accepts a list of BuildWithDetails objects from the jenkins-client API
     * and based on the statuses of the builds, calculates the total uptime and
     * downtime of the provided list of builds.
     *
     * @param selectedBuilds a list of BuildWithDetails objects from
     * jenkins-client API.
     * @param containsFinalBuild - true if the provided list contains the final build for the job.
     * @return BuildMetrics - the metrics for the provided build.
     */
    public BuildMetrics buildMetrics(List<BuildWithDetails> selectedBuilds, boolean containsFinalBuild) {
        BuildWithDetails build, nextBuild;
        BuildResult status;
        for (int i = 0; i < selectedBuilds.size() - 1; i++) {
            build = selectedBuilds.get(i);
            countBuildStatus(build);
            nextBuild = selectedBuilds.get(i + 1);
            if (build.isBuilding() || nextBuild.isBuilding()) {
                JOptionPane.showMessageDialog(null, "One of the selected builds is still building which may skew statistics.",
                        "Warning", JOptionPane.WARNING_MESSAGE);
            }
            status = nextBuild.getResult();
            switch (status) {
                case FAILURE:
                    this.addDowntime(build.getTimestamp() - nextBuild.getTimestamp());
                    break;
                case SUCCESS:
                    this.addUptime(build.getTimestamp() - nextBuild.getTimestamp());
                    break;
            }
        }
        // count the first build
        countBuildStatus(selectedBuilds.get(selectedBuilds.size() - 1));
        // the final build is actually the first element because the list is reversed.
        if (containsFinalBuild) {
            addLastBuild(selectedBuilds.get(0));
        }
        return this;
    }

    public void countBuildStatus(BuildWithDetails build) {
        if (build.getResult() == BuildResult.FAILURE) {
            this.failedBuilds += 1;
        } else if (build.getResult() == BuildResult.SUCCESS) {
            this.successfulBuilds += 1;
        }
    }

    /**
     * Calculate the uptime percentage based on the values of this BuildMetrics
     * object.
     *
     * @return The formatted uptime percentage.
     */
    public String getUptimePercentage() {
        double downtimePercentage = (double) this.getDowntime() / this.getTotalTime();
        double uptimePercentage = 1 - downtimePercentage;
        NumberFormat nf = NumberFormat.getPercentInstance();
        nf.setMaximumFractionDigits(2);
        nf.setMinimumFractionDigits(1);
        return nf.format(uptimePercentage);
    }

    public void addDowntime(long downtime) {
        this.buildDowntime += downtime;
    }

    public void addUptime(long uptime) {
        this.buildUptime += uptime;
    }

    public long getDowntime() {
        return this.buildDowntime;
    }

    public long getUptime() {
        return this.buildUptime;
    }

    public long getTotalTime() {
        return this.buildDowntime + this.buildUptime;
    }

    public int getFailedBuilds() {
        return this.failedBuilds;
    }

    public int getSuccessfulBuilds() {
        return this.successfulBuilds;
    }

    /**
     * Calculates the final build's uptime or downtime. This is different
     * because it relies on the current time, not a build's timestamp.
     *
     * @param finalBuild - The last build in the list.
     */
    private void addLastBuild(BuildWithDetails finalBuild) {
        long now = new Date().getTime();
        // if it's not a build failure, I don't think it should count as downtime.  things like
        // cancelled or aborted builds aren't really downtime in my opinion.
        if (finalBuild.getResult() == BuildResult.FAILURE) {
            this.addDowntime(now - finalBuild.getTimestamp());
        } else {
            this.addUptime(now - finalBuild.getTimestamp());
        }
    }

}
