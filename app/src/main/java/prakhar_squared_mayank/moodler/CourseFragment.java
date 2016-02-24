package prakhar_squared_mayank.moodler;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import prakhar_squared_mayank.moodler.R;
import prakhar_squared_mayank.moodler.models.Course;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CourseFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CourseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

public class CourseFragment extends Fragment implements AdapterView.OnItemClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    public ArrayList<Course> course_list=new ArrayList<Course>();
    public CoursesAdapter coursesAdapter;
    public String coursesUrl="http://"+MainActivity.ip+"/courses/list.json";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public CourseFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CourseFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CourseFragment newInstance(String param1, String param2) {
        CourseFragment fragment = new CourseFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
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
        // Inflate the layout for this fragment
        View layout_view = inflater.inflate(R.layout.fragment_course, container, false);

        System.out.println("Course fragment hits the url:" + coursesUrl);

        final ListView lv=(ListView) layout_view.findViewById(R.id.listview1);
        lv.setOnItemClickListener((AdapterView.OnItemClickListener) this);

        coursesAdapter= new CoursesAdapter(getActivity(),course_list,null);
        lv.setAdapter(coursesAdapter);

        queryCourse(lv);


        return layout_view;
    }


    public void queryCourse(final ListView lv){
        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, coursesUrl, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            System.out.println(response);

                            JSONArray courses = response.getJSONArray("courses");
                            for(int i=0;i<courses.length();i++){
                                JSONObject tmp=courses.getJSONObject(i);
                                Course course=new Course();

                                String code=tmp.getString("code"); course.setCode(code);
                                String name=tmp.getString("name"); course.setName(name);
                                String descrip=tmp.getString("description"); course.setDescription(descrip);
                                int credits=tmp.getInt("credits"); course.setCredits(credits);
                                int id=tmp.getInt("id"); course.setId(id);
                                String ltp=tmp.getString("l_t_p"); course.setLtp(ltp);
                                course_list.add(course);
                            }


                           // coursesAdapter=new CoursesAdapter(getActivity(),course_list,null);
                            //lv.setAdapter(coursesAdapter);
                            System.out.println("Size of thelist:"+course_list.size());
                            coursesAdapter.updateData(course_list);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub

                    }
                });
        volley_singleton.getInstance(getActivity()).getRequestQueue().add(jsObjRequest);

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity context) {
        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent=new Intent(getActivity(),CoursePage.class);
        Course c=course_list.get(position);
        intent.putExtra("courseCode",c.getCode());
        Log.d("Coursecode Clicked:", c.getCode());
        startActivity(intent);

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
        void onFragmentInteraction(Uri uri);
    }
}

