package nl.fontys.smpt42_1.fontysswipe.domain.result;

import java.util.List;

import nl.fontys.smpt42_1.fontysswipe.domain.Route;

/**
 * @author Robin Laugs
 */

public final class StatisticsResult extends Result {

    private List<Route> routes;

    public StatisticsResult(String title, List<Route> routes) {
        super(title);

        this.routes = routes;
    }

    public List<Route> getRoutes() {
        return routes;
    }

}
