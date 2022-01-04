package com.setianjay.academy.ui.reader.content

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.setianjay.academy.data.ContentEntity
import com.setianjay.academy.databinding.FragmentModuleContentBinding

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
            val content = ContentEntity("<h3 class=\\\\\\\"fr-text-bordered\\\\\\\">Contoh Content</h3><p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>")
            populateWebView(content)
        }
    }

    private fun populateWebView(content: ContentEntity){
        binding?.wbView?.loadData(content.content ?: "", "text/html", "UTF-8")
    }

    companion object{
        val TAG: String = ModuleContentFragment::class.java.simpleName

        fun newInstance(): ModuleContentFragment = ModuleContentFragment()
    }


}