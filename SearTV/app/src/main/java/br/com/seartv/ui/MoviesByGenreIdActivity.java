package br.com.seartv.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.Map;

import br.com.seartv.R;
import br.com.seartv.custom.MoviesAdapter;
import br.com.seartv.model.Movie;
import butterknife.Bind;

public class MoviesByGenreIdActivity extends BaseActivity {

    @Bind(R.id.lst_movies)
    RecyclerView lstMovies;

    private Context context;
    private ArrayList<Movie> movies;
    private MoviesAdapter moviesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies_by_genre_id);

        setHomeButton();

        fillInformation();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private void fillInformation() {
        movies = new ArrayList();
        addMockedMovies(movies);
        moviesAdapter = new MoviesAdapter(context, movies);

        lstMovies.setAdapter(moviesAdapter);
        lstMovies.setItemAnimator(new DefaultItemAnimator());
        lstMovies.setLayoutManager(new LinearLayoutManager(this));
    }

    private void addMockedMovies(ArrayList<Movie> movies) {
        Movie movie = new Movie();
        movie.setOriginal_title("Whiplash");
        movie.setTitle("Whiplash");
        movie.setVote_average(7.8);
        movies.add(movie);

        Movie movie2 = new Movie();
        movie2.setOriginal_title("Whiplash 2");
        movie2.setTitle("Whiplash 2");
        movie2.setVote_average(6.8);
        movies.add(movie2);
    }
}
