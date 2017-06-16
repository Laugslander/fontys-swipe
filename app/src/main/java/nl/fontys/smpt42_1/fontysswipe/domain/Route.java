package nl.fontys.smpt42_1.fontysswipe.domain;

import android.support.annotation.NonNull;

/**
 * @author SMPT42-1
 */
public class Route implements Comparable<Route> {

    private String abbreviation;
    private String name;
    private boolean mainRoute;
    private double userPoints;
    private int maxPoints;

    public Route() {
        // Empty constructor used for Firebase binding.
    }

    public void addUserPoints(double points) {
        this.userPoints += points;
    }

    public void addMaxPoints(int points) {
        maxPoints += points;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public String getName() {
        return name;
    }

    public boolean isMainRoute() {
        return mainRoute;
    }

    public double getUserPoints() {
        return userPoints;
    }

    public void setUserPoints(double point) {
        userPoints = point;
    }

    public int getMaxPoints() {
        return maxPoints;
    }

    public float getPercentage() {
        return (float) userPoints / (maxPoints / 100.0F) * 100.0F;
    }

    @Override
    public int compareTo(@NonNull Route other) {
        return (int) (other.getPercentage() - getPercentage());
    }

}
