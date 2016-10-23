package br.com.seartv.ui;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import br.com.seartv.R;
import butterknife.Bind;

public class ImageDetailActivity extends BaseActivity {

    @Bind(R.id.full_image)
    ImageView fullImage;
    @Bind(R.id.loading)
    RelativeLayout loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_detail);

        fullImage.setBackground(getDrawable(R.drawable.whiplash));

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
}
