package `in`.zyla.musicapp

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import `in`.zyla.musicapp.databinding.ItemSongBinding
import android.databinding.DataBindingUtil
import android.view.View

class SongsAdapter : RecyclerView.Adapter<SongsAdapter.SongViewHolder>() {

    var itemsList: List<String> = mutableListOf()
        set(items) {
            field = items
            notifyDataSetChanged()
        }

    override fun getItemCount() = itemsList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        SongViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_song,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        holder.bind(itemsList[position])
    }

    class SongViewHolder(parentView: View) : RecyclerView.ViewHolder(parentView) {
        private var songsGroupBinding: ItemSongBinding? = DataBindingUtil.bind(parentView)

        fun bind(songName: String) {
            songsGroupBinding?.songName = songName
        }
    }
}