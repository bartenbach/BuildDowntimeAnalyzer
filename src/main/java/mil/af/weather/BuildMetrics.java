package mil.af.weather;

import com.offbytwo.jenkins.model.BuildResult;
import com.offbytwo.jenkins.model.BuildWithDetails;
import java.text.NumberFormat;
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
     * Accepts a list of BuildWithDetails objects from the jenkins-client API and
     * based on the statuses of the builds, calculates the total uptime and downtime
     * of the provided list of builds.
     * @param selectedBuilds a list of BuildWithDetails objects from jenkins-client API.
     * @return BuildMetrics - the metrics for the provided build.
     */
    public BuildMetrics calculateMetrics(List<BuildWithDetails> selectedBuilds) {
        BuildWithDetails build, nextBuild;
        BuildResult status;
        for (int i = 0; i < selectedBuilds.size() - 1; i++) {
            build = selectedBuilds.get(i);
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
        return this;
    }
    
    /**
     * Calculate the uptime percentage based on the values of this BuildMetrics object.
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

}
