package geogram.example.geoyandexgallery.adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import geogram.example.geoyandexgallery.ui.fragments.ImagesListFragment;

/**
 * Created by geogr on 23.04.2018.
 */

public class MainPagerAdapter extends FragmentPagerAdapter {
    private static final String CARS = "cars";
    private static final String NATURE = "nature";
    private static final String ART = "art";
    public static final String ARG_TYPE = "type";
    private final List<String> titles = new ArrayList<>();

    public MainPagerAdapter(FragmentManager fm) {
        super(fm);
        titles.add(CARS);
        titles.add(NATURE);
        titles.add(ART);
    }

    @Override
    public Fragment getItem(int position) {
        final ImagesListFragment fragment = new ImagesListFragment();
        Bundle args = new Bundle();
        switch (position) {
            case 0:
                args.putString(ARG_TYPE, CARS);
                break;
            case 1:
                args.putString(ARG_TYPE, NATURE);
                break;
            case 2:
                args.putString(ARG_TYPE, ART);
                break;

        }
        fragment.setArguments(args);
        return fragment;

    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }

    @Override
    public int getCount() {
        return titles.size();
    }
}