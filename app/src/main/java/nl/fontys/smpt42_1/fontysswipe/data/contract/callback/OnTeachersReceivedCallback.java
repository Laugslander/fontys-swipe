package nl.fontys.smpt42_1.fontysswipe.data.contract.callback;

import java.util.List;

import nl.fontys.smpt42_1.fontysswipe.domain.Teacher;

/**
 * @author SMPT42-1
 */
public interface OnTeachersReceivedCallback {

    void onTeachersReceived(List<Teacher> teachers);

}
