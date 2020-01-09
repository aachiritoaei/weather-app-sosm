package com.example.weatherapplicationsosm;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.android.volley.RequestQueue;

import java.util.List;

public class WeatherCollectionAdapter extends FragmentStateAdapter {

    List<String> citiesList;
    RequestQueue requestQueue;

    public WeatherCollectionAdapter(FragmentActivity fa, List<String> citiesList, RequestQueue requestQueue) {
        super(fa);
        this.citiesList = citiesList;
        this.requestQueue = requestQueue;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return new WeatherFragment(citiesList.get(position), requestQueue);
    }

    @Override
    public int getItemCount() {
        return citiesList.size();
    }
}
