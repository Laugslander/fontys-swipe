package nl.fontys.smpt42_1.fontysswipe.controller;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import nl.fontys.smpt42_1.fontysswipe.domain.Route;
import nl.fontys.smpt42_1.fontysswipe.domain.Teacher;
import nl.fontys.smpt42_1.fontysswipe.domain.interfaces.CompareAlgo;
import nl.fontys.smpt42_1.fontysswipe.util.FindRouteUtilKt;

/**
 * @author SMPT42-1
 */
class CompareController {

    private static CompareController instance;

    private CompareController() {
        // Marked private because the CompareController should never be instantianted outside this class.
    }

    public static CompareController getInstance() {
        return instance == null ? instance = new CompareController() : instance;
    }

    /**
     * Compare teacher methode compared alle docenten met 1 student en kijkt voor alle docenten welke docenten de beste match is.
     *
     * @param userPoints een hashmap met de studie profielen en het aantal punten dat de gebruiker daarbij heeft.
     * @param comparables   all comparable objects.
     * @return een gesorteerde map van docenten met het aantal procenten dat matcht.
     */
    List<Teacher> compareTeachers(List<Route> userPoints, List<Teacher> comparables) {
        HashMap<String, Double> differenceMap = new HashMap<>();
        HashMap<CompareAlgo, Double> resultMap = new HashMap<>();
        List<Route> correctUserPoints = new ArrayList<Route>();

        for(Route route : userPoints){
            route.setUserPoints(route.getUserPoints() / (route.getMaxPoints() / 10));
            correctUserPoints.add(route);
        }

        for (CompareAlgo comparable : comparables) {
            double result = 0;

            Map<String, Integer> teachersMap = comparable.getPoints();

            for (final Map.Entry<String, Integer> teacherEntry : teachersMap.entrySet()) {

                Route route = FindRouteUtilKt.findRoute(teacherEntry.getKey(), correctUserPoints);

                System.out.println("Naam: " + route.getAbbreviation() + " user points: " + route.getUserPoints());

                double difference = Math.abs(route.getUserPoints() - teacherEntry.getValue());
                differenceMap.put(teacherEntry.getKey(), difference);

                result = result + (differenceMap.get(route.getAbbreviation()) * (route.getUserPoints() * 0.1));

                System.out.println("Naam: " + route.getAbbreviation() + " punten: " + route.getUserPoints() + " max points: " +
                route.getMaxPoints());
            }

            resultMap.put(comparable, result);
        }

        return new ArrayList(sortByValue(resultMap).keySet());
    }

    private static Map<CompareAlgo, Double> sortByValue(Map<CompareAlgo, Double> unsortMap) {

        List<Map.Entry<CompareAlgo, Double>> list = new LinkedList<Map.Entry<CompareAlgo, Double>>(unsortMap.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<CompareAlgo, Double>>() {

            public int compare(Map.Entry<CompareAlgo, Double> o1,
                               Map.Entry<CompareAlgo, Double> o2) {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });

        Map<CompareAlgo, Double> sortedMap = new LinkedHashMap<CompareAlgo, Double>();
        for (Map.Entry<CompareAlgo, Double> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        for (Map.Entry<CompareAlgo, Double> entry : sortedMap.entrySet()) {
            System.out.println("Key : " + entry.getKey().getName()
                    + " Value : " + entry.getValue());
        }

        return sortedMap;
    }
}
