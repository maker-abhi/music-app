package `in`.zyla.musicapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

private const val FILE_NAME = "sample_music_data.csv"

class MainActivity : AppCompatActivity() {

    private val dataByArtist = HashMap<String, MutableList<String>>()
    private var dataByAlbum = HashMap<String, MutableList<String>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var lines = assets.open(FILE_NAME).bufferedReader().readLines()
        lines = lines.subList(1, lines.lastIndex + 1)
        parseData(lines)
        System.out.println(lines.toString())
    }

    private fun parseData(data: List<String>) {
        data.forEach{
            val songData = it.split(",")
            val name = songData[0]
            val artist = songData[1]
            val album = songData[2]

            var songs = dataByAlbum[album]
            if (songs == null) {
                songs = mutableListOf(name)
                dataByAlbum[album] = songs
            } else {
                songs.add(name)
            }

            songs = dataByArtist[artist]
            if (songs == null) {
                songs = mutableListOf(name)
                dataByArtist[artist] = songs
            } else {
                songs.add(name)
            }
        }
    }

}
