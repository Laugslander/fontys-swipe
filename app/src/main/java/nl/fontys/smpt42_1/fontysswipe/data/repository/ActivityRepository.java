package nl.fontys.smpt42_1.fontysswipe.data.repository;

import nl.fontys.smpt42_1.fontysswipe.data.contract.ActivityContract;
import nl.fontys.smpt42_1.fontysswipe.data.contract.callback.OnActivitiesReceivedCallback;

/**
 * @author SMPT42-1
 */
public class ActivityRepository {

    private final ActivityContract context;

    public ActivityRepository() {
        this.context = RepositoryConstants.ACTIVITY_CONTEXT;
    }

    public void getActivities(OnActivitiesReceivedCallback callback) {
        context.getActivities(callback);
    }

}
