# weather-app-sosm
Weather application for SOSM.

Alexandru-Adrian Achiritoaei, SCPD


## Description

The application allows the user to add / remove a city in order to obtain current weather details.

In order to obtain the details, the application makes HTTP requests to the OpenWeatherMap API
using Volley.

The user can swipe through cities (using viewpager & fragments).

The app is uses a default list of cities (Bucharest, Zurich, London). Then, user changes to this list
are saved in SharedPreferences, as to be remembered the next time the application si opened.

There are no checks for input correctness for the API calls or duplicates.
