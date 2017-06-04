package nl.fontys.smpt42_1.fontysswipe.data.repository;

import nl.fontys.smpt42_1.fontysswipe.data.contract.QuestionContract;
import nl.fontys.smpt42_1.fontysswipe.data.contract.RouteContract;
import nl.fontys.smpt42_1.fontysswipe.data.contract.TeacherContract;
import nl.fontys.smpt42_1.fontysswipe.data.firebase.QuestionFirebaseContext;
import nl.fontys.smpt42_1.fontysswipe.data.firebase.RouteFirebaseContext;
import nl.fontys.smpt42_1.fontysswipe.data.firebase.TeacherFirebaseContext;

/**
 * @author SMPT42-1
 */
final class RepositoryConstants {

    static final QuestionContract QUESTION_CONTEXT = new QuestionFirebaseContext();
    static final RouteContract ROUTE_CONTEXT = new RouteFirebaseContext();
    static final TeacherContract TEACHER_CONTEXT = new TeacherFirebaseContext();

}
