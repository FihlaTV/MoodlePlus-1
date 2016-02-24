package prakhar_squared_mayank.moodler.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import prakhar_squared_mayank.moodler.R;
import prakhar_squared_mayank.moodler.models.Course;
import prakhar_squared_mayank.moodler.models.Grade;

/**
 * Created by Prakhar on 2/24/2016.
 */
public class CourseGradeAdapter extends ArrayAdapter<Grade> {


        private LayoutInflater inflater;
        private List<Grade> grades=new ArrayList<Grade>();
        //String[] item=new String[10];

        public CourseGradeAdapter(Activity activity, List<Grade> items){
            super(activity, R.layout.row_course_grades, items);
            inflater=activity.getWindow().getLayoutInflater();
            grades=items;
//        for(int i=0;i<items.size();i++){
//            tweetshere.add(items.get(i));
//        }

        }

        public void setListData(ArrayList<Grade> data){
            grades = data;
        }

        @Override
        public View getView(int position,View convertView,ViewGroup parent){
            if(convertView==null){
                convertView=inflater.inflate(R.layout.row_course_grades,parent,false);
            }

            TextView title=(TextView) convertView.findViewById(R.id.AssignTitle);
            TextView weitage=(TextView) convertView.findViewById(R.id.weitage);
            TextView totalMarks=(TextView) convertView.findViewById(R.id.totalMarks);
            TextView marksSecured=(TextView) convertView.findViewById(R.id.marksSecured);
            Grade tmp=grades.get(position);
            title.setText(tmp.getName());
            weitage.setText("Weitage:" + tmp.getWeitage());
            totalMarks.setText("Maximum Marks:"+tmp.getMaxMarks());
            marksSecured.setText("Marks Secured:"+tmp.getScore());

            return convertView;//inflater.inflate(R.layout.row_tweet,parent,false);
        }

        public void updateData(List<Grade> grade_list)
        {
            if(grade_list==null){
                return;
            }

            // courseshere.clear();

            grades=grade_list;


            //my adapter holds an internal list of DataItems
            notifyDataSetChanged();
        }

}
