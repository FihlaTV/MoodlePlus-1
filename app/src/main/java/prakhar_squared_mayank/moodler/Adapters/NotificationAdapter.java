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
import prakhar_squared_mayank.moodler.models.Notification;

/**
 * Created by Prakhar on 2/24/2016.
 */
public class NotificationAdapter extends ArrayAdapter<Notification> {

    private LayoutInflater inflater;
    private List<Notification> notification_list=new ArrayList<Notification>();
    //String[] item=new String[10];

    public NotificationAdapter(Activity activity, List<Notification> items){
        super(activity, R.layout.row_notification,items);
        inflater=activity.getWindow().getLayoutInflater();
        notification_list=items;
//        for(int i=0;i<items.size();i++){
//            tweetshere.add(items.get(i));
//        }

    }

    public void setListData(ArrayList<Notification> data){
        notification_list = data;
    }

    @Override
    public View getView(int position,View convertView,ViewGroup parent){
        if(convertView==null){
            convertView=inflater.inflate(R.layout.row_notification,parent,false);
        }


        TextView Description=(TextView) convertView.findViewById(R.id.description);
        TextView DateCreated=(TextView) convertView.findViewById(R.id.DateCreated);

        Notification tmp=notification_list.get(position);
        Description.setText(tmp.getDescription());
        DateCreated.setText("Created On:"+tmp.getCreated_on());

        return convertView;//inflater.inflate(R.layout.row_tweet,parent,false);
    }

    public void updateData(List<Notification> notifs)
    {
        if(notification_list==null){
            return;
        }

        // courseshere.clear();
        notification_list.clear();
        notification_list=notifs;


        //my adapter holds an internal list of DataItems
        this.notifyDataSetChanged();
    }

}
