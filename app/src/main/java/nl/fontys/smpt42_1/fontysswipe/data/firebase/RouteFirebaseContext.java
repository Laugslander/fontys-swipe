package nl.fontys.smpt42_1.fontysswipe.data.firebase;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import nl.fontys.smpt42_1.fontysswipe.data.contract.RouteContract;
import nl.fontys.smpt42_1.fontysswipe.data.contract.callback.OnRoutesReceivedCallback;
import nl.fontys.smpt42_1.fontysswipe.domain.Route;

/**
 * @author SMPT42-1
 */
public class RouteFirebaseContext extends FirebaseContext implements RouteContract {

    private static final Logger logger = Logger.getLogger(RouteFirebaseContext.class.getSimpleName());

    @Override
    public void getRoutes(final OnRoutesReceivedCallback callback) {
        getRoutesDatabaseReference().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Route> routes = new ArrayList<>();

                for (DataSnapshot routeData : dataSnapshot.getChildren()) {
                    Route route = routeData.getValue(Route.class);
                    routes.add(route);
                }

                callback.onRoutesReceived(routes);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                logger.log(Level.SEVERE, databaseError.getMessage());
            }
        });
    }

}
