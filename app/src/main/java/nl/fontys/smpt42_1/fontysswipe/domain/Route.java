package nl.fontys.smpt42_1.fontysswipe.domain;

import android.support.annotation.NonNull;

/**
 * @author SMPT42-1
 */
public class Route implements Comparable<Route> {

    private String abbreviation;
    private String name;
    private int userPoints;

    public Route() {
        // Empty constructor used for Firebase binding.
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public String getName() {
        return name;
    }

    public int getUserPoints() {
        return userPoints;
    }

    public void addUserPoints(int points) {
        this.userPoints += points;
    }

    @Override
    public int compareTo(@NonNull Route other) {
        return other.userPoints - userPoints;
    }

}
