package nl.fontys.smpt42_1.fontysswipe.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import nl.fontys.smpt42_1.fontysswipe.data.contract.callback.OnActivitiesReceivedCallback;
import nl.fontys.smpt42_1.fontysswipe.data.contract.callback.OnPrizeReceivedCallback;
import nl.fontys.smpt42_1.fontysswipe.data.contract.callback.OnQuestionsReceivedCallback;
import nl.fontys.smpt42_1.fontysswipe.data.contract.callback.OnRoutesReceivedCallback;
import nl.fontys.smpt42_1.fontysswipe.data.contract.callback.OnSchoolsReceivedCallback;
import nl.fontys.smpt42_1.fontysswipe.data.contract.callback.OnTeachersReceivedCallback;
import nl.fontys.smpt42_1.fontysswipe.data.repository.ActivityRepository;
import nl.fontys.smpt42_1.fontysswipe.data.repository.PrizeRepository;
import nl.fontys.smpt42_1.fontysswipe.data.repository.QuestionRepository;
import nl.fontys.smpt42_1.fontysswipe.data.repository.RouteRepository;
import nl.fontys.smpt42_1.fontysswipe.data.repository.SchoolRepository;
import nl.fontys.smpt42_1.fontysswipe.data.repository.TeacherRepository;
import nl.fontys.smpt42_1.fontysswipe.domain.Activity;
import nl.fontys.smpt42_1.fontysswipe.domain.Prize;
import nl.fontys.smpt42_1.fontysswipe.domain.Question;
import nl.fontys.smpt42_1.fontysswipe.domain.Route;
import nl.fontys.smpt42_1.fontysswipe.domain.School;
import nl.fontys.smpt42_1.fontysswipe.domain.Teacher;
import nl.fontys.smpt42_1.fontysswipe.domain.result.ActivityResult;
import nl.fontys.smpt42_1.fontysswipe.domain.result.PrizeResult;
import nl.fontys.smpt42_1.fontysswipe.domain.result.Result;
import nl.fontys.smpt42_1.fontysswipe.domain.result.StatisticResult;
import nl.fontys.smpt42_1.fontysswipe.domain.result.TeacherResult;

/**
 * @author SMPT42-1
 */
public final class SwipeController {

    private static final int NUMBER_OF_TOP_ROUTES = 4; // Current maximum number of routes is 12.
    private static final String STATISTIC_RESULT_TITLE = "Statistics";
    private static final String TEACHER_RESULT_TITLE = "Teachers to talk with";
    private static final String ACTIVITY_RESULT_TITLE = "Workshops to attend";
    private static final String PRIZE_RESULT_TITLE = "Win the prize";

    private static SwipeController instance;

    private SwipeControllerMainListener mainListener;
    private SwipeControllerResultListener resultListener;
    private LocationController location;

    private List<Question> questions;
    private List<Route> routes;
    private List<Teacher> teachers;
    private List<Result> results;
    private List<Activity> activities;
    private Prize prize;

    private School school;
    private int questionCounter;

    private SwipeController(SwipeControllerMainListener listener) {
        mainListener = listener;

        results = new ArrayList<>();

        questionCounter = 0;

        retrieveAppData();
    }

    public static SwipeController setInstance(SwipeControllerMainListener listener) {
        instance = new SwipeController(listener);
        return instance;
    }

    public static SwipeController getInstance() {
        return instance;
    }

    public void setSwipeControllerResultListener(SwipeControllerResultListener listener) {
        resultListener = listener;
    }

    private void retrieveAppData() {
        retrieveLocations();
        retrieveQuestions();
        retrieveRoutes();
        retrieveTeachers();
        retrieveActivities();
        retrievePrize();
    }

    private void retrieveLocations() {
        new SchoolRepository().getLocations(new OnSchoolsReceivedCallback() {
            @Override
            public void onSchoolsReceived(List<School> schools) {
                // Check at which school location the app user is.
                location = new LocationController((android.app.Activity) mainListener, schools);
                school = location.getSchoolLocation();
            }
        });
    }

    private void retrieveQuestions() {
        new QuestionRepository().getQuestions(new OnQuestionsReceivedCallback() {
            @Override
            public void onQuestionsReceived(List<Question> questions) {
                SwipeController.this.questions = questions;
                // Notify the MainActivity that the location data is retrieved.
                mainListener.onSwipeControllerInitialized();
            }
        });
    }

    private void retrieveRoutes() {
        new RouteRepository().getRoutes(new OnRoutesReceivedCallback() {
            @Override
            public void onRoutesReceived(List<Route> routes) {
                SwipeController.this.routes = routes;
            }
        });
    }

    private void retrieveTeachers() {
        new TeacherRepository().getTeachers(new OnTeachersReceivedCallback() {
            @Override
            public void onTeachersReceived(List<Teacher> teachers) {
                SwipeController.this.teachers = teachers;
                updateResults();
            }
        });
    }

    private void retrieveActivities() {
        new ActivityRepository().getActivities(new OnActivitiesReceivedCallback() {
            @Override
            public void onActivitiesReceived(List<Activity> activities) {
                SwipeController.this.activities = activities;
                updateResults();
            }
        });
    }

    private void retrievePrize() {
        new PrizeRepository().getPrize(new OnPrizeReceivedCallback() {
            @Override
            public void onPrizeReceived(Prize prize) {
                SwipeController.this.prize = prize;
                updateResults();
            }
        });
    }

    private void updateResults() {
        if (resultListener != null) {
            // Regenerate the results.
            generateResults();
            // Notify the ResultActivity that the results have changed.
            resultListener.onResultsUpdated();
        }
    }

    public void processQuestion(boolean answer) {
        Question question = questions.get(questionCounter);

        questionCounter++;

        for (Route route : routes) {
            // Check if the answer on the question is positive (yes).
            if (answer) {
                // Add the question points to the user points of the route.
                int points = question.getPoints().get(route.getAbbreviation());
                route.addUserPoints(points);
            }
        }

        // Check if all questions are processed.
        if (questionCounter == questions.size()) {
            // Generate the results.
            generateResults();
            // Notify the MainActivity that all questions are processed.
            mainListener.onAllQuestionsProcessed();
        }
    }

    private void generateResults() {
        results.clear();
        results.add(new StatisticResult(STATISTIC_RESULT_TITLE, getTopRoutes()));
        results.add(new TeacherResult(TEACHER_RESULT_TITLE, teachers));
        results.add(new ActivityResult(ACTIVITY_RESULT_TITLE, activities));
        results.add(new PrizeResult(PRIZE_RESULT_TITLE, prize));
    }

    private List<Route> getTopRoutes() {
        Queue<Route> queue = new PriorityQueue<>(routes);
        List<Route> routes = new ArrayList<>();

        // Poll the top number of routes from the queue and add them to the list.
        for (int i = 0; i < NUMBER_OF_TOP_ROUTES; i++) routes.add(queue.poll());

        return routes;
    }

    public int getQuestionsProgress() {
        double percentage = ((double) questionCounter / questions.size()) * 100;
        return (int) percentage;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public List<Result> getResults() {
        return results;
    }

}
