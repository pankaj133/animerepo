# Project Name
 -- Top animes
 
## Assumptions
- Users have an active internet connection when launching the app for the first time to fetch initial anime data.
- Offline mode relies on previously fetched and stored data using Room; no offline-first API write operations are required.
- Anime data (ratings, episodes, synopsis, cast) does not change frequently, so locally cached data remains valid until the next manual or automatic sync.
- I did not use the embed link. I used the video ID and played the trailer in the video player.
- Implemented pagination to load anime data incrementally as the user scrolls.
- Used Compose UI

## Features Implemented
- Displays a list of top-rated anime fetched from the Jikan API on the Home screen.
- Clean Architecture is implemented for better scalability and code maintainability.
- Implements pagination to load anime data page by page for better performance and smooth scrolling.
-  Caches anime data locally using Room for offline access.
- Handles API errors, database errors, and network failures gracefully with user-friendly messages.

## Known Limitations
- Offline support , like saving data room database
- Good Architecture for scalability and code maintainability.
- Network resilience
- Suppose you cannot show profile images due to a legal change — modify the UI accordingly without breaking the layout.
