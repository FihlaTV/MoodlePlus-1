<<<<<<< HEAD
//package com.example.prakhar0409.twitterapp;
//
//import android.app.Activity;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.TextView;
//
//import com.example.prakhar0409.twitterapp.models.Tweet;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import prakhar_squared_mayank.moodler.R;
//
///**
// * Created by Prakhar on 12/20/2015.
// */
//
//public class CoursesAdapter extends ArrayAdapter<Tweet> {
//
//    private LayoutInflater inflater;
//    private List<Tweet> tweetshere=new ArrayList<Tweet>();
//    //String[] item=new String[10];
//
//    public CoursesAdapter(Activity activity, List<Tweet> items, String[] item){
//        super(activity, R.layout.row_tweet,items);
//        inflater=activity.getWindow().getLayoutInflater();
//        tweetshere=items;
////        for(int i=0;i<items.size();i++){
////            tweetshere.add(items.get(i));
////        }
//
//    }
//
//    @Override
//    public View getView(int position,View convertView,ViewGroup parent){
//        if(convertView==null){
//            convertView=inflater.inflate(R.layout.row_tweet,parent,false);
//        }
//
//        TextView tweetTitle=(TextView) convertView.findViewById(R.id.tweetTitle);
//        TextView tweetBody=(TextView) convertView.findViewById(R.id.tweetBody);
//
//        tweetTitle.setText(tweetshere.get(position).getTitle());
//        tweetBody.setText(tweetshere.get(position).getBody());
//
//        return convertView;//inflater.inflate(R.layout.row_tweet,parent,false);
//    }
//}
=======
package com.example.prakhar0409.twitterapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.prakhar0409.twitterapp.models.Tweet;

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
>>>>>>> 31141b17da3bd6d346ced981f41ace883dd28b84
