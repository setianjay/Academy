package com.setianjay.academy.ui.bookmark

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ShareCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.setianjay.academy.R
import com.setianjay.academy.adapter.BookmarkAdapter
import com.setianjay.academy.data.CourseEntity
import com.setianjay.academy.databinding.FragmentBookmarkBinding
import com.setianjay.academy.ui.viewmodelfactory.ViewModelFactory

class BookmarkFragment : Fragment(), BookmarkAdapter.IOnBookmarkAdapter {
    private var _binding: FragmentBookmarkBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentBookmarkBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecycleView()
    }

    private fun setupRecycleView() {
        val bookmarkAdapter = BookmarkAdapter(this)

        val factory = ViewModelFactory.getInstance(requireActivity())
        val viewModel = ViewModelProvider(this, factory)[BookmarkViewModel::class.java]

        binding?.progressBar?.visibility = View.VISIBLE
        viewModel.getBookmarks().observe(viewLifecycleOwner) { bookmarked ->
            binding?.progressBar?.visibility = View.GONE
            bookmarkAdapter.apply {
                setAcademies(bookmarked)
                notifyDataSetChanged()
            }
        }


        binding?.rvBookmark?.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = bookmarkAdapter
            setHasFixedSize(true)
        }
    }

    override fun onShareClick(academy: CourseEntity) {
        if (activity != null) {
            val mimeType = "text/plain"
            ShareCompat.IntentBuilder
                .from(requireActivity())
                .setType(mimeType)
                .setChooserTitle("Bagikan aplikasi ini sekarang")
                .setText(resources.getString(R.string.share_text, academy.title))
                .startChooser()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}