package com.example.weatherapplicationsosm;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class WeatherFragment extends Fragment {

    private String CITY = "bucharest,ro";
    private String API_KEY = "29e467343e075e50e073a55cd8a83d8c";
    private String API_URL = "https://api.openweathermap.org/data/2.5/weather";

    private TextView addressTxt, updated_atTxt, statusTxt, tempTxt, temp_minTxt, temp_maxTxt,
            windTxt, pressureTxt, humidityTxt;

    private RequestQueue requestQueue;

    public WeatherFragment(String city, RequestQueue requestQueue) {
        this.CITY = city;
        this.requestQueue = requestQueue;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View fragmentView = inflater.inflate(R.layout.fragment_weather, container, false);

        addressTxt = fragmentView.findViewById(R.id.address);
        updated_atTxt = fragmentView.findViewById(R.id.updated_at);
        statusTxt = fragmentView.findViewById(R.id.status);
        tempTxt = fragmentView.findViewById(R.id.temp);
        temp_minTxt = fragmentView.findViewById(R.id.temp_min);
        temp_maxTxt = fragmentView.findViewById(R.id.temp_max);
        windTxt = fragmentView.findViewById(R.id.wind);
        pressureTxt = fragmentView.findViewById(R.id.pressure);
        humidityTxt = fragmentView.findViewById(R.id.humidity);

        makeRequest(fragmentView);

        return fragmentView;
    }

    private void makeRequest(final View fragmentView) {
        // Start the loader.
        fragmentView.findViewById(R.id.loader).setVisibility(View.VISIBLE);
        fragmentView.findViewById(R.id.mainContainer).setVisibility(View.GONE);
        fragmentView.findViewById(R.id.errorText).setVisibility(View.GONE);

        // Make the request.
        String url = API_URL + "?q=" + CITY + "&units=metric&APPID=" + API_KEY;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObj = new JSONObject(response);
                    JSONObject main = jsonObj.getJSONObject("main");
                    JSONObject sys = jsonObj.getJSONObject("sys");
                    JSONObject wind = jsonObj.getJSONObject("wind");
                    JSONObject weather = jsonObj.getJSONArray("weather").getJSONObject(0);

                    Long updatedAt = jsonObj.getLong("dt");
                    String updatedAtText = "Updated at: " + new SimpleDateFormat("dd/MM/yyyy hh:mm a", Locale.ENGLISH).format(new Date(updatedAt * 1000));
                    String temp = main.getString("temp") + "°C";
                    String tempMin = "Min Temp: " + main.getString("temp_min") + "°C";
                    String tempMax = "Max Temp: " + main.getString("temp_max") + "°C";
                    String pressure = main.getString("pressure");
                    String humidity = main.getString("humidity");

                    String windSpeed = wind.getString("speed");
                    String weatherDescription = weather.getString("description");

                    String address = jsonObj.getString("name") + ", " + sys.getString("country");

                    // Populate data.
                    addressTxt.setText(address);
                    updated_atTxt.setText(updatedAtText);
                    statusTxt.setText(weatherDescription.toUpperCase());
                    tempTxt.setText(temp);
                    temp_minTxt.setText(tempMin);
                    temp_maxTxt.setText(tempMax);
                    windTxt.setText(windSpeed);
                    pressureTxt.setText(pressure);
                    humidityTxt.setText(humidity);

                    // Hide loader.
                    fragmentView.findViewById(R.id.mainContainer).setVisibility(View.VISIBLE);
                    fragmentView.findViewById(R.id.loader).setVisibility(View.GONE);
                } catch (JSONException e) {
                    fragmentView.findViewById(R.id.loader).setVisibility(View.GONE);
                    fragmentView.findViewById(R.id.errorText).setVisibility(View.VISIBLE);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                fragmentView.findViewById(R.id.loader).setVisibility(View.GONE);
                fragmentView.findViewById(R.id.errorText).setVisibility(View.VISIBLE);
            }
        });

        requestQueue.add(stringRequest);
    }
}
