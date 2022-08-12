package com.ferdibayu.githubuser2.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ferdibayu.githubuser2.R
import com.ferdibayu.githubuser2.model.ResponseUser
import com.ferdibayu.githubuser2.model.User
import com.ferdibayu.githubuser2.util.RetroConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    private val listUser: MutableLiveData<List<User>> = MutableLiveData()
    val getListUser: LiveData<List<User>> = listUser

    fun loadUser(context: Context, query: String) {
        RetroConfig.getRetroClientInstance().getSearchData(query)
            .enqueue(object : Callback<ResponseUser> {
                override fun onResponse(
                    call: Call<ResponseUser>,
                    response: Response<ResponseUser>
                ) {
                    if (response.body() != null) {
                        listUser.postValue(response.body()?.items)
                    }
                }

                override fun onFailure(call: Call<ResponseUser>, t: Throwable) {
                    Toast.makeText(context, R.string.tidak_ada_koneksi, Toast.LENGTH_SHORT).show()
                }

            })
    }
}