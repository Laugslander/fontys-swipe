package nl.fontys.smpt42_1.fontysswipe.data.contract.callback;

import java.util.List;

import nl.fontys.smpt42_1.fontysswipe.domain.Route;

/**
 * @author SMPT42-1
 */
public interface OnRoutesReceivedCallback {

    void onRoutesReceived(List<Route> routes);

}

