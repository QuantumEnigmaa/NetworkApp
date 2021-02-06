package fr.isen.networkapp.pk

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.isen.networkapp.pk.databinding.PostCellBinding


class FeedAdapter(private val entries: List<String>): RecyclerView.Adapter<FeedAdapter.FeedViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {
        return FeedViewHolder(PostCellBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
        holder.titleCell.text = entries[position]
        holder.descriptionCell.text = "testing my best hard coded description"
        holder.photoCell.setImageResource(R.drawable.logoheart)
        holder.userCell.text = "some hard coded user"
    }

    override fun getItemCount(): Int {
        return entries.count()
    }

    class FeedViewHolder(postBinding: PostCellBinding): RecyclerView.ViewHolder(postBinding.root) {
        val titleCell: TextView = postBinding.celltitre
        val descriptionCell: TextView = postBinding.celldescription
        val photoCell: ImageView = postBinding.cellphoto
        val userCell: TextView = postBinding.celluser
    }
}
