package com.example.fragmentstudypractice2

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class PagerAdapter(hostFragment: Fragment) : FragmentStateAdapter(hostFragment) {
    override fun getItemCount(): Int {
        return 4
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> NestedFragmentA()
            else -> NestedFragmentB()
        }
    }

}