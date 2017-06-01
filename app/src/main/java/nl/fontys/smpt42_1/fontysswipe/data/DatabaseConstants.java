package nl.fontys.smpt42_1.fontysswipe.data;

import nl.fontys.smpt42_1.fontysswipe.data.contract.QuestionContract;
import nl.fontys.smpt42_1.fontysswipe.data.contract.RouteContract;
import nl.fontys.smpt42_1.fontysswipe.data.firebase.QuestionFirebaseContext;
import nl.fontys.smpt42_1.fontysswipe.data.firebase.RouteFirebaseContext;

/**
 * @author SMPT42-1
 */
public final class DatabaseConstants {

    public static final QuestionContract QUESTION_CONTEXT = new QuestionFirebaseContext();
    public static final RouteContract ROUTE_CONTEXT = new RouteFirebaseContext();

}
