package com.ferdibayu.githubuser2.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ferdibayu.githubuser2.R
import com.ferdibayu.githubuser2.model.detailuser.DetailsUsers
import com.ferdibayu.githubuser2.util.RetroConfig
import retrofit2.Call
import retrofit2.Response

class DetailViewModel : ViewModel() {

    private val userDetail: MutableLiveData<DetailsUsers> = MutableLiveData()
    val getDetailUser: LiveData<DetailsUsers> = userDetail

    fun loadUser(context: Context, username: String) {
        RetroConfig.getRetroClientInstance().getDetailUsers(username)
            .enqueue(object : retrofit2.Callback<DetailsUsers> {
                override fun onResponse(
                    call: Call<DetailsUsers>,
                    response: Response<DetailsUsers>
                ) {
                    if (response.body() != null) {
                        userDetail.value = response.body()
                    }
                }

                override fun onFailure(call: Call<DetailsUsers>, t: Throwable) {
                    Toast.makeText(context, R.string.tidak_ada_koneksi, Toast.LENGTH_SHORT).show()
                }
            })
    }
}