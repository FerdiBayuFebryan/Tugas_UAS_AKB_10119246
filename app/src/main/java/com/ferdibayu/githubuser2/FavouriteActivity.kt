package com.ferdibayu.githubuser2

import android.content.Intent
import android.database.ContentObserver
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.ferdibayu.githubuser2.adapter.UserAdapter
import com.ferdibayu.githubuser2.database.UserContract.UserColumns.Companion.Content_Uri
import com.ferdibayu.githubuser2.helper.MappingHelper
import kotlinx.android.synthetic.main.activity_favourite.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class FavouriteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favourite)

        supportActionBar?.title = "Favourites"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val handleThread = HandlerThread("DataObserver")
        handleThread.start()
        val handler = Handler(handleThread.looper)
        val myObserver = object : ContentObserver(handler) {
            override fun onChange(selfChange: Boolean) {
                loadUserAsync()
            }
        }
        contentResolver.registerContentObserver(Content_Uri, true, myObserver)

        if (savedInstanceState == null) {
            loadUserAsync()
        }
        rv_favourite.layoutManager = LinearLayoutManager(this)

    }

    private fun loadUserAsync() {
        GlobalScope.launch(Dispatchers.Main) {
            val deferredUser = async(Dispatchers.IO) {
                val cursor = contentResolver.query(Content_Uri, null, null, null, null)
                MappingHelper.mapCursorToArrayList(cursor)
            }
            val user = deferredUser.await()
            if (user.isNotEmpty()) {
                rv_favourite.adapter = UserAdapter(applicationContext, user)
            } else {
                iv_data_not_found.visibility = View.VISIBLE
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)

        val item = menu?.findItem(R.id.action_fav)
        item?.isVisible = false
        this.invalidateOptionsMenu()

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_setting -> {
                val intent = Intent(this, SettingActivity::class.java)
                startActivity(intent)
            }
        }


        return super.onOptionsItemSelected(item)
    }
}