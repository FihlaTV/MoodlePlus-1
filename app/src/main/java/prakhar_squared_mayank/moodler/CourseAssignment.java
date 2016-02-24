package prakhar_squared_mayank.moodler;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import prakhar_squared_mayank.moodler.R;
import prakhar_squared_mayank.moodler.models.Assignment;
import prakhar_squared_mayank.moodler.models.Course;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CourseAssignment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CourseAssignment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CourseAssignment extends Fragment implements AdapterView.OnItemClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


    public CourseAssignmentListAdapter courseAssignmentListAdapter;
    public String assignmentsUrl;
    public List<Assignment> assignment_list=new ArrayList<Assignment>();

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
     * @return A new instance of fragment CourseAssignment.
     */
    // TODO: Rename and change types and number of parameters
    public static CourseAssignment newInstance(String param1, String param2) {
        CourseAssignment fragment = new CourseAssignment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public CourseAssignment() {
        // Required empty public constructor
//        super();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout_view = inflater.inflate(R.layout.fragment_course_assignment, container, false);
        assignmentsUrl="http://"+MainActivity.ip+"/courses/course.json/"+((CoursePage)getActivity()).courseCode+"/assignments";

        System.out.println("Course Assignment fragment hits the url:" + assignmentsUrl);

        final ListView lv=(ListView) layout_view.findViewById(R.id.listview1);
        lv.setOnItemClickListener((AdapterView.OnItemClickListener) this);

        courseAssignmentListAdapter= new CourseAssignmentListAdapter(getActivity(),assignment_list);
        lv.setAdapter(courseAssignmentListAdapter);

        queryAssigns(lv);


        return layout_view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    public void queryAssigns(final ListView lv){
        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, assignmentsUrl, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            System.out.println(response);

                            JSONArray assignments = response.getJSONArray("assignments");
                            JSONObject course=response.getJSONObject("course");
                            String courseCode=course.getString("code");
                            for(int i=0;i<assignments.length();i++){
                                JSONObject tmp=assignments.getJSONObject(i);
                                Assignment assign=new Assignment();

                                String name=tmp.getString("name"); assign.setName(name);
                                String StartDate=tmp.getString("created_at"); assign.setStartDate(StartDate);
                                String descrip=tmp.getString("description"); assign.setDescription(descrip);
                                Log.d("Description",descrip);
                                String deadline=tmp.getString("deadline"); assign.setEndDate(deadline);
                                int id=tmp.getInt("id"); assign.setId(id);
                                assign.setCourseCode(courseCode);
                                assignment_list.add(assign);
                            }


                            // coursesAdapter=new CoursesAdapter(getActivity(),course_list,null);
                            //lv.setAdapter(coursesAdapter);
                           // System.out.println("Size of thelist:"+assignment_list.size());
                            courseAssignmentListAdapter.updateData(assignment_list);

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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent=new Intent(getActivity(),AssignmentDetails.class);
        Assignment a=assignment_list.get(position);
        intent.putExtra("assignmentId",a.getId());
        intent.putExtra("courseCode",a.getCourseCode());
        intent.putExtra("assign",a);
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
