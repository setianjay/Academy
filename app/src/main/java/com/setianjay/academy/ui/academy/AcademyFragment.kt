package com.setianjay.academy.ui.academy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.setianjay.academy.adapter.AcademyAdapter
import com.setianjay.academy.databinding.FragmentAcademyBinding
import com.setianjay.academy.ui.viewmodelfactory.ViewModelFactory


class AcademyFragment : Fragment() {
    private var _binding: FragmentAcademyBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAcademyBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecycleView()
    }

    private fun setupRecycleView(){
        if (activity != null){
            val academyAdapter = AcademyAdapter()

            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[AcademyViewModel::class.java]

            binding?.progressBar?.visibility = View.VISIBLE
            viewModel.getCourses().observe(viewLifecycleOwner){ courses ->
                binding?.progressBar?.visibility = View.GONE
                academyAdapter.apply {
                    setAcademies(courses)
                    notifyDataSetChanged()
                }
            }

            binding?.rvAcademy?.apply{
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = academyAdapter
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}