package br.com.seartv.service;

import android.app.Activity;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.Map;

import br.com.seartv.config.ConstantsUrl;
import br.com.seartv.utils.Utils;
import br.com.seartv.utils.VolleyRequestQueueUtil;

public class MoviesServiceImpl implements MoviesService {

    public Activity activity;

    public MoviesServiceImpl(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void getPopularMovies(final Response.Listener<PopularMoviesResponseModel> responseListener,
                                 final Response.ErrorListener errorListener,
                                 final Map<String, String> headers,
                                 final long page) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                String.format(ConstantsUrl.POPULAR_URL, page),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        responseListener.onResponse((PopularMoviesResponseModel) Utils.parseJsonToObject(response, PopularMoviesResponseModel.class));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        errorListener.onErrorResponse(error);
                    }
                }
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return headers;
            }
        };

        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                ConstantsUrl.SHORT_SOCKET_TIME_OUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        VolleyRequestQueueUtil.getInstance(activity).addToRequestQueue(jsonObjectRequest);
    }

    @Override
    public void getTopRatedMovies(final Response.Listener<TopRatedMoviesResponseModel> responseListener,
                                 final Response.ErrorListener errorListener,
                                 final Map<String, String> headers,
                                 final long page) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                String.format(ConstantsUrl.TOP_RATED_URL, page),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        responseListener.onResponse((TopRatedMoviesResponseModel) Utils.parseJsonToObject(response, TopRatedMoviesResponseModel.class));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        errorListener.onErrorResponse(error);
                    }
                }
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return headers;
            }
        };

        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                ConstantsUrl.SHORT_SOCKET_TIME_OUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        VolleyRequestQueueUtil.getInstance(activity).addToRequestQueue(jsonObjectRequest);
    }

    @Override
    public void getUpComingMovies(final Response.Listener<UpComingMoviesResponseModel> responseListener,
                                  final Response.ErrorListener errorListener,
                                  final Map<String, String> headers,
                                  final long page) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                String.format(ConstantsUrl.UPCOMING_URL, page),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        responseListener.onResponse((UpComingMoviesResponseModel) Utils.parseJsonToObject(response, UpComingMoviesResponseModel.class));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        errorListener.onErrorResponse(error);
                    }
                }
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return headers;
            }
        };

        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                ConstantsUrl.SHORT_SOCKET_TIME_OUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        VolleyRequestQueueUtil.getInstance(activity).addToRequestQueue(jsonObjectRequest);
    }
}
