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

    var spanCount: Int = 3
        set(value) {
            field = value
            notifyDataSetChanged()
        }

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
            ), spanCount
        )

    override fun onBindViewHolder(holder: GroupsViewHolder, position: Int) {
        holder.bind(itemsList[position], spanCount)
    }

    class GroupsViewHolder(parentView: View, spanCount: Int) : RecyclerView.ViewHolder(parentView) {

        private var songsGroupBinding: ItemSongsGroupBinding? = DataBindingUtil.bind(parentView)
        private val songsAdapter: SongsAdapter = SongsAdapter()
        private val layoutManager =
            GridLayoutManager(parentView.context, spanCount, LinearLayoutManager.HORIZONTAL, false)


        init {
            songsGroupBinding?.rvSongs?.layoutManager = layoutManager
            songsGroupBinding?.rvSongs?.adapter = songsAdapter
        }

        fun bind(songsGroup: SongsGroup, spanCount: Int) {
            layoutManager.spanCount = spanCount
            songsGroupBinding?.setGroupName(songsGroup.groupName)
            songsAdapter.itemsList = songsGroup.songs
        }
    }
}