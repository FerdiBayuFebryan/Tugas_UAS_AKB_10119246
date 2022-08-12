package com.ferdibayu.githubuser2.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ferdibayu.githubuser2.R
import com.ferdibayu.githubuser2.model.User
import com.ferdibayu.githubuser2.util.RetroConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowViewModel : ViewModel() {
    private val listFollowers: MutableLiveData<List<User>> = MutableLiveData()
    val getListFollowers: LiveData<List<User>> = listFollowers

    private val listFollowing: MutableLiveData<List<User>> = MutableLiveData()
    val getListFollowing: LiveData<List<User>> = listFollowing

    fun loadFollowers(context: Context?, username: String?, page: String) {
        if (username != null) {
            RetroConfig.getRetroClientInstance().getFollowersData(username, page)
                .enqueue(object : Callback<List<User>> {
                    override fun onResponse(
                        call: Call<List<User>>,
                        response: Response<List<User>>
                    ) {
                        listFollowers.value = response.body()
                    }

                    override fun onFailure(call: Call<List<User>>, t: Throwable) {
                        Toast.makeText(context, R.string.tidak_ada_koneksi, Toast.LENGTH_SHORT)
                            .show()
                    }

                })
        }
    }

    fun loadFollowing(context: Context?, username: String?, page: String) {
        if (username != null) {
            RetroConfig.getRetroClientInstance().getFollowingData(username, page)
                .enqueue(object : Callback<List<User>> {
                    override fun onResponse(
                        call: Call<List<User>>,
                        response: Response<List<User>>
                    ) {
                        listFollowing.value = response.body()
                    }

                    override fun onFailure(call: Call<List<User>>, t: Throwable) {
                        Toast.makeText(context, R.string.tidak_ada_koneksi, Toast.LENGTH_SHORT)
                            .show()
                    }

                })
        }
    }


}