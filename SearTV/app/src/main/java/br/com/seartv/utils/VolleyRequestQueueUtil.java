package br.com.seartv.utils;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.RetryPolicy;
import com.android.volley.toolbox.Volley;

public class VolleyRequestQueueUtil {
    private static VolleyRequestQueueUtil mInstance;
    private RequestQueue mRequestQueue;
    private static Context mCtx;

    private VolleyRequestQueueUtil(Context context) {
        mCtx = context;
        mRequestQueue = getRequestQueue();
    }

    public static synchronized VolleyRequestQueueUtil getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new VolleyRequestQueueUtil(context);
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            mRequestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }

    public void setRetryPolicy(RetryPolicy policy) {
        mInstance.setRetryPolicy(policy);
    }
}