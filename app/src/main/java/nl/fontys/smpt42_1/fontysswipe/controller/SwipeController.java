package nl.fontys.smpt42_1.fontysswipe.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nl.fontys.smpt42_1.fontysswipe.Initializable;
import nl.fontys.smpt42_1.fontysswipe.data.contract.callback.OnQuestionsReceivedCallback;
import nl.fontys.smpt42_1.fontysswipe.data.contract.callback.OnRoutesReceivedCallback;
import nl.fontys.smpt42_1.fontysswipe.data.repository.QuestionRepository;
import nl.fontys.smpt42_1.fontysswipe.data.repository.RouteRepository;
import nl.fontys.smpt42_1.fontysswipe.domain.Question;
import nl.fontys.smpt42_1.fontysswipe.domain.Route;

/**
 * @author SMPT42-1
 */
public final class SwipeController {

    private static SwipeController instance;

    private Initializable initializable;

    private Map<Route, Integer> points; // Routes and corresponding number of points.
    private List<Route> routes;
    private List<Question> questions;

    private SwipeController(Initializable initializable) {
        this.initializable = initializable;

        points = new HashMap<>();

        retrieveRoutes();
        retrieveQuestions();
    }

    public static SwipeController createInstance(Initializable initializable) {
        instance = new SwipeController(initializable);
        return instance;
    }

    public static SwipeController getInstance() {
        return instance;
    }

    private void retrieveRoutes() {
        new RouteRepository().getRoutes(new OnRoutesReceivedCallback() {
            @Override
            public void onRoutesReceived(List<Route> routes) {
                SwipeController.this.routes = routes;
                for (Route route : routes) SwipeController.this.points.put(route, 0);
            }
        });
    }

    private void retrieveQuestions() {
        new QuestionRepository().getQuestions(new OnQuestionsReceivedCallback() {
            @Override
            public void onQuestionsReceived(List<Question> questions) {
                SwipeController.this.questions = questions;
                initializable.onInitialized();
            }
        });
    }

    public void processQuestion(Question question, boolean answer) {
        Map<String, Integer> questionPoints = question.getPoints();

        for (Route route : routes) {
            points.put(route, answer ? points.get(route) + questionPoints.get(route.getName()) : points.get(route) - questionPoints.get(route.getName()));
        }
    }

    public List<Question> getQuestions() {
        return questions;
    }

}
