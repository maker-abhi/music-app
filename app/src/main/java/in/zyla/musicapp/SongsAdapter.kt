package `in`.zyla.musicapp

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView

class SongsAdapter : RecyclerView.Adapter<SongsAdapter.TextAnswersViewHolder>() {

    var itemsList: List<String> = mutableListOf()
        set(items) {
            field = items
            notifyDataSetChanged()
        }

    override fun getItemCount() = itemsList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        TextAnswersViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_song,
                parent,
                false
            ) as TextView
        )

    override fun onBindViewHolder(holder: TextAnswersViewHolder, position: Int) {
        holder.bind(itemsList[position])
    }

    class TextAnswersViewHolder(private val textView: TextView) : RecyclerView.ViewHolder(textView) {

        fun bind(textAnswer: String) {
            textView.text = textAnswer
        }
    }
}