package ke.co.interintel.intrintelencryption;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Hashtable;

import ke.co.interintel.nikobizzapi.Nikobizz;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    private String USERNAME = "";
    private String PASSWORD = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String API_KEY = "2013";
        String Username = "";
        String Password = "";

        Nikobizz nikobizz = new Nikobizz(API_KEY,Username,Password);

        nikobizz.sendMPESA("+254717103598", "10", "M-PESA", "", "", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //on network failure
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseString = response.body().string();
                if (!response.isSuccessful()) {
                    throw new IOException("Unexpected response code " + response);
                }

                Log.i(TAG, responseString);
            }
        });

    }

}
