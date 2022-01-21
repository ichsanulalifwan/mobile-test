package com.app.interntest.ui.detailpost

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.interntest.core.adapter.CommentAdapter
import com.app.interntest.core.data.Resource
import com.app.interntest.core.domain.model.Post
import com.app.interntest.core.domain.model.User
import com.app.interntest.databinding.FragmentDetailPostBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailPostFragment : Fragment() {

    private lateinit var commentAdapter: CommentAdapter
    private val viewModel: DetailPostViewModel by viewModel()
    private var _binding: FragmentDetailPostBinding? = null
    private val binding get() = _binding!!
    private val args by navArgs<DetailPostFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentDetailPostBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {

            // Init Toolbar
            val toolbar = binding.toolbar

            // Set Navigate to previous page (backstack)
            toolbar.setNavigationOnClickListener {
                it.findNavController().navigateUp()
            }

            // Get Data
            val postData = args.post
            populateData(postData)
            getComments(postData.postId)

            showProgressBar(true)
            setupRecyclerView()

        }
    }

    // Populate and set post data from previous page
    private fun populateData(post: Post) {
        with(binding) {
            tvPostsTitle.text = post.title
            tvPostsBody.text = post.body
            tvUserName.text = getUserData(post.userId)?.name
        }
    }

    private fun getUserData(id: Int): User? {
        var userData: User? = null
        viewModel.setSelectedUser(id)
        viewModel.user.observe(viewLifecycleOwner) { user ->
            if (user != null) {
                when (user) {
                    is Resource.Loading<*> -> showProgressBar(true)
                    is Resource.Success<*> -> {
                        showProgressBar(false)
                        userData = user.data
                    }
                    is Resource.Error<*> -> {
                        showProgressBar(false)
                        Toast.makeText(
                            context,
                            user.message,
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        }

        return userData
    }

    // Init and setup recyclerview and adapter for comments
    private fun setupRecyclerView() {
        commentAdapter = CommentAdapter()
        with(binding.rvComment) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = commentAdapter
        }
    }

    private fun getComments(postId: Int) {
        viewModel.setSelectedPost(postId)
        viewModel.comments.observe(viewLifecycleOwner) { user ->
            if (user != null) {
                when (user) {
                    is Resource.Loading<*> -> showProgressBar(true)
                    is Resource.Success<*> -> {
                        showProgressBar(false)
                        commentAdapter.setData(user.data)
                    }
                    is Resource.Error<*> -> {
                        showProgressBar(false)
                        Toast.makeText(
                            context,
                            user.message,
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        }
    }

    /**
     * Change the visibility of progressBar
     * true --> Show progressBar
     * false --> Hide progressBar
     */
    private fun showProgressBar(state: Boolean) {
        binding.progressBar.visibility = if (state) View.VISIBLE
        else View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}