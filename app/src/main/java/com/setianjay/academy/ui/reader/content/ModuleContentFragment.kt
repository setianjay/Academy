package com.setianjay.academy.ui.reader.content

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.setianjay.academy.data.ModuleEntity
import com.setianjay.academy.databinding.FragmentModuleContentBinding
import com.setianjay.academy.ui.reader.CourseReaderViewModel

class ModuleContentFragment : Fragment() {
    private var _binding: FragmentModuleContentBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentModuleContentBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null){
            val viewModel = ViewModelProvider(requireActivity())[CourseReaderViewModel::class.java]
            val content = viewModel.getSelectedModule()
            populateWebView(content)
        }
    }

    private fun populateWebView(content: ModuleEntity){
        binding?.wbView?.loadData(content.contentEntity?.content ?: "", "text/html", "UTF-8")
    }

    companion object{
        val TAG: String = ModuleContentFragment::class.java.simpleName

        fun newInstance(): ModuleContentFragment = ModuleContentFragment()
    }


}