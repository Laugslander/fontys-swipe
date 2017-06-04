package nl.fontys.smpt42_1.fontysswipe.domain;

import java.util.HashMap;

/**
 * @author SMPT42-1
 */
public class Teacher {

    private String id;
    private String name;
    private String email;
    private String phone;
    private String image;
    private HashMap<String, Integer> points;

    public Teacher() {
        // Empty constructor used for Firebase binding.
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getImage() {
        return image;
    }

    public HashMap<String, Integer> getPoints() {
        return points;
    }

}
