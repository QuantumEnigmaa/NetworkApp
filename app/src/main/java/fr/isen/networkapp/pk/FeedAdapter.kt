package fr.isen.networkapp.pk

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import fr.isen.networkapp.pk.databinding.PostCellBinding
import fr.isen.networkapp.pk.model.Post


class FeedAdapter(private val entries: MutableList<Post>): RecyclerView.Adapter<FeedAdapter.FeedViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {
        return FeedViewHolder(PostCellBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
        //TODO mega sale à refaire avec var temp et bind fonction (cf correction td categoryActivity)
        // + mise en page
        // + finir de remplir les text/img
        holder.titleCell.text = entries[position].postTitle

        holder.descriptionCell.text = "testing my best hard coded description"
        holder.photoCell.setImageResource(R.drawable.logoheart)
        holder.userCell.text = "some hard coded user"


        val context = holder.itemView.context
        holder.detailCell.setOnClickListener {
            context.startActivity(Intent(context, DetailPost::class.java).putExtra("POST_INFO", entries[position]))
        }
    }

    override fun getItemCount(): Int {
        return entries.count()
    }

    class FeedViewHolder(postBinding: PostCellBinding): RecyclerView.ViewHolder(postBinding.root) {
        val titleCell: TextView = postBinding.celltitre
        val descriptionCell: TextView = postBinding.celldescription
        val photoCell: ImageView = postBinding.cellphoto
        val userCell: TextView = postBinding.celluser
        val detailCell: TextView = postBinding.celldetail
    }

    companion object {
        const val POST_INFO = "POST_INFO"
    }
}
