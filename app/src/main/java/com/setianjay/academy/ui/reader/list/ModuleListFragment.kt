package com.setianjay.academy.ui.reader.list

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.setianjay.academy.adapter.ModuleListAdapter
import com.setianjay.academy.adapter.MyAdapterClickListener
import com.setianjay.academy.data.ModuleEntity
import com.setianjay.academy.databinding.FragmentModuleListBinding
import com.setianjay.academy.ui.reader.CourseReaderActivity
import com.setianjay.academy.ui.reader.CourseReaderCallback
import com.setianjay.academy.ui.reader.CourseReaderViewModel

class ModuleListFragment : Fragment(), MyAdapterClickListener {
    private var _binding: FragmentModuleListBinding? = null
    private val binding get() = _binding

    private lateinit var adapter: ModuleListAdapter
    private lateinit var callback: CourseReaderCallback
    private lateinit var viewModel: CourseReaderViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = context as CourseReaderActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentModuleListBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[CourseReaderViewModel::class.java]
        adapter = ModuleListAdapter(this)

        binding?.progressBar?.visibility = View.VISIBLE
        viewModel.getModules().observe(viewLifecycleOwner) { modules ->
            populateRecycleView(modules)
        }
    }

    private fun populateRecycleView(modules: List<ModuleEntity>) {
        binding?.apply {
            progressBar.visibility = View.GONE
            adapter.setModules(modules)
            rvModule.layoutManager = LinearLayoutManager(context)
            rvModule.setHasFixedSize(true)
            rvModule.adapter = adapter
            val dividerItemDecoration =
                DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
            rvModule.addItemDecoration(dividerItemDecoration)
        }
    }

    override fun onItemClicked(position: Int, moduleId: String) {
        callback.moveTo(position, moduleId)
        viewModel.selectedModule(moduleId)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        val TAG: String = ModuleListFragment::class.java.simpleName

        fun newInstance(): ModuleListFragment = ModuleListFragment()
    }
}