package `in`.zyla.musicapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*

private const val FILE_NAME = "sample_music_data.csv"

class MainActivity : AppCompatActivity() {

    private val dataByArtist = HashMap<String, SongsGroup>()
    private var dataByAlbum = HashMap<String, SongsGroup>()

    private lateinit var groupsAdapter: GroupsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var lines = assets.open(FILE_NAME).bufferedReader().readLines()
        lines = lines.subList(1, lines.lastIndex + 1)
        parseData(lines)
        System.out.println(lines.toString())

        rv_groups.layoutManager = LinearLayoutManager(this)
        groupsAdapter = GroupsAdapter()
        rv_groups.adapter = groupsAdapter
        spinner_group_by.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listOf("Artist", "Album"))
        spinner_songs_per_page.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listOf("3", "4"))
        groupsAdapter.itemsList = dataByArtist.values.toList()
    }

    private fun parseData(data: List<String>) {
        data.forEach {
            val songData = it.split(",")
            val name = songData[0]
            val artist = songData[1]
            val album = songData[2]

            var songsGroup = dataByAlbum[album]
            if (songsGroup == null) {
                songsGroup = SongsGroup(album, mutableListOf(name))
                dataByAlbum[album] = songsGroup
            } else {
                songsGroup.songs.add(name)
            }

            songsGroup = dataByArtist[artist]
            if (songsGroup == null) {
                songsGroup = SongsGroup(artist, mutableListOf(name))
                dataByArtist[artist] = songsGroup
            } else {
                songsGroup.songs.add(name)
            }
        }
    }

}
