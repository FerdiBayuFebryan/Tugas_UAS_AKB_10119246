package com.ferdibayu.githubuser2.adapter
//NAMA                : FERDI BAYU FEBRYAN
//NIM                 : 10119246
//KELAS               : IF-06
//TANGGAL PENGERJAAN  :
//DEKRIPSI            : APLIKASI PENCARI USER GITHUB YANG TERVERFIKASI
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ferdibayu.githubuser2.fragment.FollowersFragment
import com.ferdibayu.githubuser2.fragment.FollowingFragment

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