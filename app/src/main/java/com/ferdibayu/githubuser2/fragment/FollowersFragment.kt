package com.ferdibayu.githubuser2.fragment
//NAMA                : FERDI BAYU FEBRYAN
//NIM                 : 10119246
//KELAS               : IF-06
//TANGGAL PENGERJAAN  :
//DEKRIPSI            : APLIKASI PENCARI USER GITHUB YANG TERVERFIKASI
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ferdibayu.githubuser2.DetailActivity
import com.ferdibayu.githubuser2.R
import com.ferdibayu.githubuser2.adapter.UserAdapter
import com.ferdibayu.githubuser2.model.User
import com.ferdibayu.githubuser2.viewmodel.FollowViewModel
import kotlinx.android.synthetic.main.fragment_followers.*


class FollowersFragment : Fragment() {

    private lateinit var followViewModel: FollowViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        showLoading(true)
        setFollowersData()



        super.onViewCreated(view, savedInstanceState)
    }

    private fun setFollowersData() {
        val user = activity?.intent?.getParcelableExtra<User>(DetailActivity.EXTRA_USER)

        if (user != null) {
            followViewModel = ViewModelProvider(
                this,
                ViewModelProvider.NewInstanceFactory()
            ).get(FollowViewModel::class.java)
            followViewModel.loadFollowers(context, user.login, "0")
            followViewModel.getListFollowers.observe(viewLifecycleOwner, Observer {
                rv_followers_fragment.adapter = UserAdapter(context, it)
                showLoading(false)
                if (it.isEmpty()) {
                    tv_followers_empty.visibility = View.VISIBLE
                }
            })
        }
        rv_followers_fragment.layoutManager = LinearLayoutManager(context)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_followers, container, false)
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            pb_followers_fragment.visibility = View.VISIBLE
        } else {
            pb_followers_fragment.visibility = View.GONE
        }
    }
}