package com.setianjay.academy.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.setianjay.academy.R
import com.setianjay.academy.ui.academy.AcademyFragment
import com.setianjay.academy.ui.bookmark.BookmarkFragment
import com.setianjay.academy.utils.TextUtils

class SectionHomePagerAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    private val fragments: ArrayList<Fragment> = arrayListOf(
        AcademyFragment(),
        BookmarkFragment()
    )

    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }

    companion object {
        fun getTitles(context: Context): List<String> {
            return arrayListOf(
                TextUtils.getStringFromRes(context, R.string.home),
                TextUtils.getStringFromRes(context, R.string.bookmark)
            )
        }
    }
}