package com.example.weatherapplicationsosm;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity {

    private static final String PLACES_API_KEY = "AIzaSyA-CWYXgBjdZ7l9HjDGOxlfknuaR1e0A_U";

    private RequestQueue requestQueue;

    private List<String> citiesList;
    private ViewPager viewPager;
    private WeatherCollectionAdapter pagerAdapter;
    private EditText cityId;
    private Button addButton, deleteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // HTTP Volley request queue.
        requestQueue = Volley.newRequestQueue(this);

//        // Places API KEY.
//        Places.initialize(this, PLACES_API_KEY);
//        PlacesClient placesClient = Places.createClient(this);
//
//        // Initialize the AutocompleteSupportFragment.
//        AutocompleteSupportFragment autocompleteFragment = (AutocompleteSupportFragment)
//                getSupportFragmentManager().findFragmentById(R.id.autocompleteFragment);
//        // Specify the types of place data to return.
//        autocompleteFragment.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME));
//        autocompleteFragment.setTypeFilter(TypeFilter.CITIES);
//        // Set up a PlaceSelectionListener to handle the response.
//        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
//            @Override
//            public void onPlaceSelected(Place place) {
//                pagerAdapter.addItem(place.getId());
//            }
//
//            @Override
//            public void onError(Status status) {
//                Log.i("TEST", "An error occurred: " + status);
//            }
//        });

        // Swipe ViewPager.
        viewPager = findViewById(R.id.pager);
        initializeCities();
        pagerAdapter = new WeatherCollectionAdapter(getSupportFragmentManager(), citiesList, requestQueue);
        viewPager.setAdapter(pagerAdapter);

        // City edit text.
        cityId = findViewById(R.id.cityEditText);
        cityId.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    Log.i("ADD", cityId.getText().toString());
                    pagerAdapter.addItem(cityId.getText().toString());
                    cityId.setText("");
                    return true;
                }
                return false;
            }
        });

        // Add a add button.
        addButton = findViewById(R.id.addCity);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("ADD", cityId.getText().toString());
                pagerAdapter.addItem(cityId.getText().toString());
                cityId.setText("");
            }
        });

        // Add a delete button.
        deleteButton = findViewById(R.id.deleteCity);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                System.out.println(viewPager.getCurrentItem());
                Log.i("REMOVE", viewPager.getCurrentItem() + "");
                pagerAdapter.removeItem(viewPager.getCurrentItem());
            }
        });
    }

    private void initializeCities() {
        citiesList = new ArrayList<>();
        citiesList.add("bucharest,ro"); // Bucharest
        citiesList.add("zurich,ch"); // Zurich
        citiesList.add("london,uk"); // London
    }
}
