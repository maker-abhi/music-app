package `in`.zyla.musicapp

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_songs_group.view.*

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

    class GroupsViewHolder(private val parentView: View) : RecyclerView.ViewHolder(parentView), LayoutContainer {

        override val containerView: View?
            get() = parentView

        private val songsAdapter: SongsAdapter = SongsAdapter()

        init {
            parentView.rv_songs.layoutManager =
                GridLayoutManager(parentView.context, 3, LinearLayoutManager.HORIZONTAL, false)
            parentView.rv_songs.adapter = songsAdapter
        }

        fun bind(songsGroup: SongsGroup) {
            parentView.group_name.text = songsGroup.groupName
            songsAdapter.itemsList = songsGroup.songs
        }
    }
}