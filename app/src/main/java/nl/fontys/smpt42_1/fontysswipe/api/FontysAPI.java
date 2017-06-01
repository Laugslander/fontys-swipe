package nl.fontys.smpt42_1.fontysswipe.api;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import nl.fontys.smpt42_1.fontysswipe.domain.Teacher;

/**
 * Created by ken on 6/1/2017.
 */

public class FontysAPI {

    private String accessToken;

    public FontysAPI() {
    }

    public Teacher getTeacherDataByID(String userid) {
        Teacher teacher = null;
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        TeacherDataCallable cal = new TeacherDataCallable(userid);
        if (accessToken != null && !accessToken.isEmpty()) {
            cal.setAccessToken(accessToken);
        }
        Future<Teacher> future = executorService.submit(cal);

        try {
            teacher = future.get();
            this.accessToken = cal.getAccessToken();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return teacher;
    }
}
