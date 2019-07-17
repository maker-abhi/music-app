package `in`.zyla.musicapp

import android.databinding.DataBindingUtil
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import `in`.zyla.musicapp.databinding.ItemSongsGroupBinding


class GroupsAdapter : RecyclerView.Adapter<GroupsAdapter.GroupsViewHolder>() {

    var itemsList: List<SongsGroup> = mutableListOf()
        set(items) {
            field = items
            notifyDataSetChanged()
        }

    override fun getItemCount() = itemsList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        GroupsViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_songs_group,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: GroupsViewHolder, position: Int) {
        holder.bind(itemsList[position])
    }

    class GroupsViewHolder(parentView: View) : RecyclerView.ViewHolder(parentView) {

        private var songsGroupBinding: ItemSongsGroupBinding? = DataBindingUtil.bind(parentView)
        private val songsAdapter: SongsAdapter = SongsAdapter()

        init {
            songsGroupBinding?.rvSongs?.layoutManager =
                GridLayoutManager(parentView.context, 3, LinearLayoutManager.HORIZONTAL, false)
            songsGroupBinding?.rvSongs?.adapter = songsAdapter
        }

        fun bind(songsGroup: SongsGroup) {
            songsGroupBinding?.setGroupName(songsGroup.groupName)
            songsAdapter.itemsList = songsGroup.songs
        }
    }
}