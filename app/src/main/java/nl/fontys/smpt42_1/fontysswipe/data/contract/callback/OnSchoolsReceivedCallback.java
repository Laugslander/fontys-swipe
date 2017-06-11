package nl.fontys.smpt42_1.fontysswipe.data.contract.callback;

import java.util.List;

import nl.fontys.smpt42_1.fontysswipe.domain.School;

/**
 * @author SMPT42-1
 */
public interface OnSchoolsReceivedCallback {

    void onSchoolsReceived(List<School> schools);

}

