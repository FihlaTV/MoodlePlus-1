package prakhar_squared_mayank.moodler;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.Map;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    final Context context = this;
    Button loginButton;
    EditText username,password;
    static String ip="10.0.2.2:8000";
    private TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CookieManager manager = new CookieManager( null, CookiePolicy.ACCEPT_ALL );
        CookieHandler.setDefault(manager);

        //Onclick listener for login button
        loginButton=(Button) findViewById(R.id.loginB);
        loginButton.setOnClickListener(this);

        //Uername and password edit texts
        username=(EditText) findViewById(R.id.username);
        password=(EditText) findViewById(R.id.password);
    }

    public void onClick(View view){
//        loginProc();
        switch (view.getId()){
            case R.id.loginB:
                goToCoursePage();
                break;
            default:
        }
    }

    private void goToDashBoard() {
        Intent intent = new Intent(getApplicationContext(), Dashboard.class);
        startActivity(intent);
    }
    private void goToCoursePage() {
        Intent intent = new Intent(getApplicationContext(), CoursePage.class);
        startActivity(intent);
    }

    public void loginProc(){
        String usernameString=username.getText().toString().trim();
        String passwordString=password.getText().toString().trim();
//    String loginUrl="http://www.google.com";
        String loginUrl="http://"+ip+"/default/login.json?userid="+usernameString+"&password="+passwordString;
        System.out.println("URL HIT WAS:"+loginUrl);
        StringRequest req=new StringRequest(Request.Method.GET, loginUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String Response) {
                try {
//                    JSONObject res=new JSONObject(Response);

//                    String success=res.getString("success");
                    System.out.println("YOU ARE HERE EUREKA!");
                    showToast("success");
                    Intent it = new Intent(getApplicationContext(), Dashboard.class);
                    startActivity(it);
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
        volley_singleton.getInstance(context).getRequestQueue().add(req);
    }

    //Shows toast with appropriate responses
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void changeIp(){
        LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
        View promptView = layoutInflater.inflate(R.layout.input_dialog, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
        alertDialogBuilder.setView(promptView);

        final EditText editText = (EditText) promptView.findViewById(R.id.edittext);
        // setup a dialog window
        alertDialogBuilder.setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        resultText.setText("Hello, " + editText.getText());
                    }
                })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        // create an alert dialog
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch(item.getItemId()){
            case R.id.action_settings:
                break;
            case R.id.action_change_ip:
                changeIp();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        //noinspection SimplifiableIfStatement
        return super.onOptionsItemSelected(item);


    }
}
