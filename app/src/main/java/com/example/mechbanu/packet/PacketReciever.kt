package com.example.mechbanu.packet

import android.util.Log
import com.example.mechbanu.bluetooth.BluetoothConnectService
import com.example.mechbanu.neopixel.DisplayEditor
import com.example.mechbanu.packet.instance.DisplayUpdatePacket
import com.example.mechbanu.utils.sender
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
        val packet = DisplayUpdatePacket(5, 20)
        packet.draw(DisplayEditor.getString(code, value), hashMapOf(
            'a' to DisplayUpdatePacket.Pixel(255, 0, 0),
            'b' to DisplayUpdatePacket.Pixel(255, 255, 0),
            'c' to DisplayUpdatePacket.Pixel(0, 255, 0),
            'd' to DisplayUpdatePacket.Pixel(0, 255, 255),
            'e' to DisplayUpdatePacket.Pixel(0, 0, 255),
            'f' to DisplayUpdatePacket.Pixel(255, 0, 255)
        ))
        sender?.sendPacket(packet)
    }

    private fun op_5() {
        val packet = DisplayUpdatePacket(3, 20)
        packet.draw("" +
                "........................" +
                ".....1............1....." +
                "......1..........1......" +
                ".......1........1......." +
                "........1......1........" +
                ".......1........1......." +
                "......1..........1......" +
                ".....1............1.....", hashMapOf(
                    '1' to DisplayUpdatePacket.Pixel(255, 255, 255)
                )
        )
        packet.setColorGradient(hashMapOf(
            DisplayUpdatePacket.Pixel(255, 255, 255) to DisplayUpdatePacket.Gradient(255, 178, 245, 107, 102, 255)
        ))
        sender?.sendPacket(packet)
    }
}