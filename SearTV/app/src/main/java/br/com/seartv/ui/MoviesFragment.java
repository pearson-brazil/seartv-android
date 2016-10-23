package br.com.seartv.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.seartv.R;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MoviesFragment extends Fragment {

    private Context context;

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

        fillInformation();

        return view;
    }

    private void fillInformation() {

    }

    @OnClick(R.id.card)
    public void onClickCard() {
        Intent intent = new Intent(context, MovieDetailsActivity.class);
        context.startActivity(intent);
    }
}
