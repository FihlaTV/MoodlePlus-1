package prakhar_squared_mayank.moodler;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
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
    Button loginButton, registerButton;
    EditText username,password;
    static String ip="192.168.43.48:8000";
    private TextView resultText;
    LinearLayout mainLL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CookieManager manager = new CookieManager( null, CookiePolicy.ACCEPT_ALL );
        CookieHandler.setDefault(manager);

        //Onclick listener for login button
        loginButton=(Button) findViewById(R.id.loginB);
        loginButton.setOnClickListener(this);

        registerButton = (Button)findViewById(R.id.register_main);
        registerButton.setOnClickListener(this);

        mainLL = (LinearLayout) findViewById(R.id.main_linear);
        mainLL.setOnClickListener(this);

        //Username and password edit texts
        username=(EditText) findViewById(R.id.username);
        password=(EditText) findViewById(R.id.password);
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.loginB:
                hideKeyboard();
                loginProc();
                break;
            case R.id.register_main:
                hideKeyboard();
                Intent it = new Intent(this, RegisterActivity.class);
                startActivity(it);
                break;
            case R.id.main_linear:
                hideKeyboard();
                break;
            default:
        }
    }

    public void hideKeyboard()
    {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
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

        String loginUrl="http://"+ip+"/default/login.json?userid="+usernameString+"&password="+passwordString;
        System.out.println("URL HIT WAS:"+loginUrl);
        StringRequest req=new StringRequest(Request.Method.GET, loginUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String Response) {
                try {
                    JSONObject res=new JSONObject(Response);

                    String success=res.getString("success");
                    if(success.equals("false"))
                    {
                        Toast.makeText(getApplicationContext(), "Login Failed", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Welcome "+res.getJSONObject("user").getString("first_name")+"!", Toast.LENGTH_SHORT).show();
                        Intent it = new Intent(getApplicationContext(), Dashboard.class);
                        it.putExtra("Extra.firstname", res.getJSONObject("user").getString("first_name"));
                        it.putExtra("Extra.lastname", res.getJSONObject("user").getString("last_name"));
                        it.putExtra("Extra.entry", res.getJSONObject("user").getString("entry_no"));
                        it.putExtra("Extra.email", res.getJSONObject("user").getString("email"));
                        it.putExtra("Extra.username", res.getJSONObject("user").getString("username"));
                        startActivity(it);
                    }
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

                        ip=(String) editText.getText().toString().trim();
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
