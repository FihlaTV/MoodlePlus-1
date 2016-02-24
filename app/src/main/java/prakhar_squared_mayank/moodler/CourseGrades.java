package prakhar_squared_mayank.moodler;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import java.util.List;

import prakhar_squared_mayank.moodler.Adapters.CourseGradeAdapter;
import prakhar_squared_mayank.moodler.R;
import prakhar_squared_mayank.moodler.models.Grade;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CourseGrades.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CourseGrades#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CourseGrades extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    public CourseGradeAdapter courseGradeAdapter;
    public String courseGradeUrl;
    public List<Grade> grade_list=new ArrayList<Grade>();

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CourseGrades.
     */
    // TODO: Rename and change types and number of parameters
    public static CourseGrades newInstance(String param1, String param2) {
        CourseGrades fragment = new CourseGrades();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public CourseGrades() {
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
        View layout_view = inflater.inflate(R.layout.fragment_course_grades, container, false);
        courseGradeUrl="http://"+MainActivity.ip+"/courses/course.json/"+((CoursePage)getActivity()).courseCode+"/grades";

        System.out.println("Course Grade fragment hits the url:" + courseGradeUrl);

        final ListView lv=(ListView) layout_view.findViewById(R.id.listview1);

        courseGradeAdapter= new CourseGradeAdapter(getActivity(),grade_list);
        lv.setAdapter(courseGradeAdapter);

        queryGrades(lv);
        return layout_view;
    }

    public void queryGrades(final ListView lv){
        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, courseGradeUrl, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {


                            JSONArray grades = response.getJSONArray("grades");


                            for(int i=0;i<grades.length();i++){
                                JSONObject tmp=grades.getJSONObject(i);
                                Grade g=new Grade();

                                String name=tmp.getString("name"); g.setName(name);
                                int weightage=tmp.getInt("weightage"); g.setWeitage(weightage);
                                int maxMarks=tmp.getInt("out_of"); g.setMaxMarks(maxMarks);
                                int score=tmp.getInt("out_of"); g.setScore(score);
                                int id=tmp.getInt("id"); g.setId(id);
                                int user_id=tmp.getInt("user_id"); g.setUser_id(user_id);

                                grade_list.add(g);
                            }


                            System.out.println("First course name::"+grade_list.get(0).getName());
                            courseGradeAdapter.updateData(grade_list);

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
    public void onAttach(Activity activity) {
        super.onAttach(activity);
//        try {
//            mListener = (OnFragmentInteractionListener) activity;
//        } catch (ClassCastException e) {
//            throw new ClassCastException(activity.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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
