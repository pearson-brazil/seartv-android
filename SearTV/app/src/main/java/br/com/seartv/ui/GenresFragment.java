package br.com.seartv.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import br.com.seartv.R;
import butterknife.Bind;
import butterknife.ButterKnife;

public class GenresFragment extends Fragment {

    @Bind(R.id.lst_genres)
    ListView lstGenres;

    String[] genres;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_genres, container, false);
        ButterKnife.bind(this, view);

        fillInformation();

        return view;
    }

    private void fillInformation() {

        genres = getResources().getStringArray(R.array.genres_category);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, genres);
        lstGenres.setAdapter(adapter);

        lstGenres.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(GenresFragment.this.getActivity(), MovieDetailsActivity.class);
                startActivity(intent);
            }
        });
    }
}
