package ke.co.interintel.nikobizzapi;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Hashtable;

import ke.co.interintel.nikobizzapi.encryption.ApiSecurity;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by Brian on 1/2/2017.
 */

public class Nikobizz {

    private static final String TAG = Nikobizz.class.getSimpleName();

    private final String HOST = "69.64.68.17";
    private final String URL = "nikobizz.com";

    private String API_KEY;
    private String USERNAME;
    private String PASSWORD;

    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    public Nikobizz(String apiKey, String username, String password) {

        this.API_KEY = apiKey;
        this.USERNAME = username;
        this.PASSWORD = password;

    }

    public void sendMPESA(String msisdn, String sendAmount, String paymentMethod, String transactionID, String scheduleSend,Callback callback) {

        if (API_KEY == null || API_KEY.isEmpty()){
            throw new NullPointerException("API KEY cannot be null");
        }


        try {

            JSONObject payload = initPayload();

            JSONObject credentials = new JSONObject();
            credentials.put("username", USERNAME);
            credentials.put("password", PASSWORD);

            payload.put("credentials", credentials);

            //payload.put("testMap", credentials);
            //payload.put("testArray", new JSONArray("[1,2,3]"));

            payload.put("MSISDN", msisdn);
            payload.put("amount", sendAmount);
            payload.put("currency", "KES");
            payload.put("institution_id", "56");//Abana Institution ID
            payload.put("product_item_id", "3");//M-PESA Product ID
            payload.put("payment_method", paymentMethod);//Payment M-PESA|MIPAY
            payload.put("ext_outbound_id", transactionID);
            payload.put("scheduled_send", scheduleSend); // d/m/Y H:M (am/pm)


            Log.i(TAG, "POST PARAMS :" + payload.toString());
            String packagedPayload = ApiSecurity.packagePayload(API_KEY, payload).toString();

            this.serviceRequest(packagedPayload, "REMIT", callback);


        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    private void serviceRequest(String payload, String service, Callback callback) {
        OkHttpClient client = new OkHttpClient();

        RequestBody body;
        body = RequestBody.create(JSON, payload);

        String url = "https://" + URL + "/api/" + service + "/";
        Log.i(TAG, "POST url: " + url);

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        client.newCall(request).enqueue(callback);
    }

    private JSONObject initPayload() {
        Hashtable<String, String> toReturn = new Hashtable<>();

        toReturn.put("CHID", "1");
        toReturn.put("timestamp", String.valueOf(System.currentTimeMillis()));
        toReturn.put("ip_address", HOST);
        toReturn.put("gateway_host", URL);
        toReturn.put("lat", "0.0");
        toReturn.put("lng", "0.0");

        return new JSONObject(toReturn);
    }

    public  void sendMPESA(String msisdn, String sendAmount, String paymentMethod,Callback callback) {
        sendMPESA(msisdn, sendAmount, paymentMethod, "", "",callback);
    }

}
