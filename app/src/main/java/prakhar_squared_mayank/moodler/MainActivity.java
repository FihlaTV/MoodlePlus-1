package prakhar_squared_mayank.moodler;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.Map;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    Button loginButton;
    EditText username,password;
    String ip="127.0.0.1";
    String port="2000";
    String loginUrl="";
    String urlhit="http://10.0.2.2:2000";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Onclick listener for login button
        loginButton=(Button) findViewById(R.id.loginB);
        loginButton.setOnClickListener(this);

        //Uername and password edit texts
        username=(EditText) findViewById(R.id.username);
        password=(EditText) findViewById(R.id.password);
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.loginB:
                loginProc();
                break;
            default:
        }
    }


    public void loginProc(){
        String usernameString=username.getText().toString().trim();
        String passwordString=password.getText().toString().trim();
//    String loginUrl="http://www.google.com";
        loginUrl=urlhit+"/default/login.json?userid="+usernameString+"&password="+passwordString;
        System.out.println("URL HIT WAS:"+loginUrl);
        StringRequest req=new StringRequest(Request.Method.GET, loginUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String Response) {
                try {
//                    JSONObject res=new JSONObject(Response);

//                    String success=res.getString("success");
                    System.out.println("YOU ARE HERE EUREKA!");
                    showToast("success");
                }catch (Exception e){
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                showToast("failed");
            }
        });
        RequestQueue a=Volley.newRequestQueue(getApplicationContext());
        a.add(req);

    }

    //Shows toast with appropriate responses
    public void showToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch(item.getItemId()){
            case R.id.action_change_ip:
                AlertDialog.Builder alertDialog=new AlertDialog.Builder(MainActivity.this);
                alertDialog.setMessage("Enter IP");
                final EditText input = new EditText(MainActivity.this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input.setLayoutParams(lp);
                alertDialog.setView(input);

                alertDialog.setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                urlhit=input.getText().toString().trim();
                                showToast("IP CHANGED");
                            }
                        });

                alertDialog.setNegativeButton("NO",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                showToast("kuch toh hua hai!");
                alertDialog.show();

                break;
            case R.id.action_settings:
                break;
            default:
                return super.onOptionsItemSelected(item);

        }
        return super.onOptionsItemSelected(item);


        //noinspection SimplifiableIfStatement



    }
}
