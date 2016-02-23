package prakhar_squared_mayank.moodler;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

//    public void register() {
//        String url="http://agni.iitd.ernet.in/cop290/assign0/register/";
//
//        StringRequest req=new StringRequest(Request.Method.POST,url,new Response.Listener<String>(){
//            @Override
//            public void onResponse(String response) {
//
//                try {
//                    progressDialog.dismiss();
//                    JSONObject res = new JSONObject(response);      //parsing into JSON response format
//                    String success = res.getString(res_code);
//                    String msg = res.getString(res_msg);
//                    displayMessage(success, msg);
//
//                } catch (Exception e) {
//                    sound_player = MediaPlayer.create(MainActivity.this, R.raw.check_data_fail);	//instantiating sound_player object with the sound associated with failed registration
//                    sound_player.setLooping(false);
//                    sound_player.start();
//                    Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
//                    v.vibrate(150);
//                    e.printStackTrace();
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                sound_player = MediaPlayer.create(MainActivity.this, R.raw.check_data_fail);	//instantiating sound_player object with the sound associated with failed registration
//                sound_player.setLooping(false);
//                sound_player.start();
//                Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
//                v.vibrate(150);
//
//                progressDialog.dismiss();
//                error.printStackTrace();
//                if(error instanceof TimeoutError) {
//                    showToast("The connection timed out.");
//                } else if(error instanceof NoConnectionError) {
//                    showToast("No internet connection available.");
//                } else if(error instanceof NetworkError) {
//                    showToast("A network error occurred.");
//                } else if(error instanceof ServerError) {
//                    showToast("A server error occurred.");
//                } else {
//                    showToast("An unidentified error occurred.");
//                }
//            }
//        }){
//            @Override
//            protected Map<String,String> getParams() {
//                Map<String, String> params = new HashMap<String,String>();
//                params.put("teamname", teams);
//                params.put("name1", name1s);
//                params.put("name2", name2s);
//                params.put("name3", name3s);
//                params.put("entry1", entry1s);
//                params.put("entry2", entry2s);
//                params.put("entry3", entry3s);
//                return params;
//            }
//            @Override
//            public String getBodyContentType(){
//                return "application/x-www-form-urlencoded;";
//            }
//        };
//        volley_singleton.getInstance(context).getRequestQueue().add(req);
//    }
}
