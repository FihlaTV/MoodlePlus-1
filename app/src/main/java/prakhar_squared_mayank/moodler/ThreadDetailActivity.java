package prakhar_squared_mayank.moodler;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import prakhar_squared_mayank.moodler.Adapters.*;
import prakhar_squared_mayank.moodler.Adapters.CourseThreadsAdapter;

public class ThreadDetailActivity extends AppCompatActivity implements View.OnClickListener {
    String threadID = "";
    private String ip = MainActivity.ip;
    JSONObject responseObject = null;
    ThreadDetailAdapter listAdapter;
    ListView threadLV;
    FloatingActionButton newCommentFAB;
    ProgressDialog progressDialog;
    TextView title, desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_detail);

        threadID = getIntent().getStringExtra("ThreadDetailID");

        threadLV = (ListView) findViewById(R.id.list_td);
        listAdapter = new ThreadDetailAdapter(this, responseObject);
        threadLV.setAdapter(listAdapter);
        newCommentFAB = (FloatingActionButton)findViewById(R.id.fabtd);
        newCommentFAB.setOnClickListener(this);

        title = (TextView) findViewById(R.id.title_td);
        title.setText(getIntent().getStringExtra("ThreadDetailTitle"));
        desc = (TextView) findViewById(R.id.description_td);
        desc.setText(getIntent().getStringExtra("ThreadDetailDesc"));
        getComments();

    }

    public void getComments() {
        String loginUrl="http://"+ip+"/threads/thread.json/"+threadID;
        System.out.println("URL HIT WAS FOR THREAD COMMENTS:" + loginUrl);
        StringRequest req=new StringRequest(Request.Method.GET, loginUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    responseObject=(new JSONObject(response));
                    listAdapter.updateData(responseObject);

                }catch (Exception e){
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        volley_singleton.getInstance(this).getRequestQueue().add(req);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId())
        {
            case R.id.fabtd:
                showNewCommentOptions();
                break;
            default:
        }
    }

    public void showNewCommentOptions() {
        Log.d("Course Thread", "New Thread");
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add Comment");

        final LinearLayout ll = new LinearLayout(this);
        ll.setOrientation(LinearLayout.VERTICAL);
        ll.setPadding(15, 5, 15, 5);
        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        input.setHint("Enter your comment");


        ll.addView(input);
        builder.setView(ll);

// Set up the buttons
        builder.setPositiveButton("ADD", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String desc="", descEncoded="";
                try {
                    desc = input.getText().toString();
                    descEncoded = URLEncoder.encode(desc, "utf-8");
                } catch (UnsupportedEncodingException e) {
                    Toast.makeText(getApplicationContext(), "Unsupported input", Toast.LENGTH_SHORT).show();
                    return;
                }
                showProgressDialog();
                String loginUrl = "http://" + ip + "/threads/post_comment.json?thread_id="+threadID+"&description="+descEncoded;
                System.out.println("URL HIT WAS NEW COMMENT:" + loginUrl);
                StringRequest req = new StringRequest(Request.Method.GET, loginUrl, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String Response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject res = new JSONObject(Response);

                            String success = res.getString("success");
                            if (success.equals("false")) {
                                Toast.makeText(getApplicationContext(), "Failed to create thread.", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getApplicationContext(), "Comment Added.", Toast.LENGTH_SHORT).show();
                                getComments();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), "Server Error.", Toast.LENGTH_SHORT).show();
                    }
                });
                volley_singleton.getInstance(getApplicationContext()).getRequestQueue().add(req);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    public void showProgressDialog() {
        progressDialog = new ProgressDialog(this, R.style.Base_Theme_AppCompat_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setTitle("Adding Comment...");
        progressDialog.show();
    }
}
