package nl.fontys.smpt42_1.fontysswipe.domain;

import android.content.Context;
import android.telephony.TelephonyManager;

/**
 * @author SMPT42-1
 */
public class Prize {

    private String image;
    private String catchphrase;
    private String description;

    public Prize() {
        // Empty constructor used for Firebase binding.
    }

    public String getImage() {
        return image;
    }

    public String getCatchphrase() {
        return catchphrase;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
