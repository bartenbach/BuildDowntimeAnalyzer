package mil.af.weather.format;

import java.util.concurrent.TimeUnit;
import mil.af.weather.BuildMetrics;

/**
 *
 * @author Blake Bartenbach
 */
public class TimeFormat {
    
    /**
     * Formats the milliseconds from the BuildWithDetails objects into a more human readable format.
     * @param metrics - The BuildMetrics object containing the uptime and downtime of the build.
     * @return String - the formatted time string as XX hours XX minutes XX seconds
     */
    public static String formatMillisecondsToReadableTime(BuildMetrics metrics) {
        return String.format("%02d hours %02d minutes %02d seconds", TimeUnit.MILLISECONDS.toHours(metrics.getDowntime()),
                TimeUnit.MILLISECONDS.toMinutes(metrics.getDowntime()) % TimeUnit.HOURS.toMinutes(1),
                TimeUnit.MILLISECONDS.toSeconds(metrics.getDowntime()) % TimeUnit.MINUTES.toSeconds(1));
    }
    
}
