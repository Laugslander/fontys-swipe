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

import nl.fontys.smpt42_1.fontysswipe.data.contract.TeacherContract;
import nl.fontys.smpt42_1.fontysswipe.data.contract.callback.OnTeacherImageLinkReceivedCallback;
import nl.fontys.smpt42_1.fontysswipe.data.contract.callback.OnTeachersReceivedCallback;
import nl.fontys.smpt42_1.fontysswipe.domain.Teacher;

/**
 * @author Robin Laugs
 */

public class TeacherFirebaseContext extends FirebaseContext implements TeacherContract {

    private static final Logger logger = Logger.getLogger(TeacherFirebaseContext.class.getSimpleName());

    @Override
    public void getTeachers(final OnTeachersReceivedCallback callback) {
        getTeachersDatabaseReference().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Teacher> teachers = new ArrayList<>();

                for (DataSnapshot teacherData : dataSnapshot.getChildren()) {
                    Teacher teacher = teacherData.getValue(Teacher.class);
                    teachers.add(teacher);
                }

                callback.onTeachersReceived(teachers);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                logger.log(Level.SEVERE, databaseError.getMessage());
            }
        });
    }

    @Override
    public void getTeacherImageUri(String image, final OnTeacherImageLinkReceivedCallback callback) {
        getTeachersStorageReference().child(image).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                callback.onTeacherImageUriReceived(uri);
            }
        });
    }

}
