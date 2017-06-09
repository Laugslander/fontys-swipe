package nl.fontys.smpt42_1.fontysswipe.data.contract.callback;

import java.util.List;

import nl.fontys.smpt42_1.fontysswipe.domain.Activity;

/**
 * @author SMPT42-1
 */
public interface OnActivitiesReceivedCallback {

    void onActivitiesReceived(List<Activity> activities);


}
