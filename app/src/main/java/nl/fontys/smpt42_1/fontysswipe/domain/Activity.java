package nl.fontys.smpt42_1.fontysswipe.domain;

import java.util.List;
import java.util.Map;

/**
 * @author SMPT42-1
 */
public class Activity {

    private String title;
    private Map<String, List<String>> timetable;

    public Activity() {
        // Empty constructor used for Firebase binding.
    }

    public String getTitle() {
        return title;
    }

    public Map<String, List<String>> getTimetable() {
        return timetable;
    }

}
