package prakhar_squared_mayank.moodler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by mayank on 24/02/16.
 */
public class CourseThreadsAdapter extends BaseAdapter {

    Context mContext;
    JSONArray mJsonArray;

    public CourseThreadsAdapter(Context context, JSONArray arr) {
        mContext = context;
        mJsonArray = arr;
    }

    @Override
    public int getCount() {
        if(mJsonArray == null)
        {
            return 0;
        }
        return mJsonArray.length();
    }

    @Override
    public Object getItem(int position) {
        return mJsonArray.optJSONObject(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void updateData(JSONArray jsonArray) {
        // update the adapter's dataset
        mJsonArray = jsonArray;
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.course_thread_row, null);

            // create a new "Holder" with subviews
            holder = new ViewHolder();
            holder.threadTitle = (TextView) convertView.findViewById(R.id.thread_title);
            holder.threadDesc = (TextView) convertView.findViewById(R.id.thread_desc);

            // hang onto this holder for future recyclage
            convertView.setTag(holder);
        } else {

            // skip all the expensive inflation/findViewById
            // and just get the holder you already made
            holder = (ViewHolder) convertView.getTag();
        }
        JSONObject jsonObject = (JSONObject) getItem(position);

        String threadTitle = "";
        String threadDesc = "";

        if (jsonObject.has("title")) {
            threadTitle = jsonObject.optString("title");
        }

        if (jsonObject.has("description")) {
            threadDesc = jsonObject.optString("description");
        }

// Send these Strings to the TextViews for display
        holder.threadTitle.setText(threadTitle);
        holder.threadDesc.setText(threadDesc);

        return convertView;
    }

    private static class ViewHolder {
        public TextView threadTitle;
        public TextView threadDesc;
    }
}
