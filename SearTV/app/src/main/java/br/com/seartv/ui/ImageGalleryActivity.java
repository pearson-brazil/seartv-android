package br.com.seartv.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import java.util.ArrayList;
import java.util.Map;

import br.com.seartv.R;
import br.com.seartv.custom.ImageGalleryAdapter;
import br.com.seartv.model.Image;
import butterknife.Bind;

public class ImageGalleryActivity extends BaseActivity {

    @Bind(R.id.image_gallery)
    GridView gridView;

    private static int galleryType;
    private ImageGalleryAdapter gridAdapter;
    private Context context;
    private ArrayList<Image> images;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_gallery);

        this.context = this;

        setHomeButton();
        setToolbarTitle("Galeria de imagens");

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(context, ImageDetailActivity.class);
                startActivity(intent);
            }
        });
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
        images = new ArrayList<>();
        Image image1 = new Image();
        images.add(image1);
        gridAdapter = new ImageGalleryAdapter(context, R.layout.item_image_gallery, images);
        gridView.setAdapter(gridAdapter);
    }
}
