package prakhar_squared_mayank.moodler;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import prakhar_squared_mayank.moodler.models.Assignment;


/**
 * Created by Prakhar on 2/24/2016.
 */
public class CourseAssignmentListAdapter extends ArrayAdapter<Assignment> {

        private LayoutInflater inflater;
        private List<Assignment> assignment_list=new ArrayList<Assignment>();
        private int numOfChar=100;
        //String[] item=new String[10];

        public CourseAssignmentListAdapter(Activity activity, List<Assignment> items){
            super(activity, R.layout.row_course_assignments_fragment,items);
            inflater=activity.getWindow().getLayoutInflater();
            assignment_list=items;
//        for(int i=0;i<items.size();i++){
//            tweetshere.add(items.get(i));
//        }

        }

        public void setListData(ArrayList<Assignment> data){
            assignment_list = data;
        }

        @Override
        public View getView(int position,View convertView,ViewGroup parent){
            if(convertView==null){
                convertView=inflater.inflate(R.layout.row_course_assignments_fragment,parent,false);
            }

            TextView title=(TextView) convertView.findViewById(R.id.AssignTitle);
            TextView Description=(TextView) convertView.findViewById(R.id.CourseAssignmentDescription);
            TextView StartDate=(TextView) convertView.findViewById(R.id.StartDate);
            TextView EndDate=(TextView) convertView.findViewById(R.id.EndDate);
            Assignment tmp=assignment_list.get(position);
            if(tmp!=null) {
                title.setText(tmp.getName());
                String des=tmp.getDescription();
                if (des!=null) {
                    Description.setText("Description:" + des.substring(0, Math.min(des.length(), numOfChar)));
                }
                StartDate.setText("Created_on:" + tmp.getStartDate());
                EndDate.setText("DeadLine:" + tmp.getEndDate());
            }
            return convertView;//inflater.inflate(R.layout.row_tweet,parent,false);
        }

        public void updateData(List<Assignment> assigns)
        {
            if(assigns==null){
                return;
            }
            // courseshere.clear();
            assignment_list=assigns;
            //my adapter holds an internal list of DataItems
            this.notifyDataSetChanged();
        }


}
