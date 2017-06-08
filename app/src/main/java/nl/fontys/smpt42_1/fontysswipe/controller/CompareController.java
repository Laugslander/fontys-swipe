package nl.fontys.smpt42_1.fontysswipe.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import nl.fontys.smpt42_1.fontysswipe.domain.Route;
import nl.fontys.smpt42_1.fontysswipe.domain.Teacher;

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
     * @param teachers   all docenten.
     * @return een gesorteerde map van docenten met het aantal procenten dat matcht.
     */
    public TreeMap<Teacher, Double> compareTeachers(ArrayList<Route> userPoints, List<Teacher> teachers) {
        HashMap<String, Integer> differenceMap = new HashMap<>();
        HashMap<Teacher, Double> resultMap = new HashMap<>();

        for (Teacher teacher : teachers) {
            double result = 0;

            HashMap<String, Integer> teachersMap = teacher.getPoints();

            for (final Map.Entry<String, Integer> teacherEntry : teachersMap.entrySet()) {

                Route route = FindRouteKt.findRoute(teacherEntry.getKey(), userPoints);

                int difference = Math.abs(route.getUserPoints() - teacherEntry.getValue());
                differenceMap.put(teacherEntry.getKey(), difference);
            }

            for (Route route : userPoints) {
                result = result + (differenceMap.get(route.getName()) * (xroute.getUserPoints() * 0.1));
            }

            resultMap.put(teacher, (10 - result) * 10);
        }

        return (TreeMap<Teacher, Double>) entriesSortedByValues(resultMap);
    }

    private static <K, V extends Comparable<? super V>> SortedSet<Map.Entry<K, V>> entriesSortedByValues(Map<K, V> map) {
        SortedSet<Map.Entry<K, V>> sortedEntries = new TreeSet<>(
                new Comparator<Map.Entry<K, V>>() {
                    @Override
                    public int compare(Map.Entry<K, V> e1, Map.Entry<K, V> e2) {
                        int res = e1.getValue().compareTo(e2.getValue());
                        return res != 0 ? res : 1;
                    }
                }
        );
        sortedEntries.addAll(map.entrySet());
        return sortedEntries;
    }

}
