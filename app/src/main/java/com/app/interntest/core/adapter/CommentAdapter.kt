package com.app.interntest.core.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.interntest.core.domain.model.Comment
import com.app.interntest.databinding.ItemCommentBinding

class CommentAdapter : RecyclerView.Adapter<CommentAdapter.ListViewHolder>() {

    private var listComment = ArrayList<Comment>()

    fun setData(data: List<Comment>?) {
        if (data == null) return
        listComment.clear()
        listComment.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CommentAdapter.ListViewHolder {
        val itemCommentBinding =
            ItemCommentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(itemCommentBinding)
    }

    override fun onBindViewHolder(holder: CommentAdapter.ListViewHolder, position: Int) {
        holder.bind(listComment[position])
    }

    override fun getItemCount(): Int = listComment.size

    inner class ListViewHolder(private val binding: ItemCommentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Comment) {
            with(binding) {
                tvCommentAuthor.text = data.name
                tvCommentBody.text = data.body
            }
        }
    }
}