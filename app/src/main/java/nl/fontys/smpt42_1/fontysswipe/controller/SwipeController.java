package nl.fontys.smpt42_1.fontysswipe.controller;

import android.content.Context;
import android.telephony.TelephonyManager;

import java.util.ArrayList;
import java.util.List;

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

    private android.app.Activity activity;

    private SwipeController(SwipeControllerMainListener listener) {
        mainListener = listener;

        results = new ArrayList<>();

        questionCounter = 0;

        retrieveData();
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

    private void retrieveData() {
        retrieveInitialData();
        retrieveRoutes();
        retrieveTeachers();
        retrieveActivities();
        retrievePrize();
    }

    private void retrieveInitialData() {
        new SchoolRepository().getLocations(new OnSchoolsReceivedCallback() {
            @Override
            public void onSchoolsReceived(final List<School> schools) {
                // Check at which school location the app user is.
                location = new LocationController((android.app.Activity) mainListener, schools);
                school = location.getSchoolLocation();

                new QuestionRepository().getQuestions(new OnQuestionsReceivedCallback() {
                    @Override
                    public void onQuestionsReceived(List<Question> questions) {
                        SwipeController.this.questions = questions;

                        updateIntialQuestionBasedOnLocation(questions.get(0), school.getCity());

                        // Notify the MainActivity that the location data is retrieved.
                        mainListener.onSwipeControllerInitialized();
                    }
                });
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

                updatePrizeBasedOnLocation(prize, school.getCity());

                updateResults();
            }
        });
    }

    private void updateIntialQuestionBasedOnLocation(Question question, String location) {
        question.setText(String.format(question.getText(), location));
    }

    private void updatePrizeBasedOnLocation(Prize prize, String location) {
        ImeiController imei = new ImeiController((android.app.Activity) mainListener);
        prize.setDescription(String.format(prize.getDescription(), location) + " CODE: " + imei.getImei());
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
            int points = question.getPoints().get(route.getAbbreviation());
            // Check if the answer on the question is positive (yes).
            if (answer) {
                // Add the question points to the user points of the route.
                route.addUserPoints(points);
            }

            // Add the question points to the max points of the route.
            route.addMaxPoints(points);
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
        results.add(new TeacherResult(TEACHER_RESULT_TITLE, CompareController.getInstance().compareTeachers(routes, teachers)));
        results.add(new ActivityResult(ACTIVITY_RESULT_TITLE, CompareController.getInstance().compareWorkshops(routes, activities)));

        // TODO set prize location
        results.add(new PrizeResult(PRIZE_RESULT_TITLE, prize));
    }

    private List<Route> getMainRoutes() {
        List<Route> mainRoutes = new ArrayList<>();

        // Poll the top number of routes from the queue and add them to the list.
        for (Route route : routes) {
            if (route.isMainRoute()) {
                mainRoutes.add(route);
            }
        }

        return mainRoutes;
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
