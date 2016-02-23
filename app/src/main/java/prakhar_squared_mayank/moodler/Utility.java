package prakhar_squared_mayank.moodler;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;

/**
 * Created by mayank on 24/02/16.
 */
public class Utility {

    public static void logoutUser(final Context ctx) {
        String loginUrl="http://192.168.43.48:8000/default/logout.json";
        System.out.println("URL HIT WAS:"+loginUrl);
        StringRequest req=new StringRequest(Request.Method.GET, loginUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String Response) {

                    Toast.makeText(ctx, "Logged Out, Bye!", Toast.LENGTH_SHORT).show();
                    Intent it = new Intent(ctx, MainActivity.class);
                    ctx.startActivity(it);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ctx, "Failed to logout.", Toast.LENGTH_SHORT).show();
            }
        });
        volley_singleton.getInstance(ctx).getRequestQueue().add(req);
    }
}
