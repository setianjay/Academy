package com.setianjay.academy.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.tabs.TabLayoutMediator
import com.setianjay.academy.adapter.SectionHomePagerAdapter
import com.setianjay.academy.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private var _binding: ActivityHomeBinding? = null
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        supportActionBar?.elevation = 0f
        setupTabLayout()
    }

    private fun setupTabLayout(){
        val sectionHomePagerAdapter = SectionHomePagerAdapter(supportFragmentManager, lifecycle)
        binding?.vwPager?.adapter = sectionHomePagerAdapter

        binding?.tbLayout?.let { tabLayout ->
            binding?.vwPager?.let { viewPager2 ->
                TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
                    val title = SectionHomePagerAdapter.getTitles(this)[position]
                    tab.text = title
                }.attach()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}