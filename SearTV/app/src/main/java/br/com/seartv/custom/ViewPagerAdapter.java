package br.com.seartv.custom;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import br.com.seartv.ui.GenresFragment;
import br.com.seartv.ui.MoviesFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    int qtdTabs;

    public ViewPagerAdapter(FragmentManager fm, int numTabs) {
        super(fm);
        this.qtdTabs = numTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new GenresFragment();
            case 1:
                return new MoviesFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return qtdTabs;
    }
}