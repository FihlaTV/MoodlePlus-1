package prakhar_squared_mayank.moodler;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by mayank on 18/02/16.
 */
public class GradeListViewAdapter extends BaseExpandableListAdapter {

    private Context _context;
    private List<String> courseCodes = new ArrayList<String>();
    private HashMap<String, List<String>> _listDataChild;
    JSONObject jsonObject;

    public GradeListViewAdapter(Context context, JSONObject jsonObj) {
        this._context = context;
        jsonObject = jsonObj;
    }

    public void updateData(JSONObject jsonObj) {
        jsonObject = jsonObj;
        courseCodes.clear();
        if(jsonObject == null)
        {
            return;
        }
        try {
            JSONArray coursesArray = jsonObject.getJSONArray("courses");
            for(int i=0;i<coursesArray.length();i++)
            {
                String code = coursesArray.getJSONObject(i).getString("code");
                if(!courseCodes.contains(code))
                {
                    courseCodes.add(code);
                }
            }
        }
        catch(JSONException e) {

        }
        notifyDataSetChanged();
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        JSONObject jsonObjectR = null;
        int num  = 0;
        try {
            JSONArray gradesArray = jsonObject.getJSONArray("grades");
            JSONArray courseArray = jsonObject.getJSONArray("courses");
            for(int i=0;i<gradesArray.length();i++)
            {
                String code = courseArray.getJSONObject(i).getString("code");
                if(code.equals(courseCodes.get(groupPosition)))
                {
                    if(num == childPosititon)
                    {
                        jsonObjectR = gradesArray.getJSONObject(i);
                        break;
                    }
                    num++;
                }
            }
        }
        catch(JSONException e) {

        }
        return jsonObjectR;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.grade_expanded_row, null);
        }

        JSONObject childData = (JSONObject) getChild(groupPosition, childPosition);
        String assignmentName="", obtainedMarks="", totalMarks="";
        try {
            assignmentName = childData.getString("name");
            obtainedMarks = childData.getString("score");
            totalMarks = childData.getString("out_of");
        }
        catch(JSONException e) {

        }
        TextView assignmentNameTV = (TextView) convertView.findViewById(R.id.assignment_name_tv);
        TextView marksTV = (TextView) convertView.findViewById(R.id.score_tv);

        assignmentNameTV.setText(assignmentName+": ");
        marksTV.setText(obtainedMarks+"/"+totalMarks);
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        int num = 0;
        try {
            JSONArray coursesArray = jsonObject.getJSONArray("courses");
            for(int i=0;i<coursesArray.length();i++)
            {
                String code = coursesArray.getJSONObject(i).getString("code");
                if(code.equals(courseCodes.get(groupPosition)))
                {
                    num++;
                }
            }
        }
        catch(JSONException e) {

        }
        Log.d("grade adapter", "Child at position " + groupPosition + " count : " + num);
        return num;
    }


    @Override
    public JSONObject getGroup(int groupPosition) {
        JSONObject groupObject = null;
        try {
            JSONArray coursesArray = jsonObject.getJSONArray("courses");
            for(int i=0;i<coursesArray.length();i++)
            {
                String code = coursesArray.getJSONObject(i).getString("code");
                if(code.equals(courseCodes.get(groupPosition)))
                {
                    groupObject = coursesArray.getJSONObject(i);
                    break;
                }
            }
        }
        catch(JSONException e) {

        }
        return groupObject;
    }

    @Override
    public int getGroupCount() {

        return courseCodes.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        JSONObject groupObject = getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.grade_collapsed_row, null);
        }

        String courseCodeString="", courseNameString="";
        try {
            courseCodeString = groupObject.getString("code").toUpperCase();
            courseNameString = groupObject.getString("name");
        }
        catch(JSONException e) {

        }
        TextView courseCodeTV = (TextView) convertView.findViewById(R.id.course_code_tv);
        courseCodeTV.setTypeface(null, Typeface.BOLD);
        courseCodeTV.setText(courseCodeString);

        TextView courseNameTV = (TextView) convertView.findViewById(R.id.course_name_tv);
        courseNameTV.setTypeface(null, Typeface.BOLD);
        courseNameTV.setText(courseNameString);

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}