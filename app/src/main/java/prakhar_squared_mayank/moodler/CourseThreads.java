package prakhar_squared_mayank.moodler;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import prakhar_squared_mayank.moodler.Adapters.CourseThreadsAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CourseThreads.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CourseThreads#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CourseThreads extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    JSONArray responseArray = null;
    CourseThreadsAdapter listAdapter;
    ListView courseThreadLV;
    FloatingActionButton newThreadFAB;
    ProgressDialog progressDialog;

    private String ip = MainActivity.ip;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CourseThreads.
     */
    // TODO: Rename and change types and number of parameters
    public static CourseThreads newInstance(String param1, String param2) {
        CourseThreads fragment = new CourseThreads();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public CourseThreads() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_course_threads, container, false);
        courseThreadLV = (ListView) v.findViewById(R.id.course_thread_lv);
        listAdapter = new CourseThreadsAdapter(getActivity(), responseArray);
        courseThreadLV.setAdapter(listAdapter);
        courseThreadLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //todo:b shift to the specific thread from here
            }
        });
        newThreadFAB = (FloatingActionButton)v.findViewById(R.id.fabct);
        newThreadFAB.setOnClickListener(this);
        getThreads();

        return v;
    }

    public void getThreads() {
        String loginUrl="http://"+ip+"/courses/course.json/"+((CoursePage)getActivity()).getCourseCode()+"/threads";
        System.out.println("URL HIT WAS:" + loginUrl);
        StringRequest req=new StringRequest(Request.Method.GET, loginUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    responseArray=(new JSONObject(response)).getJSONArray("course_threads");
                    listAdapter.updateData(responseArray);

                }catch (Exception e){
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        volley_singleton.getInstance(getActivity()).getRequestQueue().add(req);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View view) {
        switch(view.getId())
        {
            case R.id.fabct:
                showNewThreadOptions();
                break;
        }
    }

    public void showNewThreadOptions() {
        Log.d("Course Thread", "New Thread");
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Add Thread");

        final LinearLayout ll = new LinearLayout(getActivity());
        ll.setOrientation(LinearLayout.VERTICAL);
        ll.setPadding(15, 5, 15, 5);
        final EditText input = new EditText(getActivity());
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        input.setHint("Thread title");
        final EditText input2 = new EditText(getActivity());
        input2.setInputType(InputType.TYPE_CLASS_TEXT);
        input2.setHint("Thread Description");


        ll.addView(input);
        ll.addView(input2);
        builder.setView(ll);

// Set up the buttons
        builder.setPositiveButton("CREATE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String title="", titleEncoded="", desc="", descEncoded="";
                try {
                    title = input.getText().toString();
                    titleEncoded = URLEncoder.encode(title, "utf-8");
                    desc = input2.getText().toString();
                    descEncoded = URLEncoder.encode(desc, "utf-8");
                } catch (UnsupportedEncodingException e) {
                    Toast.makeText(getActivity(), "Unsupported input", Toast.LENGTH_SHORT).show();
                    return;
                }
                showProgressDialog();
                String loginUrl = "http://" + ip + "/threads/new.json?title="+titleEncoded+"&description="+descEncoded+"&course_code=" + ((CoursePage) getActivity()).getCourseCode();
                System.out.println("URL HIT WAS NEW THREAD:" + loginUrl);
                StringRequest req = new StringRequest(Request.Method.GET, loginUrl, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String Response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject res = new JSONObject(Response);

                            String success = res.getString("success");
                            if (success.equals("false")) {
                                Toast.makeText(getActivity(), "Failed to create thread.", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getActivity(), "Thread created.", Toast.LENGTH_SHORT).show();
                                getThreads();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(getActivity(), "Server Error.", Toast.LENGTH_SHORT).show();
                    }
                });
                volley_singleton.getInstance(getActivity()).getRequestQueue().add(req);
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
        progressDialog = new ProgressDialog(getActivity(), R.style.Base_Theme_AppCompat_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setTitle("Creating thread...");
        progressDialog.show();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
