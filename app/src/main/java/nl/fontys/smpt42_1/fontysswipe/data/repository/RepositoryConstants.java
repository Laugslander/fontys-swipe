package nl.fontys.smpt42_1.fontysswipe.data.repository;

import nl.fontys.smpt42_1.fontysswipe.data.contract.ActivityContract;
import nl.fontys.smpt42_1.fontysswipe.data.contract.PrizeContract;
import nl.fontys.smpt42_1.fontysswipe.data.contract.QuestionContract;
import nl.fontys.smpt42_1.fontysswipe.data.contract.RouteContract;
import nl.fontys.smpt42_1.fontysswipe.data.contract.SchoolContract;
import nl.fontys.smpt42_1.fontysswipe.data.contract.TeacherContract;
import nl.fontys.smpt42_1.fontysswipe.data.firebase.ActivityFirebaseContext;
import nl.fontys.smpt42_1.fontysswipe.data.firebase.PrizeFirebaseContext;
import nl.fontys.smpt42_1.fontysswipe.data.firebase.QuestionFirebaseContext;
import nl.fontys.smpt42_1.fontysswipe.data.firebase.RouteFirebaseContext;
import nl.fontys.smpt42_1.fontysswipe.data.firebase.SchoolFirebaseContext;
import nl.fontys.smpt42_1.fontysswipe.data.firebase.TeacherFirebaseContext;

/**
 * @author SMPT42-1
 */
final class RepositoryConstants {

    static final QuestionContract QUESTION_CONTEXT = new QuestionFirebaseContext();
    static final RouteContract ROUTE_CONTEXT = new RouteFirebaseContext();
    static final TeacherContract TEACHER_CONTEXT = new TeacherFirebaseContext();
    static final ActivityContract ACTIVITY_CONTEXT = new ActivityFirebaseContext();
    static final SchoolContract SCHOOL_CONTEXT = new SchoolFirebaseContext();
    static final PrizeContract PRIZE_CONTEXT = new PrizeFirebaseContext();

}
