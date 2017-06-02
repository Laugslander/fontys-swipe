package nl.fontys.smpt42_1.fontysswipe.api;

import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Callable;

import javax.net.ssl.HttpsURLConnection;

import nl.fontys.smpt42_1.fontysswipe.domain.Teacher;

import static android.content.ContentValues.TAG;

/**
 * @author SMPT42-1
 */
class TeacherDataCallable implements Callable<Teacher> {

    private String userID;
    private String accessToken;

    TeacherDataCallable(String userID) {
        this.userID = userID;
    }

    @Override
    public Teacher call() throws Exception {
        if (this.accessToken == null || this.accessToken.isEmpty()) {
            this.retrieveAccessToken();
        }
        Teacher teacher = new Teacher();
        try {
            try {
                URL url = new URL("https://api.fhict.nl/people/" + this.userID);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Accept", "application/json");
                conn.setRequestProperty("Authorization", "Bearer " + this.accessToken);

                if (conn.getResponseCode() != 200) {
                    throw new RuntimeException("Failed : HTTP error code : "
                            + conn.getResponseCode());
                }

                BufferedReader br = new BufferedReader(new InputStreamReader(
                        (conn.getInputStream())));
                String line;
                StringBuilder sb = new StringBuilder();
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
                String json = sb.toString();
                teacher = new Gson().fromJson(json, Teacher.class);
                conn.disconnect();

            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return teacher;
    }

    private void retrieveAccessToken() {
        try {
            String url = "https://identity.fhict.nl/connect/token";
            URL obj = new URL(url);
            HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

            con.setRequestMethod("POST");
            String urlParameters = "grant_type=client_credentials&scope=fhict&client_id=i342347-fontysswip&client_secret=Ufy1g(NILn7otzstV8AsTPMA55zqzUp7SaLG2V3r";

            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.flush();
            wr.close();

            int responseCode = con.getResponseCode();
            System.out.println("\nSending 'POST' request to URL : " + url);
            System.out.println("Post parameters : " + urlParameters);
            System.out.println("Response Code : " + responseCode);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            String jsonAccesTokenString = response.toString();
            JSONObject jObj = new JSONObject(jsonAccesTokenString);
            this.accessToken = jObj.getString("access_token");
        } catch (Exception e) {
            Log.d(TAG, "retrieveAccessToken() called");
        }
    }

    void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    String getAccessToken() {
        return accessToken;
    }

}
