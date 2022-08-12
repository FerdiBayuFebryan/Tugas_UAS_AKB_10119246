package com.ferdibayu.githubuser2
//NAMA                : FERDI BAYU FEBRYAN
//NIM                 : 10119246
//KELAS               : IF-06
//TANGGAL PENGERJAAN  :
//DEKRIPSI            : APLIKASI PENCARI USER GITHUB YANG TERVERFIKASI
import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.View.GONE
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayoutMediator
import com.ferdibayu.githubuser2.adapter.TabsAdapter
import com.ferdibayu.githubuser2.database.UserContract.UserColumns.Companion.AVATAR
import com.ferdibayu.githubuser2.database.UserContract.UserColumns.Companion.Content_Uri
import com.ferdibayu.githubuser2.database.UserContract.UserColumns.Companion.URL
import com.ferdibayu.githubuser2.database.UserContract.UserColumns.Companion.USERNAME
import com.ferdibayu.githubuser2.database.UserHelper
import com.ferdibayu.githubuser2.model.User
import com.ferdibayu.githubuser2.viewmodel.DetailViewModel
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    private lateinit var detailViewModel: DetailViewModel

    companion object {
        const val EXTRA_USER = "extra_user"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        supportActionBar?.title = "Detail Users"

        initTabs()
        initData()

    }

    @SuppressLint("SetTextI18n")
    private fun initData() {
        val user = intent.getParcelableExtra<User>(EXTRA_USER)
        if (user != null) {
            detailViewModel = ViewModelProvider(
                this,
                ViewModelProvider.NewInstanceFactory()
            ).get(DetailViewModel::class.java)
            user.login?.let { detailViewModel.loadUser(applicationContext, it) }
            detailViewModel.getDetailUser.observe(this, Observer {
                Glide.with(applicationContext)
                    .load(it.avatar)
                    .into(iv_photo_detail)
                tv_name.text = it.name
                tv_username.text = "@" + it.login
                tv_bio.text = it.bio
                tv_blog.text = it.blog
                tv_localisation.text = it.location
                tv_followers.text = it.followers.toString() + " Followers"
                tv_following.text = it.following.toString() + " Following"
                tv_repository.text = it.publicRepos.toString() + " Repositories"

                if (it.blog.isNullOrEmpty()) {
                    tv_blog.visibility = GONE
                }
                if (it.location.isNullOrEmpty()) {
                    tv_localisation.visibility = GONE
                }
                if (it.publicRepos.toString().isNullOrEmpty()) {
                    tv_repository.visibility = GONE
                }
                if (it.bio.isNullOrEmpty()) {
                    tv_repository.visibility = GONE
                }
                btn_unliked.visibility = View.VISIBLE
            })
            checkDatabase()
            addToDatabase()
            deleteFromDatabase()
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

    override fun onBackPressed() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        super.onBackPressed()
    }

    private fun checkDatabase() {
        val user = intent.getParcelableExtra<User>(EXTRA_USER)
        val userHelper = UserHelper.getInstance(applicationContext)
        userHelper.open()
        if (userHelper.check(user.id.toString())) {
            btn_liked.visibility = View.VISIBLE
            btn_unliked.visibility = View.INVISIBLE
        }
    }

    private fun addToDatabase() {
        val user = intent.getParcelableExtra<User>(EXTRA_USER)
        btn_unliked.setOnClickListener {
            val values = ContentValues()
            values.put(USERNAME, user.login)
            values.put(AVATAR, user.avatar)
            values.put(URL, user.htmlUrl)
            contentResolver.insert(Content_Uri, values)
            btn_liked.visibility = View.VISIBLE
            btn_unliked.visibility = View.INVISIBLE
        }
    }

    private fun deleteFromDatabase() {
        val user = intent.getParcelableExtra<User>(EXTRA_USER)
        btn_liked.setOnClickListener {
            contentResolver.delete(
                Uri.parse(Content_Uri.toString() + "/" + user.id),
                null,
                null
            )
            btn_liked.visibility = View.INVISIBLE
            btn_unliked.visibility = View.VISIBLE
        }
    }

    private fun initTabs() {
        view_pager.adapter = TabsAdapter(this)
        TabLayoutMediator(
            tabs_layout,
            view_pager,
            TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                when (position) {
                    0 -> tab.text = "Followers"
                    1 -> tab.text = "Following"
                }
            }).attach()
    }
}