# PlacesNearMe
An Android App that displays restaurant list data with a search bar and details screen.

### User Stories

- [x] Retrieve the place list data from the API provided in the assessment and display it in the listview
- [x] Users should be able to filter which places they see using the search bar.
- [x] When a user selects a place on the list of places, they should be able to then view all the details associated with that place.
  

### Application Walkthrough

<img src="https://github.com/Kariizma/PlacesNearMe/blob/master/PlacesNearMe_walkthrough.gif" width=250><br>

## Dependencies
- [Retrofit 2](https://square.github.io/retrofit/) - A type-safe HTTP client for Android and Java/Kotlin
- [Retrofit Moshi Converter](https://github.com/square/moshi/) Moshi is a modern JSON library for Android, Java and Kotlin. It makes it easy to parse JSON into Java and Kotlin classes
- [Jetpack](https://developer.android.com/jetpack) - Jetpack is a suite of libraries to help developers follow best practices, reduce boilerplate code, and write code that works consistently across Android versions and devices so that developers can focus on the code they care about.
-  [Hilt](https://developer.android.com/jetpack/androidx/releases/hilt) - Dependency Injection
-  [Mockito](https://site.mockito.org/) - Mocking for testing
-  [Material 3](https://m3.material.io/) - Material 3 UI
-  [Jetpack Compose]
-  [Jetpack Navigation]

  ## Improvements I can think of if I had more time
  - Error Handling: I could have added error handling to the UI by showing a loading screen or an error screen if the data from the API isn't fetched or if the user doesn't have internet.
  - API Efficiency: Im pretty sure im making an API call for every image that im getting using the ID. Im wondering if it would be more optimal to put the images within the places json.
  - Testing: I would write unit tests and UI tests with tools like Junit, Mockito, and Espresso to test all the work I have done and ensure it's working properly.
  - Accessibility: I would need to add the proper content descriptions to all interactive elements and ensure the app is usable for all users including those who have disabilities.
  - Repository: I would need to set up a local database using room with the API so the users can still use the application while in an offline setting.
