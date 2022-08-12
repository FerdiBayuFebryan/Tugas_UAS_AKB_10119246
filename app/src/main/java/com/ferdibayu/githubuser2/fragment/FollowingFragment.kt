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
import kotlinx.android.synthetic.main.fragment_following.*

class FollowingFragment : Fragment() {

    private lateinit var followViewModel: FollowViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        showLoading(true)
        setFollowingData()

        super.onViewCreated(view, savedInstanceState)
    }

    private fun setFollowingData() {
        val user = activity?.intent?.getParcelableExtra<User>(DetailActivity.EXTRA_USER)

        if (user != null) {
            followViewModel = ViewModelProvider(
                this,
                ViewModelProvider.NewInstanceFactory()
            ).get(FollowViewModel::class.java)
            followViewModel.loadFollowing(context, user.login, "1")
            followViewModel.getListFollowing.observe(viewLifecycleOwner, Observer {
                rv_following_fragment.adapter = UserAdapter(context, it)
                showLoading(false)
                if (it.isEmpty()) {
                    tv_following_empty.visibility = View.VISIBLE
                }
            })
        }
        rv_following_fragment.layoutManager = LinearLayoutManager(context)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_following, container, false)
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            pb_following_fragment.visibility = View.VISIBLE
        } else {
            pb_following_fragment.visibility = View.GONE
        }
    }
}