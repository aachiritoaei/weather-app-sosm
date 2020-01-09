package com.example.weatherapplicationsosm;

import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity {

    private RequestQueue requestQueue;

    private List<String> citiesList;
    private ViewPager2 viewPager;
    private FragmentStateAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestQueue = Volley.newRequestQueue(this);

        viewPager = findViewById(R.id.pager);

        List<String> citiesList = new ArrayList<>();
        citiesList.add("bucharest,ro");
        citiesList.add("zurich,ch");
        citiesList.add("london,uk");

        pagerAdapter = new WeatherCollectionAdapter(this, citiesList, requestQueue);
        viewPager.setAdapter(pagerAdapter);
    }
}
