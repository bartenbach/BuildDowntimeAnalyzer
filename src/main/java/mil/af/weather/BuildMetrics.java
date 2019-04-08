/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
    
}
