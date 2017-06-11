package nl.fontys.smpt42_1.fontysswipe.data.repository;

import nl.fontys.smpt42_1.fontysswipe.data.contract.SchoolContract;
import nl.fontys.smpt42_1.fontysswipe.data.contract.callback.OnSchoolsReceivedCallback;

/**
 * @author SMPT42-1
 */
public final class SchoolRepository {

    private final SchoolContract context;

    public SchoolRepository() {
        this.context = RepositoryConstants.SCHOOL_CONTEXT;
    }

    public void getLocations(OnSchoolsReceivedCallback callback) {
        context.getSchools(callback);
    }


}
