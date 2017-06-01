package nl.fontys.smpt42_1.fontysswipe.data.firebase;

import android.net.Uri;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import nl.fontys.smpt42_1.fontysswipe.data.contract.QuestionContract;
import nl.fontys.smpt42_1.fontysswipe.data.contract.callback.OnQuestionImageLinkReceivedCallback;
import nl.fontys.smpt42_1.fontysswipe.data.contract.callback.OnQuestionsReceivedCallback;
import nl.fontys.smpt42_1.fontysswipe.domain.Question;

/**
 * @author SMPT42-1
 */
public final class QuestionFirebaseContext extends FirebaseContext implements QuestionContract {

    private static final Logger logger = Logger.getLogger(QuestionFirebaseContext.class.getSimpleName());

    @Override
    public void getQuestions(final OnQuestionsReceivedCallback callback) {
        getQuestionsDatabaseReference().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Question> questions = new ArrayList<>();

                for (DataSnapshot questionData : dataSnapshot.getChildren()) {
                    Question question = questionData.getValue(Question.class);
                    questions.add(question);
                }

                callback.onQuestionsReceived(questions);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                logger.log(Level.SEVERE, databaseError.getMessage());
            }
        });
    }

    @Override
    public void getQuestionImageUri(String image, final OnQuestionImageLinkReceivedCallback callback) {
        getQuestionsStorageReference().child(image).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                callback.onQuestionImageUriReceived(uri);
            }
        });
    }

}
