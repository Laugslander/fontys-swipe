package nl.fontys.smpt42_1.fontysswipe.data.contract;

import nl.fontys.smpt42_1.fontysswipe.data.contract.callback.OnSchoolsReceivedCallback;

/**
 * @author SMPT42-1
 */
public interface SchoolContract {

    void getSchools(OnSchoolsReceivedCallback callback);

}
