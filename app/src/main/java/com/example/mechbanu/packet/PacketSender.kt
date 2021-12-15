package com.example.mechbanu.packet

import android.widget.Toast
import com.example.mechbanu.bluetooth.BluetoothConnectService
import com.example.mechbanu.speech.IClassificater
import com.example.mechbanu.speech.classificater.Classificater_NowTemp
import java.lang.Exception

class PacketSender(val service: BluetoothConnectService) {
    val clazz = arrayOf(
        Classificater_NowTemp()
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