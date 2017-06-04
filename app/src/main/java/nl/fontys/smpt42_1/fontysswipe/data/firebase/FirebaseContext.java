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

    StorageReference getQuestionsStorageReference() {
        return storage.getReference(QUESTIONS_REFERENCE);
    }

}
