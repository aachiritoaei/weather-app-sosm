# weather-app-sosm
Weather application for SOSM.

Alexandru-Adrian Achiritoaei, SCPD


## Description

The application has a single main screen which allows the user to add / remove a city in order to
obtain current weather details.

The user can swipe through cities (using viewpager & fragments).

In order to obtain the details, the application makes HTTP requests to the OpenWeatherMap API
using Volley.

The app uses a default list of cities (Bucharest, Zurich, London), when first opened. Then, user
changes to this list are saved in SharedPreferences, as to be remembered the next time the
application is opened.

There are no checks for input correctness for the API calls or duplicates.

For a small demo, android_demo.webm is available.

I tried to use the Places API from Google in order to have an autocomplete search for cities, but API Quota Limits
made this not reliable, as I was reaching the limit pretty fast. (can be seen in the commented code)
