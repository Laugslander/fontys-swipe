package nl.fontys.smpt42_1.fontysswipe.data.contract;

import nl.fontys.smpt42_1.fontysswipe.data.contract.callback.OnTeacherImageLinkReceivedCallback;
import nl.fontys.smpt42_1.fontysswipe.data.contract.callback.OnTeachersReceivedCallback;

/**
 * @author SMPT42-1
 */
public interface TeacherContract {

    void getTeachers(OnTeachersReceivedCallback callback);

    void getTeacherImageUri(String image, OnTeacherImageLinkReceivedCallback callback);

}
