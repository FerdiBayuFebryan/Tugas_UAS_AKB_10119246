 package com.ferdibayu.githubuser2
//NAMA                : FERDI BAYU FEBRYAN
//NIM                 : 10119246
//KELAS               : IF-06
//TANGGAL PENGERJAAN  :
//DEKRIPSI            : APLIKASI PENCARI USER GITHUB YANG TERVERFIKASI
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ferdibayu.githubuser2.adapter.UserAdapter
import com.ferdibayu.githubuser2.model.User
import com.ferdibayu.githubuser2.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel
    private var dataUser: ArrayList<User> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val actionBar = supportActionBar
        actionBar!!.title = "Github Users"

        showLogo(true)


        sv_user.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                if (query.isNotEmpty()) {
                    dataUser.clear()
                    mainViewModel = ViewModelProvider(
                        this@MainActivity,
                        ViewModelProvider.NewInstanceFactory()
                    ).get(MainViewModel::class.java)
                    mainViewModel.loadUser(this@MainActivity, query)
                    showLogo(false)
                    mainViewModel.getListUser.observe(this@MainActivity, Observer {
                        rv_main.adapter = UserAdapter(applicationContext, it as ArrayList<User>)
                    })
                    rv_main.layoutManager = LinearLayoutManager(this@MainActivity)
                } else {
                    return true
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_fav -> {
                val intent = Intent(this, FavouriteActivity::class.java)
                startActivity(intent)
            }
            R.id.action_setting -> {
                val intent = Intent(this, SettingActivity::class.java)
                startActivity(intent)
            }
        }


        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        finish()
    }

    private fun showLogo(state: Boolean) {
        if (state) {
            iv_logo.visibility = View.VISIBLE
            tv_main.visibility = View.VISIBLE
        } else {
            iv_logo.visibility = View.GONE
            tv_main.visibility = View.GONE
        }
    }

}