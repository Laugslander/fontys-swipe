package nl.fontys.smpt42_1.fontysswipe.data.contract;

import nl.fontys.smpt42_1.fontysswipe.data.contract.callback.OnRoutesReceivedCallback;

/**
 * @author SMPT42-1
 */
public interface RouteContract {

    void getRoutes(OnRoutesReceivedCallback callback);

}
