package nl.fontys.smpt42_1.fontysswipe;

import java.util.Arrays;
import java.util.List;

import nl.fontys.smpt42_1.fontysswipe.domain.Question;

/**
 * TODO class needs to be removed after implementing retrieval of questions from database
 *
 * @author SMPT42-1
 */
class TestData {

    static final List<Question> questions = Arrays.asList(
            new Question("Vind je design belangrijk?"),
            new Question("Ben je geïnteresseerd in robotica?"),
            new Question("Houdt je van het schrijven van verslagen?"),
            new Question("Vind je het leuk om anderen iets te leren?"),
            new Question("Lijkt het je ook om een app zoals deze te leren te ontwikkelen?")
    );

}
