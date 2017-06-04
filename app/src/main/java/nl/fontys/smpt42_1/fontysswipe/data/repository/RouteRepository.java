package nl.fontys.smpt42_1.fontysswipe.data.repository;

import nl.fontys.smpt42_1.fontysswipe.data.contract.RouteContract;
import nl.fontys.smpt42_1.fontysswipe.data.contract.callback.OnRoutesReceivedCallback;

/**
 * @author SMPT42-1
 */
public final class RouteRepository {

    private final RouteContract context;

    public RouteRepository() {
        this.context = RepositoryConstants.ROUTE_CONTEXT;
    }

    public void getRoutes(OnRoutesReceivedCallback callback) {
        context.getRoutes(callback);
    }

}
