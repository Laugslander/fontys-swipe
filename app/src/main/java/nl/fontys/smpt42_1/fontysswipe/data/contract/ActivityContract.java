package nl.fontys.smpt42_1.fontysswipe.data.contract;

import nl.fontys.smpt42_1.fontysswipe.data.contract.callback.OnActivitiesReceivedCallback;

/**
 * @author SMPT42-1
 */
public interface ActivityContract {

    void getActivities(OnActivitiesReceivedCallback callback);

}
