package nl.fontys.smpt42_1.fontysswipe.controller;

import android.content.Intent;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import nl.fontys.smpt42_1.fontysswipe.domain.Route;
import nl.fontys.smpt42_1.fontysswipe.domain.Teacher;

/**
 * Created by Merik on 02/06/2017.
 */

public class CompareController {

    private static CompareController instance;

    private CompareController() {

    }

    public static CompareController createInstance() {
        instance = new CompareController();
        return instance;
    }

    /**
     * Compare teacher methode compared alle docenten met 1 student en kijkt voor alle docenten welke docenten de beste match is.
     * @param userPoints een hashmap met de studie profielen en het aantal punten dat de gebruiker daarbij heeft.
     * @param teachers all docenten.
     * @return een gesorteerde map van docenten met het aantal procenten dat matcht.
     */
    public TreeMap<Teacher, Double> compareTeachers(HashMap<String, Integer> userPoints, ArrayList<Teacher> teachers) {
        HashMap<String, Integer> differenceMap = new HashMap<>();
        HashMap<Teacher, Double> resultMap = new HashMap<>();

        for (Teacher teacher : teachers) {
            double result = 0;

            HashMap<String, Integer> teachersMap = teacher.getRoutePoints();

            for (Map.Entry<String, Integer> teacherEntry : teachersMap.entrySet()) {

                int difference = Math.abs(userPoints.get(teacherEntry.getKey()) - teacherEntry.getValue());
                differenceMap.put(teacherEntry.getKey(), difference);
            }

            for (Map.Entry<String, Integer> student : userPoints.entrySet()) {
                result = result + (differenceMap.get(student.getKey()) * (student.getValue() * 0.1));
            }

            resultMap.put(teacher, (10 - result) * 10);
        }

        return (TreeMap<Teacher, Double>) entriesSortedByValues(resultMap);
    }

    private static <K, V extends Comparable<? super V>> SortedSet<Map.Entry<K, V>> entriesSortedByValues(Map<K, V> map) {
        SortedSet<Map.Entry<K, V>> sortedEntries = new TreeSet<Map.Entry<K, V>>(
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
