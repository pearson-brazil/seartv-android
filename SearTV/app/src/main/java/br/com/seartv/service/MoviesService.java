package br.com.seartv.service;

import com.android.volley.Response;

import java.util.Map;

public interface MoviesService {
    void getPopularMovies(final Response.Listener<PopularMoviesResponseModel> responseListener,
                          final Response.ErrorListener errorListener,
                          final Map<String, String> headers,
                          final long page);

    void getTopRatedMovies(final Response.Listener<TopRatedMoviesResponseModel> responseListener,
                           final Response.ErrorListener errorListener,
                           final Map<String, String> headers,
                           final long page);

    void getUpComingMovies(final Response.Listener<UpComingMoviesResponseModel> responseListener,
                           final Response.ErrorListener errorListener,
                           final Map<String, String> headers,
                           final long page);
}