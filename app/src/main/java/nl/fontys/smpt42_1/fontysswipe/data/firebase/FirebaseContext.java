package nl.fontys.smpt42_1.fontysswipe.data.firebase;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

/**
 * @author SMPT42-1
 */
abstract class FirebaseContext {

    private final String QUESTIONS_REFERENCE = "questions";
    private final String ROUTES_REFERENCE = "routes";
    private final String TEACHERS_REFERENCE = "teachers";
    private final String ACTIVITIES_REFERENCE = "activities";
    private final String PRIZE_REFERENCE = "prize";

    private final FirebaseDatabase database;
    private final FirebaseStorage storage;

    FirebaseContext() {
        database = FirebaseDatabase.getInstance();
        storage = FirebaseStorage.getInstance();
    }

    DatabaseReference getQuestionsDatabaseReference() {
        return database.getReference(QUESTIONS_REFERENCE);
    }

    DatabaseReference getRoutesDatabaseReference() {
        return database.getReference(ROUTES_REFERENCE);
    }

    DatabaseReference getTeachersDatabaseReference() {
        return database.getReference(TEACHERS_REFERENCE);
    }

    DatabaseReference getActivitiesDatabaseReference() {
        return database.getReference(ACTIVITIES_REFERENCE);
    }

    DatabaseReference getPrizeDatabaseReference() {
        return database.getReference(PRIZE_REFERENCE);
    }

    StorageReference getQuestionsStorageReference() {
        return storage.getReference(QUESTIONS_REFERENCE);
    }

    StorageReference getPrizeStorageReference() {
        return storage.getReference(PRIZE_REFERENCE);
    }

}
