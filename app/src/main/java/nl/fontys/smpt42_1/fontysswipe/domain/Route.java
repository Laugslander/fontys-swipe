package nl.fontys.smpt42_1.fontysswipe.domain;

import android.support.annotation.NonNull;

/**
 * @author SMPT42-1
 */
public class Route implements Comparable<Route> {

    private String abbreviation;
    private String name;
    private int points;

    public Route() {
        // Empty constructor used for Firebase binding.
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }

    public void addPoints(int points) {
        this.points += points;
    }

    @Override
    public int compareTo(@NonNull Route other) {
        return other.points - points;
    }

}
