package com.haura.uialarmapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.haura.uialarmapp.R
import com.haura.uialarmapp.room.Alarm
import kotlinx.android.synthetic.main.item_row_reminder_alarm.view.*

class AlarmAdapter (val alarms : ArrayList<Alarm>) :
    RecyclerView.Adapter<AlarmAdapter.AlarmViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AlarmViewHolder {
        return AlarmViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_row_reminder_alarm, parent, false)
        )
    }

    class AlarmViewHolder (val view : View) : RecyclerView.ViewHolder(view)

    fun setData(list : List<Alarm>){
        alarms.clear()
        alarms.addAll(list)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: AlarmViewHolder, position: Int) {
        val alarm = alarms[position]
        holder.view.item_time_alarm.text = alarm.time
        holder.view.item_date_alarm.text = alarm.date
        holder.view.item_note_alarm.text = alarm.note

    }

    override fun getItemCount() = alarms.size


}