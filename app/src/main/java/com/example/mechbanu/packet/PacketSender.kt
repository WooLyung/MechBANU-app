package com.example.mechbanu.packet

import android.util.Log
import android.widget.Toast
import com.example.mechbanu.bluetooth.BluetoothConnectService
import com.example.mechbanu.speech.classifier.*
import java.lang.Exception

class PacketSender(val service: BluetoothConnectService) {
    val clazz = arrayOf(
        Classifier_NowTemp(),
        Classifier_NowHum(),
        Classifier_NowDust(),
        Classifier_MoodLampOn(),
        Classifier_MoodLampOff(),
        Classifier_MoodLampBrightUp(),
        Classifier_MoodLampBrightDown(),
        Classifier_MoodLampTempUp(),
        Classifier_MoodLampTempDown(),
        Classifier_Smile(),
        Classifier_TodayTemp(),
        Classifier_TodayHum(),
        Classifier_TodayDust(),
        Classifier_TodayWeather(),
        Classifier_Unknown()
    )

    fun classify(text: String) {
        try {
            clazz.forEach { inst ->
                if (inst.classify(text)) {
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