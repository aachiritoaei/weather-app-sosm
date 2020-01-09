package com.example.weatherapplicationsosm;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;

import com.android.volley.RequestQueue;

import java.util.List;

public class WeatherCollectionAdapter extends FragmentStatePagerAdapter {

    List<String> citiesList;
    RequestQueue requestQueue;

    public WeatherCollectionAdapter(FragmentManager fa, List<String> citiesList, RequestQueue requestQueue) {
        super(fa);
        this.citiesList = citiesList;
        this.requestQueue = requestQueue;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return new WeatherFragment(citiesList.get(position), requestQueue);
    }

    @Override
    public int getCount() {
        return citiesList.size();
    }

    @Override
    public int getItemPosition(Object object) {
        // refresh all fragments when data set changed
        return PagerAdapter.POSITION_NONE;
    }

    public void removeItem(int index) {
        if (citiesList.size() > 0) {
            citiesList.remove(index);
            notifyDataSetChanged();
        }
    }

    public void addItem(String location) {
        citiesList.add(location);
        notifyDataSetChanged();
    }
}
