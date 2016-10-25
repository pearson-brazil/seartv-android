package br.com.seartv.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.ArrayList;
import java.util.Map;

import br.com.seartv.R;
import br.com.seartv.config.ConstantsGeneral;
import br.com.seartv.custom.MoviesAdapter;
import br.com.seartv.custom.OnLoadMoreListenerInterface;
import br.com.seartv.model.Movie;
import br.com.seartv.service.MoviesService;
import br.com.seartv.service.MoviesServiceImpl;
import br.com.seartv.service.PopularMoviesResponseModel;
import br.com.seartv.service.TopRatedMoviesResponseModel;
import br.com.seartv.service.UpComingMoviesResponseModel;
import br.com.seartv.utils.Utils;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MoviesFragment extends Fragment {

    private long pagePopular = 1;
    private long totalPagesPopular = 2;
    private long pageTopRated = 1;
    private long totalPagesTopRated = 2;
    private long pageUpComing = 1;
    private long totalPagesUpComing = 2;
    private static final int FILTER_POPULAR = 0;
    private static final int FILTER_TOP_RATED = 1;
    private static final int FILTER_UP_COMING = 2;
    private static int selectedFilter = FILTER_TOP_RATED;

    @Bind(R.id.loading)
    RelativeLayout loading;
    @Bind(R.id.lst_movies)
    RecyclerView lstMovies;

    /** filters */
    @Bind(R.id.filter_popular)
    Button filterPopular;
    @Bind(R.id.filter_best)
    Button filterBest;
    @Bind(R.id.filter_soon)
    Button filterSoon;

    private MoviesService moviesService;
    private Context context;

    private MoviesAdapter moviesAdapter;
    private ArrayList<Movie> movies;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.context = getContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movies, container, false);
        ButterKnife.bind(this, view);

        moviesService = new MoviesServiceImpl(getActivity());

        fillInformation();

        return view;
    }

    private void fillInformation() {
        movies = new ArrayList<Movie>();
        moviesAdapter = new MoviesAdapter(context, movies);

        lstMovies.setAdapter(moviesAdapter);
        lstMovies.setItemAnimator(new DefaultItemAnimator());
        lstMovies.setLayoutManager(new LinearLayoutManager(getActivity()));

        moviesAdapter.setOnLoadMoreInterfaceListener(new OnLoadMoreListenerInterface() {
            @Override
            public void onLoadMore() {
                updateCurrentTimeLine();
            }
        });

        changeStateFilterButton(filterBest, true);
        getTopRatedMovies();
    }

    public void changeStateFilterButton(Button filter, boolean isSelected) {
        if (isSelected) {
            filter.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.filter_button_selected));
            filter.setTextColor(ContextCompat.getColor(getContext(), R.color.filter_title_selected_color));
        } else {
            filter.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.filter_button_unselected));
            filter.setTextColor(ContextCompat.getColor(getContext(), R.color.colorAccent));
        }
    }

    public void getPopularMovies() {

        final Map<String, String> headers = Utils.generalHeader(getActivity());

        if (pagePopular < totalPagesPopular) {
            isLoading(true);
            moviesService.getPopularMovies(
                    new Response.Listener<PopularMoviesResponseModel>() {
                        @Override
                        public void onResponse(PopularMoviesResponseModel response) {

                            moviesAdapter.addAll(response.getResults());
                            totalPagesPopular = response.getTotal_pages();
                            pagePopular++;
                            isLoading(false);
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            handleResponseErrors(error);
                            isLoading(false);
                        }
                    },
                    headers,
                    pagePopular);
        }
    }

    public void getTopRatedMovies() {

        final Map<String, String> headers = Utils.generalHeader(getActivity());

        if (pageTopRated < totalPagesTopRated) {
            isLoading(true);
            moviesService.getTopRatedMovies(
                    new Response.Listener<TopRatedMoviesResponseModel>() {
                        @Override
                        public void onResponse(TopRatedMoviesResponseModel response) {

                            moviesAdapter.addAll(response.getResults());
                            totalPagesTopRated = response.getTotal_pages();
                            pageTopRated++;
                            isLoading(false);
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            handleResponseErrors(error);
                            isLoading(false);
                        }
                    },
                    headers,
                    pageTopRated);
        }
    }

    public void getUpComingMovies() {

        final Map<String, String> headers = Utils.generalHeader(getActivity());

        if (pageUpComing < totalPagesUpComing) {
            isLoading(true);
            moviesService.getUpComingMovies(
                    new Response.Listener<UpComingMoviesResponseModel>() {
                        @Override
                        public void onResponse(UpComingMoviesResponseModel response) {

                            moviesAdapter.addAll(response.getResults());
                            totalPagesUpComing = response.getTotal_pages();
                            pageUpComing++;
                            isLoading(false);
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            handleResponseErrors(error);
                            isLoading(false);
                        }
                    },
                    headers,
                    pageUpComing);
        }
    }

    private void handleResponseErrors(VolleyError error) {
        NetworkResponse response = error.networkResponse;

        if (response != null && response.data != null) {
            String errorMessage = Utils.trimMessage(new String(response.data), ConstantsGeneral.MESSAGE);

            if (errorMessage != null) {
                switch(response.statusCode) {

                }
            } else {
            }
        } else {
        }
    }

    public void isLoading(boolean isLoading) {

        loading.setVisibility(isLoading ? View.VISIBLE : View.GONE);
    }

    public void updateCurrentTimeLine() {
        switch (selectedFilter) {
            case FILTER_TOP_RATED:
                getTopRatedMovies();
                break;
            case FILTER_POPULAR:
                getPopularMovies();
                break;
            case FILTER_UP_COMING:
                getUpComingMovies();
                break;
        }
    }

    @OnClick({R.id.filter_popular, R.id.filter_best, R.id.filter_soon})
    public void onSelectFilterClick(View v) {

        changeStateFilterButton(filterPopular, false);
        changeStateFilterButton(filterBest, false);
        changeStateFilterButton(filterSoon, false);

        movies.clear();

        switch (v.getId()) {
            case R.id.filter_popular:
                pagePopular = 1;
                changeStateFilterButton(filterPopular, true);
                getPopularMovies();
                selectedFilter = FILTER_POPULAR;
                break;
            case R.id.filter_best:
                pageTopRated = 1;
                changeStateFilterButton(filterBest, true);
                getTopRatedMovies();
                selectedFilter = FILTER_TOP_RATED;
                break;
            case R.id.filter_soon:
                pageUpComing = 1;
                changeStateFilterButton(filterSoon, true);
                getUpComingMovies();
                selectedFilter = FILTER_UP_COMING;
                break;
        }
    }
}
