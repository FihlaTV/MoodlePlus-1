package prakhar_squared_mayank.moodler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by mayank on 24/02/16.
 */
public class ThreadDetailAdapter extends BaseAdapter {
    Context mContext;
    JSONObject mJsonObject;

    public ThreadDetailAdapter(Context context, JSONObject arr) {
        mContext = context;
        mJsonObject = arr;
    }

    @Override
    public int getCount() {
        if(mJsonObject == null)
        {
            return 0;
        }
        int num = 0;
        try {
            num = mJsonObject.getJSONArray("comments").length();
        }
        catch(JSONException e)
        {

        }
        return num;
    }

    @Override
    public Object getItem(int position) {
        return mJsonObject;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void updateData(JSONObject jsonArray) {
        // update the adapter's dataset
        mJsonObject = jsonArray;
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.thread_detail_row, null);

            // create a new "Holder" with subviews
            holder = new ViewHolder();
            holder.commentTV = (TextView) convertView.findViewById(R.id.comment_tdr);
            holder.usernameTV = (TextView) convertView.findViewById(R.id.username_tdr);
            holder.agoTV = (TextView) convertView.findViewById(R.id.ago_tdr);

            // hang onto this holder for future recyclage
            convertView.setTag(holder);
        } else {

            // skip all the expensive inflation/findViewById
            // and just get the holder you already made
            holder = (ViewHolder) convertView.getTag();
        }
        JSONObject jsonObject = (JSONObject) getItem(position);

        String commentString = "";
        String usernameString = "";
        String agoString = "";

        try {
            commentString = mJsonObject.getJSONArray("comments").getJSONObject(position).getString("description");
            usernameString = mJsonObject.getJSONArray("comment_users").getJSONObject(position).getString("username");
            agoString = mJsonObject.getJSONArray("times_readable").getString(position);
        }
        catch(JSONException e)
        {

        }

// Send these Strings to the TextViews for display
        holder.commentTV.setText(commentString);
        holder.usernameTV.setText(usernameString);
        holder.agoTV.setText("     "+agoString + " ago.");

        return convertView;
    }

    private static class ViewHolder {
        public TextView commentTV;
        public TextView agoTV;
        public TextView usernameTV;
    }
}
