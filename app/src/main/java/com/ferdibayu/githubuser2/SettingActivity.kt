package com.ferdibayu.githubuser2
//NAMA                : FERDI BAYU FEBRYAN
//NIM                 : 10119246
//KELAS               : IF-06
//TANGGAL PENGERJAAN  :
//DEKRIPSI            : APLIKASI PENCARI USER GITHUB YANG TERVERFIKASI
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager
import com.ferdibayu.githubuser2.receiver.AlarmReceiver
import kotlinx.android.synthetic.main.activity_setting.*
import java.util.*

class SettingActivity : AppCompatActivity() {

    companion object {
        private val TAG = SettingActivity::class.java.simpleName
    }

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Settings"

        val switch = findViewById<Switch>(R.id.switch_reminder)
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        val dailyReminder = sharedPreferences.getInt("user_reminder", 0)

        switch.isChecked = dailyReminder == 1

        switch?.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                setReminderOn(this)
                val edit = sharedPreferences.edit()
                edit.putInt("user_reminder", 1)
                edit.commit()
                Toast.makeText(this, "Reminder On", Toast.LENGTH_SHORT).show()
            } else {
                setReminderOff(this)
                val edit = sharedPreferences.edit()
                edit.putInt("user_reminder", 0)
                edit.commit()
                Toast.makeText(this, "Reminder Off", Toast.LENGTH_SHORT).show()
            }
        }
        tv_change_language.setOnClickListener {
            val intent = Intent(Settings.ACTION_LOCALE_SETTINGS)
            startActivity(intent)
        }
    }

    private fun setReminderOn(context: Context) {
        val intent = Intent(context, AlarmReceiver::class.java)
        val calendar = Calendar.getInstance()

        calendar.set(Calendar.HOUR_OF_DAY, 9)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)

        val reminderManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            102,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        Log.d(TAG, "SwitchOn")
        reminderManager.setRepeating(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            AlarmManager.INTERVAL_DAY,
            pendingIntent
        )

    }

    private fun setReminderOff(context: Context) {
        val intent = Intent(context, AlarmReceiver::class.java)

        val reminderManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            102,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        Log.d(TAG, "SwitfOff")
        reminderManager.cancel(pendingIntent)
    }
}