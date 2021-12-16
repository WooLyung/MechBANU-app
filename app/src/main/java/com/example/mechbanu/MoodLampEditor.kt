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

            sender?.sendPacket(MoodLampPacket(temp, bright))
        }
    }

    fun off() {
        sender?.sendPacket(MoodLampPacket(0, 0))
    }

    fun brightUp() {
        if (sender != null) {
            val pref = sender!!.service.getSharedPreferences(preference, MODE_PRIVATE)
            val temp = pref.getInt("moodlamp_temp", 3)
            var bright = pref.getInt("moodlamp_bright", 3)

            if (bright != 5) {
                bright++
                val editor = pref.edit()
                editor.putInt("moodlamp_bright", bright)
                editor.commit()
            }

            sender?.sendPacket(MoodLampPacket(temp, bright))
        }
    }

    fun brightDown() {
        if (sender != null) {
            val pref = sender!!.service.getSharedPreferences(preference, MODE_PRIVATE)
            val temp = pref.getInt("moodlamp_temp", 3)
            var bright = pref.getInt("moodlamp_bright", 3)

            if (bright != 0) {
                bright--
                val editor = pref.edit()
                editor.putInt("moodlamp_bright", bright)
                editor.commit()
            }

            sender?.sendPacket(MoodLampPacket(temp, bright))
        }
    }

    fun tempUp() {
        if (sender != null) {
            val pref = sender!!.service.getSharedPreferences(preference, MODE_PRIVATE)
            var temp = pref.getInt("moodlamp_temp", 3)
            val bright = pref.getInt("moodlamp_bright", 3)

            if (temp != 5) {
                temp++
                val editor = pref.edit()
                editor.putInt("moodlamp_temp", temp)
                editor.commit()
            }

            sender?.sendPacket(MoodLampPacket(temp, bright))
        }
    }

    fun tempDown() {
        if (sender != null) {
            val pref = sender!!.service.getSharedPreferences(preference, MODE_PRIVATE)
            var temp = pref.getInt("moodlamp_temp", 3)
            val bright = pref.getInt("moodlamp_bright", 3)

            if (temp != 0) {
                temp--
                val editor = pref.edit()
                editor.putInt("moodlamp_temp", temp)
                editor.commit()
            }

            sender?.sendPacket(MoodLampPacket(temp, bright))
        }
    }
}