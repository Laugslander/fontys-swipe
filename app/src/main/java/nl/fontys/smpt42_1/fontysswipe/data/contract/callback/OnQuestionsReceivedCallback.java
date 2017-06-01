package nl.fontys.smpt42_1.fontysswipe.data.contract.callback;

import java.util.List;

import nl.fontys.smpt42_1.fontysswipe.domain.Question;

/**
 * @author SMPT42-1
 */
public interface OnQuestionsReceivedCallback {

    void onQuestionsReceived(List<Question> questions);

}
