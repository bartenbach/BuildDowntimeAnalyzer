package mil.af.weather;

/**
 * A simple container object for build metrics.
 */
public class BuildMetrics {
    
    private long buildDowntime = 0;
    private long buildUptime = 0;
    
    public void addDowntime(long downtime) {
        this.buildDowntime+=downtime;
    }
    
    public void addUptime(long uptime) {
        this.buildUptime+=uptime;
    }
    
    public long getDowntime() {
        return this.buildDowntime;
    }
    
    public long getUptime() {
        return this.buildUptime;
    }

    long getTotalTime() {
        return this.buildDowntime + this.buildUptime;
    }
    
}
