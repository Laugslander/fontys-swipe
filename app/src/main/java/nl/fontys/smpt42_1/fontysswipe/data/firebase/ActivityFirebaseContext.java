package nl.fontys.smpt42_1.fontysswipe.data.firebase;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import nl.fontys.smpt42_1.fontysswipe.data.contract.ActivityContract;
import nl.fontys.smpt42_1.fontysswipe.data.contract.callback.OnActivitiesReceivedCallback;
import nl.fontys.smpt42_1.fontysswipe.domain.Activity;

/**
 * @author SMPT42-1
 */
public final class ActivityFirebaseContext extends FirebaseContext implements ActivityContract {

    private static final Logger logger = Logger.getLogger(ActivityFirebaseContext.class.getSimpleName());

    @Override
    public void getActivities(final OnActivitiesReceivedCallback callback) {
        getActivitiesDatabaseReference().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Activity> activities = new ArrayList<>();

                for (DataSnapshot activityData : dataSnapshot.getChildren()) {
                    Activity activity = activityData.getValue(Activity.class);
                    activities.add(activity);
                }

                callback.onActivitiesReceived(activities);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                logger.log(Level.SEVERE, databaseError.getMessage());
            }
        });
    }

}
