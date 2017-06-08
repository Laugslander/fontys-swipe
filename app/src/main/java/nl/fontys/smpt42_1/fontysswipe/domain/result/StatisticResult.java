package nl.fontys.smpt42_1.fontysswipe.domain.result;

import java.util.List;

import nl.fontys.smpt42_1.fontysswipe.domain.Route;

/**
 * @author SMPT42-1
 */
public final class StatisticResult extends Result {

    private List<Route> routes;

    public StatisticResult(String title, List<Route> routes) {
        super(title);

        this.routes = routes;
    }

    public List<Route> getRoutes() {
        return routes;
    }

}
