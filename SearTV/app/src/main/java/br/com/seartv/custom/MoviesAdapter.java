package br.com.seartv.custom;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.ArrayList;
import java.util.List;

import br.com.seartv.R;
import br.com.seartv.config.ConstantsUrl;
import br.com.seartv.model.Movie;
import br.com.seartv.ui.MovieDetailsActivity;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class MoviesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public Context context;
    private final ArrayList<Movie> movies;
    private OnLoadMoreListenerInterface onLoadMoreListenerInterface;

    public MoviesAdapter(Context context, ArrayList<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View movieItem = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);
        return new MovieViewHolder(movieItem);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final MovieViewHolder movieViewHolder = (MovieViewHolder) holder;

        final Movie movie = movies.get(position);

        if (movie.getPoster_path() != null) {

            movieViewHolder.imageNotAvailable.setVisibility(View.GONE);
            movieViewHolder.loading.setVisibility(View.VISIBLE);
            Glide.with(context).load(String.format(ConstantsUrl.POSTER_URL, movie.getBackdrop_path()))
                    .thumbnail(0.5f)
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .listener(new RequestListener<String, GlideDrawable>() {
                        @Override
                        public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                            movieViewHolder.imageNotAvailable.setVisibility(View.VISIBLE);
                            movieViewHolder.loading.setVisibility(View.GONE);
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                            movieViewHolder.loading.setVisibility(View.GONE);
                            return false;
                        }
                    })
                    .bitmapTransform(new RoundedCornersTransformation(context, 4, 0, RoundedCornersTransformation.CornerType.ALL))
                    .into(movieViewHolder.wallpaper);
        }
        movieViewHolder.wallpaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MovieDetailsActivity.class);
                Bundle extra = new Bundle();
                extra.putSerializable("MOVIE", movies.get(position));
                intent.putExtras(extra);
                context.startActivity(intent);
            }
        });

        movieViewHolder.title.setText(movie.getTitle());
        movieViewHolder.grade.setText(String.valueOf(String.format("%.1f", movie.getVote_average())));

        if (position == movies.size() - 1) {
            onLoadMoreListenerInterface.onLoadMore();
        }
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public void addAll(List<Movie> movies) {
        this.movies.addAll(movies);
        notifyDataSetChanged();
    }

    public void setOnLoadMoreInterfaceListener(OnLoadMoreListenerInterface onLoadMoreInterfaceListener){
        this.onLoadMoreListenerInterface = onLoadMoreInterfaceListener;
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {

        public ImageView wallpaper;
        public TextView title;
        public TextView grade;
        public ProgressBar loading;
        public TextView imageNotAvailable;

        public MovieViewHolder(final View itemView) {
            super(itemView);

            wallpaper = (ImageView) itemView.findViewById(R.id.wallpaper);
            title = (TextView) itemView.findViewById(R.id.title);
            grade = (TextView) itemView.findViewById(R.id.grade);
            loading = (ProgressBar) itemView.findViewById(R.id.loading);
            imageNotAvailable = (TextView) itemView.findViewById(R.id.image_not_available);
        }
    }
}
