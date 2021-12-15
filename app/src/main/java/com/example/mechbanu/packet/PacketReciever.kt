package com.example.mechbanu.packet

import android.util.Log
import com.example.mechbanu.bluetooth.BluetoothConnectService
import java.nio.ByteBuffer

class PacketReciever(val service: BluetoothConnectService) {
    fun readPacket(buffer: ByteArray) {
        val H = buffer[0]
        val op = buffer[1]
        val len = buffer[2] * 16 + buffer[3]

        if (H == 'H'.toByte()) {
            when (op) {
                (0x04).toByte() -> op_4(buffer[4].toInt(), byteArrayOf(buffer[8], buffer[7], buffer[6], buffer[5]))
                (0x05).toByte() -> op_5()
            }
        }
    }

    private fun op_4(code: Int, data: ByteArray) {
        val value = ByteBuffer.wrap(data).getFloat()

        Log.i("BANUBANU", "${code} ${value} 데이터 읽어!")
    }

    private fun op_5() {
        Log.i("BANUBANU", "쓰다듬기!")
    }
}