package nl.fontys.smpt42_1.fontysswipe.data.repository;

import nl.fontys.smpt42_1.fontysswipe.data.contract.TeacherContract;
import nl.fontys.smpt42_1.fontysswipe.data.contract.callback.OnTeachersReceivedCallback;

/**
 * @author SMPT42-1
 */
public class TeacherRepository {

    private final TeacherContract context;

    public TeacherRepository() {
        this.context = RepositoryConstants.TEACHER_CONTEXT;
    }

    public void getTeachers(OnTeachersReceivedCallback callback) {
        context.getTeachers(callback);
    }

}
