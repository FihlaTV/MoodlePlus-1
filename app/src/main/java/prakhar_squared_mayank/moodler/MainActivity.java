package prakhar_squared_mayank.moodler;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

    Button loginButton;
    EditText username,password;
    String ip="127.0.0.1";
    String port="2000";

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
        switch (view.getId()){
            case R.id.loginB:
                loginProc();
                goToDashBoard();
                break;
            default:
        }
    }

    private void goToDashBoard() {
        Intent intent = new Intent(getApplicationContext(), Dashboard.class);
        startActivity(intent);
    }

    public void loginProc(){
        String usernameString=username.getText().toString().trim();
        String passwordString=password.getText().toString().trim();
//    String loginUrl="http://www.google.com";
        String loginUrl="http://10.0.2.2:2000/default/login.json?userid="+usernameString+"&password="+passwordString;
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
        RequestQueue a=Volley.newRequestQueue(getApplicationContext(), 4000);
        a.add(req);

    }

    //Shows toast with appropriate responses
    public void showToast(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
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
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
