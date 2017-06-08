package nl.fontys.smpt42_1.fontysswipe.domain;

/**
 * @author SMPT42-1
 */
public class Prize {

    private String name;
    private String description;

    public Prize() {
        // Empty constructor used for Firebase binding.
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

}
