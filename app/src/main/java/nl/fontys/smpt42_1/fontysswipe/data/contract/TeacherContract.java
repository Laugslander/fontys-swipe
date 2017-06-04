package nl.fontys.smpt42_1.fontysswipe.data.contract;

import nl.fontys.smpt42_1.fontysswipe.data.contract.callback.OnTeachersReceivedCallback;

/**
 * @author SMPT42-1
 */
public interface TeacherContract {

    void getTeachers(OnTeachersReceivedCallback callback);

}
