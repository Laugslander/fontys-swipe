package nl.fontys.smpt42_1.fontysswipe.data.contract;

import nl.fontys.smpt42_1.fontysswipe.data.contract.callback.OnQuestionImageLinkReceivedCallback;
import nl.fontys.smpt42_1.fontysswipe.data.contract.callback.OnQuestionsReceivedCallback;

/**
 * @author SMPT42-1
 */
public interface QuestionContract {

    void getQuestions(OnQuestionsReceivedCallback callback);

    void getQuestionImageUri(String image, OnQuestionImageLinkReceivedCallback callback);

}
