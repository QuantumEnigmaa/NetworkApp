package fr.isen.networkapp.pk.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.isen.networkapp.pk.databinding.CommentCellBinding

class CommentAdapter(private val commentList: ArrayList<String>): RecyclerView.Adapter<CommentAdapter.CommentViewHolder>() {
    class CommentViewHolder(binding: CommentCellBinding): RecyclerView.ViewHolder(binding.root) {
        val comment: TextView = binding.commentCellText
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val commentBinding = CommentCellBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CommentViewHolder(commentBinding)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.comment.text = commentList[position]
    }

    override fun getItemCount(): Int {
        return commentList.size
    }

}