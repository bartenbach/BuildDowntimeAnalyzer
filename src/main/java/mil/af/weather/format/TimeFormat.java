package mil.af.weather.format;

import java.util.concurrent.TimeUnit;

/**
 *
 * @author Blake Bartenbach
 */
public final class TimeFormat {
    
    /**
     * Utility class.
     */
    private TimeFormat() {}
    
    /**
     * Formats the milliseconds from the BuildWithDetails objects into a more human readable format.
     * @param time - The milliseconds to convert.
     * @return String - the formatted time string as XX hours XX minutes XX seconds
     */
    public static String formatMillisecondsToReadableTime(long time) {
        return String.format("%02d hours %02d minutes %02d seconds", TimeUnit.MILLISECONDS.toHours(time),
                TimeUnit.MILLISECONDS.toMinutes(time) % TimeUnit.HOURS.toMinutes(1),
                TimeUnit.MILLISECONDS.toSeconds(time) % TimeUnit.MINUTES.toSeconds(1));
    }
    
}
