package com.haura.uialarmapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.haura.uialarmapp.databinding.ActivityMainBinding
import com.haura.uialarmapp.room.Alarm
import com.haura.uialarmapp.room.AlarmDB
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_one_time_alarm.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    val db by lazy { AlarmDB (this) }
    lateinit var alarmAdapter : AlarmAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initAlarmType()
        initTimeToday()
        initDateToday()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        alarmAdapter = AlarmAdapter(arrayListOf())
        rv_reminder_alarm.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = alarmAdapter
        }
    }

    private fun initAlarmType() {
        view_set_one_time_alarm.setOnClickListener{
            startActivity(Intent(this, OneTimeAlarmActivity::class.java))
        }

        view_set_repeating_alarm.setOnClickListener{
            startActivity(Intent(this, RepeatingAlarmActivity::class.java))
        }
    }

    private fun initTimeToday() {

        val timeNow = Calendar.getInstance()
        val timeFormat = SimpleDateFormat("HH:mm")
        val formattedTime = timeFormat.format(timeNow.time)

        tv_time_today.text = formattedTime
    }

    private fun initDateToday() {
        val dateNow : Date = Calendar.getInstance().time
        val dateFormat = SimpleDateFormat("E, dd MMM yyyy", Locale.getDefault())
        val formattedDate: String = dateFormat.format(dateNow)

        tv_date_today.text = formattedDate

    }

    override fun onStart() {
        super.onStart()
        CoroutineScope(Dispatchers.IO).launch {
            val alarms = db.alarmDao().getAlarm()
            Log.d("MainActivity", "dbresponse: $alarms")
            withContext(Dispatchers.Main){
                alarmAdapter.setData(alarms)
            }
        }
    }
}