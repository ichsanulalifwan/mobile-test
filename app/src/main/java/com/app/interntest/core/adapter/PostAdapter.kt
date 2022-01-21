package com.app.interntest.core.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.interntest.core.domain.model.Post
import com.app.interntest.core.domain.model.User
import com.app.interntest.databinding.ItemPostBinding
import com.app.interntest.ui.listpost.ListPostFragment

class PostAdapter : RecyclerView.Adapter<PostAdapter.ListViewHolder>() {

    private lateinit var onItemClickListener: OnItemClickListener
    private var listPost = ArrayList<Post>()

    fun setData(data: List<Post>?) {
        if (data == null) return
        listPost.clear()
        listPost.addAll(data)
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostAdapter.ListViewHolder {
        val itemUserBinding =
            ItemPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(itemUserBinding)
    }

    override fun onBindViewHolder(holder: PostAdapter.ListViewHolder, position: Int) {
        holder.bind(listPost[position])
    }

    override fun getItemCount(): Int = listPost.size

    inner class ListViewHolder(private val binding: ItemPostBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(post: Post) {
            with(binding) {
                tvPostsTitle.text = post.title
                tvPostsBody.text = post.body
                tvUsersName.text = post.userId.toString()
                tvCompany.text = post.postId.toString()

                itemView.setOnClickListener {
                    onItemClickListener.onItemClicked(post)
                }
            }
        }
    }

    fun userData(id: Int): User? {
        val user = ListPostFragment()
        return user.getUserData(id)
    }

    interface OnItemClickListener {
        fun onItemClicked(post: Post)
    }
}