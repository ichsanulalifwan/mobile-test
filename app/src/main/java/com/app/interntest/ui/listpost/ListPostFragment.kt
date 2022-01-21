package com.app.interntest.ui.listpost

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.interntest.R
import com.app.interntest.core.adapter.PostAdapter
import com.app.interntest.core.data.Resource
import com.app.interntest.core.domain.model.Post
import com.app.interntest.core.domain.model.User
import com.app.interntest.databinding.FragmentListPostBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class ListPostFragment : Fragment() {

    private lateinit var postAdapter: PostAdapter
    private val listPostViewModel: ListPostViewModel by viewModel()
    private var _binding: FragmentListPostBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentListPostBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {

            setupRecyclerView()
            getData()
            showProgressBar(true)
            onItemSelected()
        }
    }

    // Init and setup recyclerview and adapter
    private fun setupRecyclerView() {
        postAdapter = PostAdapter()
        with(binding.rvPost) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = postAdapter
        }
    }

    // Fetch list post data from API
    private fun getData() {
        listPostViewModel.listPost.observe(viewLifecycleOwner) { post ->
            if (post != null) {
                when (post) {
                    is Resource.Loading<*> -> showProgressBar(true)
                    is Resource.Success<*> -> {
                        showProgressBar(false)
                        postAdapter.setData(post.data)
                    }
                    is Resource.Error<*> -> {
                        showProgressBar(false)
                        binding.viewError.root.visibility = View.VISIBLE
                        binding.viewError.tvError.text =
                            post.message ?: getString(R.string.something_wrong)
                    }
                }
            }
        }
    }

    // Fetch user data from API
    fun getUserData(userId: Int): User? {
        var userData: User? = null
        listPostViewModel.setSelectedUser(userId)
        listPostViewModel.user.observe(viewLifecycleOwner) { user ->
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

    // Navigate to Post Detail
    private fun onItemSelected() {
        postAdapter.setOnItemClickListener(object : PostAdapter.OnItemClickListener {
            override fun onItemClicked(post: Post) {
                val actionToDetail =
                    ListPostFragmentDirections.actionListPostFragmentToDetailPostFragment(post)
                findNavController().navigate(actionToDetail)
            }
        })
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