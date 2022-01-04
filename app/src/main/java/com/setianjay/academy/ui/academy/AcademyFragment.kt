package com.setianjay.academy.ui.academy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.setianjay.academy.adapter.AcademyAdapter
import com.setianjay.academy.data.CourseEntity
import com.setianjay.academy.databinding.FragmentAcademyBinding
import com.setianjay.academy.utils.DataDummy


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
            val academies: List<CourseEntity> = DataDummy.generateDummyCourses()
            val academyAdapter = AcademyAdapter().apply {
                this.setAcademies(academies)
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