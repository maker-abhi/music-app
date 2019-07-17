# music-app

Instructions:
1. Clone the repository: git clone https://github.com/maker-abhi/music-app.git
2. Open the project in Android Studio. The IDE will automatically pull all the dependencies and build the project
3. Make sure all the dependencies are resolved and there are no errors
4. Run the app on an emulator or a device
5. Play with it!


Something about the solution itself:
1. Written entirely in Kotlin
2. Uses Android data binding library for accessing and setting data on views
3. SongsGroup is a data class to store data related to a group displayed on screen
4. sample_music_data.csv file is placed in the assets folder which gets loaded when the app starts
5. The data from csv is parsed into two HashMaps based on grouping Album/Artist.
   Here key is the album/artist name and values are the list of songs.
