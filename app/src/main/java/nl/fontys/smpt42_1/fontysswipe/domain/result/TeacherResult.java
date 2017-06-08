package nl.fontys.smpt42_1.fontysswipe.domain.result;

import java.util.List;

import nl.fontys.smpt42_1.fontysswipe.domain.Teacher;

/**
 * @author SMPT42-1
 */
public final class TeacherResult extends Result {

    private final List<Teacher> teachers;

    public TeacherResult(String title, List<Teacher> teachers) {
        super(title);

        this.teachers = teachers;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

}
