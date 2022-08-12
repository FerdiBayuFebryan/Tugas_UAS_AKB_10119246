package com.example.consumerapp.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.consumerapp.fragment.FollowersFragment
import com.example.consumerapp.fragment.FollowingFragment

class TabsAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return FollowersFragment()
            1 -> return FollowingFragment()
        }
        return FollowersFragment()
    }

}