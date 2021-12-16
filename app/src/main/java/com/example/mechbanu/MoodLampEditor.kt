package com.example.mechbanu

import android.content.Context.MODE_PRIVATE
import android.util.Log
import com.example.mechbanu.packet.instance.DataRequestPacket
import com.example.mechbanu.packet.instance.MoodLampPacket
import com.example.mechbanu.utils.sender

object MoodLampEditor {
    val preference = "com.example.mechbanu"

    fun on() {
        if (sender != null) {
            val pref = sender!!.service.getSharedPreferences(preference, MODE_PRIVATE)
            val temp = pref.getInt("moodlamp_temp", 3)
            val bright = pref.getInt("moodlamp_bright", 3)

            Log.i("BANUBANU", "${temp} ${bright}")

            sender?.sendPacket(MoodLampPacket(temp, bright))
        }
    }

    fun off() {
        sender?.sendPacket(MoodLampPacket(0, 0))
    }
}