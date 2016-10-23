package br.com.seartv.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import java.util.Map;

import br.com.seartv.R;
import butterknife.OnClick;

public class MovieDetailsActivity extends BaseActivity {

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        this.context = this;

        setHomeButton();
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

    }

    @OnClick(R.id.cast)
    public void onClickCast() {
        startActivity(new Intent(this, CastMovieListActivity.class));
    }
}
