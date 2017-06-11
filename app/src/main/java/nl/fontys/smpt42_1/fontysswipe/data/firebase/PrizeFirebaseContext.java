package nl.fontys.smpt42_1.fontysswipe.data.firebase;

import android.net.Uri;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.logging.Level;
import java.util.logging.Logger;

import nl.fontys.smpt42_1.fontysswipe.data.contract.PrizeContract;
import nl.fontys.smpt42_1.fontysswipe.data.contract.callback.OnPrizeImageLinkReceivedCallback;
import nl.fontys.smpt42_1.fontysswipe.data.contract.callback.OnPrizeReceivedCallback;
import nl.fontys.smpt42_1.fontysswipe.domain.Prize;

/**
 * @author SMPT42-1
 */
public class PrizeFirebaseContext extends FirebaseContext implements PrizeContract {

    private static final Logger logger = Logger.getLogger(PrizeFirebaseContext.class.getSimpleName());

    @Override
    public void getPrize(final OnPrizeReceivedCallback callback) {
        getPrizeDatabaseReference().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Prize prize = dataSnapshot.getValue(Prize.class);
                callback.onPrizeReceived(prize);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                logger.log(Level.SEVERE, databaseError.getMessage());
            }
        });
    }

    @Override
    public void getPrizeImageUri(String image, final OnPrizeImageLinkReceivedCallback callback) {
        getPrizeStorageReference().child(image).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                callback.onPrizeImageUriReceived(uri);
            }
        });
    }

}
