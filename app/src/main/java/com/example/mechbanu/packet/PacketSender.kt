package com.example.mechbanu.packet

import android.widget.Toast
import com.example.mechbanu.bluetooth.BluetoothConnectService
import com.example.mechbanu.speech.classificater.*
import java.lang.Exception

class PacketSender(val service: BluetoothConnectService) {
    val clazz = arrayOf(
        Classificater_NowTemp(),
        Classificater_NowHum(),
        Classificater_NowDust(),
        Classificater_MoodLampOn(),
        Classificater_MoodLampOff(),
        Classificater_MoodLampBrightUp(),
        Classificater_MoodLampBrightDown(),
        Classificater_MoodLampTempUp(),
        Classificater_MoodLampTempDown()
    )

    fun classificate(text: String) {
        try {
            clazz.forEach { inst ->
                if (inst.classificate(text)) {
                    inst.process()
                    return
                }
            }
        }
        catch (e: Exception) {
            Toast.makeText(service.applicationContext, "처리 중에 문제가 발생했어요.", Toast.LENGTH_SHORT).show()
        }
    }

    fun sendPacket(packet: IPacket) {
        service.write(packet.toByteArray())
    }
}