package nl.fontys.smpt42_1.fontysswipe.data.firebase;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

/**
 * @author SMPT42-1
 */
abstract class FirebaseContext {

    private static final String QUESTIONS_REFERENCE = "questions";
    private static final String ROUTES_REFERENCE = "routes";
    private static final String TEACHERS_REFERENCE = "teachers";
    private static final String ACTIVITIES_REFERENCE = "activities";
    private static final String SCHOOLS_REFERENCE = "schools";
    private static final String PRIZE_REFERENCE = "prize";

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

    DatabaseReference getSchoolsDatabaseReference() {
        return database.getReference(SCHOOLS_REFERENCE);
    }

    DatabaseReference getPrizeDatabaseReference() {
        return database.getReference(PRIZE_REFERENCE);
    }

    StorageReference getQuestionsStorageReference() {
        return storage.getReference(QUESTIONS_REFERENCE);
    }

    StorageReference getTeachersStorageReference() {
        return storage.getReference(TEACHERS_REFERENCE);
    }

    StorageReference getPrizeStorageReference() {
        return storage.getReference(PRIZE_REFERENCE);
    }

}
