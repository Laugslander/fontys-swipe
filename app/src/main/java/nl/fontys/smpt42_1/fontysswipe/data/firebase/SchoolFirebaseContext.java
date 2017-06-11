package nl.fontys.smpt42_1.fontysswipe.data.firebase;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import nl.fontys.smpt42_1.fontysswipe.data.contract.SchoolContract;
import nl.fontys.smpt42_1.fontysswipe.data.contract.callback.OnSchoolsReceivedCallback;
import nl.fontys.smpt42_1.fontysswipe.domain.School;

/**
 * @author SMPT42-1
 */
public final class SchoolFirebaseContext extends FirebaseContext implements SchoolContract {

    private static final Logger logger = Logger.getLogger(SchoolFirebaseContext.class.getSimpleName());

    @Override
    public void getSchools(final OnSchoolsReceivedCallback callback) {
        getSchoolsDatabaseReference().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<School> schools = new ArrayList<>();

                for (DataSnapshot questionData : dataSnapshot.getChildren()) {
                    School school = questionData.getValue(School.class);
                    schools.add(school);
                }

                callback.onSchoolsReceived(schools);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                logger.log(Level.SEVERE, databaseError.getMessage());
            }
        });
    }

}
