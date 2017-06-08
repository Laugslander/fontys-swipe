package nl.fontys.smpt42_1.fontysswipe.domain.result;

import android.app.Activity;

import java.util.List;

/**
 * @author SMPT42-1
 */
public class ActivityResult extends Result {

    private final List<Activity> activities;

    public ActivityResult(String title, List<Activity> activities) {
        super(title);

        this.activities = activities;
    }

    public List<Activity> getActivities() {
        return activities;
    }

}
