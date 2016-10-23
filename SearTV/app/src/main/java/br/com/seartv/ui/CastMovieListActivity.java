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
import br.com.seartv.custom.CastMovieAdapter;
import br.com.seartv.model.CastMovieMember;
import br.com.seartv.model.Movie;
import butterknife.Bind;

public class CastMovieListActivity extends BaseActivity {

    @Bind(R.id.lst_cast)
    RecyclerView lstCast;

    private Context context;
    private ArrayList<CastMovieMember> castMembers;
    private CastMovieAdapter castAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cast_list);

        this.context = this;

        setHomeButton();
        setToolbarTitle("Elenco");
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

    @Override
    protected void onResume() {
        super.onResume();
        fillInformation();
    }

    private void fillInformation() {
        castMembers = new ArrayList<CastMovieMember>();
        addMockedCast(castMembers);
        castAdapter = new CastMovieAdapter(context, castMembers);

        lstCast.setAdapter(castAdapter);
        lstCast.setItemAnimator(new DefaultItemAnimator());
        lstCast.setLayoutManager(new LinearLayoutManager(this));

    }

    private void addMockedCast(ArrayList<CastMovieMember> castMembers) {
        CastMovieMember cast1 = new CastMovieMember();
        cast1.setName("Pedro");
        cast1.setCharacter("John");
        castMembers.add(cast1);

        CastMovieMember cast2 = new CastMovieMember();
        cast2.setName("Marcos");
        cast2.setCharacter("Peter");
        castMembers.add(cast2);
    }
}
