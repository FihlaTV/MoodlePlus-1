package prakhar_squared_mayank.moodler;

import android.app.Application;
import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Prakhar on 1/16/2016.
 */

public class volley_singleton{//} extends Application {
    private RequestQueue _RequestQueue;
    private static volley_singleton _volley_singleton;
    private static Context _context;

    public static final String TAG=volley_singleton.class.getName();

    public volley_singleton(Context context){
        _context=context;

        _RequestQueue= getRequestQueue();

    }

    public static synchronized volley_singleton getInstance(Context context){
        if (_volley_singleton== null) {
            _volley_singleton= new volley_singleton(context);
        }
        return _volley_singleton;

    }

    public RequestQueue getRequestQueue(){
        if (_RequestQueue== null) {
            _RequestQueue = Volley.newRequestQueue(_context, 10 * 1024 * 1024); // this is for caching request
        }

        return _RequestQueue;
    }

    public <T> void add(Request<T> req){
        getRequestQueue().add(req);
    }
    public void cancel(){
        _RequestQueue.cancelAll(TAG);
    }


}
