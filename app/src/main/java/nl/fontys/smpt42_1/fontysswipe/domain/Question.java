package nl.fontys.smpt42_1.fontysswipe.domain;

import java.util.Map;

/**
 * @author SMPT42-1
 */
public class Question {

    private String image;
    private String text;
    private Map<String, Integer> points;

    public Question() {
        // Empty constructor used for Firebase binding.
    }

    public String getImage() {
        return image;
    }

    public String getText() {
        return text;
    }

    public Map getPoints() {
        return points;
    }

}
