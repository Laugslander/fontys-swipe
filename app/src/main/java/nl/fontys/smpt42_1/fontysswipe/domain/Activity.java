package nl.fontys.smpt42_1.fontysswipe.domain;

import java.util.HashMap;

import nl.fontys.smpt42_1.fontysswipe.domain.Interfaces.CompareAlgo;

/**
 * @author SMPT42-1
 */
public class Activity implements CompareAlgo{

    private HashMap<String, Integer> points;
    private String title;

    public Activity() {
        // Empty constructor used for Firebase binding.
    }

    public String getTitle() {
        return title;
    }

    @Override
    public HashMap<String, Integer> getPoints() {
        return points;
    }
}
