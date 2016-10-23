package br.com.seartv.ui;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import br.com.seartv.R;
import br.com.seartv.custom.AboutDialog;
import br.com.seartv.custom.ViewPagerAdapter;
import butterknife.Bind;

public class HomeActivity extends BaseActivity {

    @Bind(R.id.tabLayout)
    TabLayout tabLayout;

    @Bind(R.id.pager)
    ViewPager viewPager;

    ViewPagerAdapter adapter;

    private static final int TAB_GENDERS = 0;
    private static final int TAB_MOVIES = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Adicionando Tabs com título em cada uma delas
        tabLayout.addTab(tabLayout.newTab().setText(getResources().getString(R.string.genres)));
        tabLayout.addTab(tabLayout.newTab().setText(getResources().getString(R.string.movies)));

        // Criando o Pager Adapter. É ele quem gerencia as abas
        adapter = new ViewPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);

        // Este listener permite que as abas possam acompanhar o movimento do swipe
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        // Este listener é utilizado para alterar o título da toolbar de acordo com a aba selecionada
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                switch (tab.getPosition()) {
                    case TAB_GENDERS:
                        setToolbarTitle(R.string.genres);
                        break;
                    case TAB_MOVIES:
                        setToolbarTitle(R.string.movies);
                        break;
                    default:
                        return;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        // Inicia o app na aba 'Filmes' selecionada
        tabLayout.getTabAt(TAB_MOVIES).select();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.home_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.about:
                new AboutDialog(this).showDialog();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
