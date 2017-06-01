package nl.fontys.smpt42_1.fontysswipe.domain;

/**
 * @author SMPT42-1
 */
public class Route {

    private String abbreviation;
    private String name;

    public Route() {
        // Empty constructor used for Firebase binding.
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public String getName() {
        return name;
    }

}
