package `in`.zyla.musicapp

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.ArrayAdapter
import `in`.zyla.musicapp.databinding.ActivityMainBinding
import android.view.View
import android.widget.AdapterView

private const val FILE_NAME = "sample_music_data.csv"

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private val dataByArtist = HashMap<String, SongsGroup>()
    private var dataByAlbum = HashMap<String, SongsGroup>()

    private val groupByList = listOf("Artist", "Album")
    private val songsPerPageList = listOf(2, 3, 4)

    private lateinit var groupsAdapter: GroupsAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        var lines = assets.open(FILE_NAME).bufferedReader().readLines()
        lines = lines.subList(1, lines.lastIndex + 1)
        parseData(lines)
        initViews()
    }

    private fun initViews() {
        binding.rvGroups.layoutManager = LinearLayoutManager(this)
        groupsAdapter = GroupsAdapter()
        binding.rvGroups.adapter = groupsAdapter
        binding.spinnerGroupBy.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, groupByList)
        binding.spinnerSongsPerPage.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, songsPerPageList)

        binding.spinnerGroupBy.onItemSelectedListener = this
        binding.spinnerSongsPerPage.onItemSelectedListener = this

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

    override fun onNothingSelected(parent: AdapterView<*>?) {
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        when (parent) {
            binding.spinnerGroupBy -> {
                if (groupByList[position] == "Album") {
                    groupsAdapter.itemsList = dataByAlbum.values.toList()
                    return
                }
                groupsAdapter.itemsList = dataByArtist.values.toList()
            }
            binding.spinnerSongsPerPage -> {
                val spanCount = songsPerPageList[position]
                groupsAdapter.spanCount = spanCount
            }
        }
    }
}
