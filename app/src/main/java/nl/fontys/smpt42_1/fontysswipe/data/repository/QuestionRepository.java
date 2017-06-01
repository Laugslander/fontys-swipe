package nl.fontys.smpt42_1.fontysswipe.data.repository;

import nl.fontys.smpt42_1.fontysswipe.data.DatabaseConstants;
import nl.fontys.smpt42_1.fontysswipe.data.contract.QuestionContract;
import nl.fontys.smpt42_1.fontysswipe.data.contract.callback.OnQuestionImageLinkReceivedCallback;
import nl.fontys.smpt42_1.fontysswipe.data.contract.callback.OnQuestionsReceivedCallback;

/**
 * @author SMPT42-1
 */
public final class QuestionRepository {

    private final QuestionContract context;

    public QuestionRepository() {
        this.context = DatabaseConstants.QUESTION_CONTEXT;
    }

    public void getQuestions(OnQuestionsReceivedCallback callback) {
        context.getQuestions(callback);
    }

    public void getQuestionImageUri(String image, OnQuestionImageLinkReceivedCallback callback) {
        context.getQuestionImageUri(image, callback);
    }

}
