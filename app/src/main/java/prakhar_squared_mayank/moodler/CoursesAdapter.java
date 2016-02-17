package prakhar_squared_mayank.moodler;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import prakhar_squared_mayank.moodler.models.Course;

import java.util.ArrayList;
import java.util.List;

import prakhar_squared_mayank.moodler.R;
import prakhar_squared_mayank.moodler.models.Course;

/**
 * Created by Prakhar on 12/20/2015.
 */

public class CoursesAdapter extends ArrayAdapter<Course> {

    private LayoutInflater inflater;
    private List<Course> courseshere=new ArrayList<Course>();
    //String[] item=new String[10];

    public CoursesAdapter(Activity activity, List<Course> items, String[] item){
        super(activity, R.layout.row_courses_fragment,items);
        inflater=activity.getWindow().getLayoutInflater();
        courseshere=items;
//        for(int i=0;i<items.size();i++){
//            tweetshere.add(items.get(i));
//        }

    }

    @Override
    public View getView(int position,View convertView,ViewGroup parent){
        if(convertView==null){
            convertView=inflater.inflate(R.layout.row_courses_fragment,parent,false);
        }

        TextView title=(TextView) convertView.findViewById(R.id.courseTitle);
        TextView Description=(TextView) convertView.findViewById(R.id.courseDescription);
        TextView footer=(TextView) convertView.findViewById(R.id.footer);
        Course tmp=courseshere.get(position);
        title.setText(tmp.getCode()+"::"+tmp.getName());
        Description.setText("Description::"+tmp.getDescription());
        footer.setText("id:"+tmp.getId()+"  credits:"+tmp.getCredits()+"  l_t_p:"+tmp.getLtp());


        return convertView;//inflater.inflate(R.layout.row_tweet,parent,false);
    }
}
