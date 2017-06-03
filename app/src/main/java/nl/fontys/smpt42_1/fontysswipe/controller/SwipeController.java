package nl.fontys.smpt42_1.fontysswipe.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

import nl.fontys.smpt42_1.fontysswipe.data.contract.callback.OnQuestionsReceivedCallback;
import nl.fontys.smpt42_1.fontysswipe.data.contract.callback.OnRoutesReceivedCallback;
import nl.fontys.smpt42_1.fontysswipe.data.repository.QuestionRepository;
import nl.fontys.smpt42_1.fontysswipe.data.repository.RouteRepository;
import nl.fontys.smpt42_1.fontysswipe.domain.Question;
import nl.fontys.smpt42_1.fontysswipe.domain.Route;
import nl.fontys.smpt42_1.fontysswipe.domain.RouteScore;

/**
 * @author SMPT42-1
 */
public final class SwipeController {

    private static final int NUMBER_OF_TOP_ROUTES = 12; // Current maximum number of routes is 12.

    private static SwipeController instance;

    private SwipeControllerDelegate delegate;

    private List<Route> routes; // List of all routes.
    private List<Question> questions; // List of all questions.
    private Map<Route, Integer> total; // Map of all routes and the corresponding total number of points.

    private int questionCounter;
    private int pointsCounter;

    private SwipeController(SwipeControllerDelegate delegate) {
        this.delegate = delegate;

        total = new HashMap<>();

        questionCounter = 0;
        pointsCounter = 0;

        retrieveData();
    }

    public static SwipeController createInstance(SwipeControllerDelegate delegate) {
        instance = new SwipeController(delegate);
        return instance;
    }

    public static SwipeController getInstance() {
        return instance;
    }

    private void retrieveData() {
        retrieveRoutes();
        retrieveQuestions();
    }

    private void retrieveRoutes() {
        new RouteRepository().getRoutes(new OnRoutesReceivedCallback() {
            @Override
            public void onRoutesReceived(List<Route> routes) {
                SwipeController.this.routes = routes;
                for (Route route : routes) SwipeController.this.total.put(route, 0);
            }
        });
    }

    private void retrieveQuestions() {
        new QuestionRepository().getQuestions(new OnQuestionsReceivedCallback() {
            @Override
            public void onQuestionsReceived(List<Question> questions) {
                SwipeController.this.questions = questions;
                // Notify the MainActivity when the question data is loaded.
                delegate.onSwipeControllerInitialized();
            }
        });
    }

    public void processQuestion(boolean answer) {
        Question question = questions.get(questionCounter);
        Map<String, Integer> questionPoints = question.getPoints();

        questionCounter++;

        for (Route route : routes) {
            // Check if the answer on the question was positive (yes).
            if (answer) {
                // Add the question total to map of routes and total total.
                int points = question.getPoints().get(route.getAbbreviation());
                total.put(route, total.get(route) + points);
                pointsCounter += points;
            }
        }

        // Notify the MainActivity when all questions are processed.
        if (questionCounter == questions.size()) {
            delegate.onAllQuestionsProcessed();
        }
    }

    public List<RouteScore> getTopRoutes() {
        Queue<RouteScore> queue = new PriorityQueue<>();
        List<RouteScore> routes = new ArrayList<>();

        // Add all routes and corresponding total points to the queue as new RouteScore objects.
        for (Route route : total.keySet()) {
            int percentage = (int) ((float) total.get(route) / pointsCounter * 100);
            queue.add(new RouteScore(route.getName(), percentage));
        }

        // Poll the top number of routes from the queue and add them to the list.
        for (int i = 0; i < NUMBER_OF_TOP_ROUTES; i++) {
            routes.add(queue.poll());
        }

        return routes;
    }

    public List<Question> getQuestions() {
        return questions;
    }

}
