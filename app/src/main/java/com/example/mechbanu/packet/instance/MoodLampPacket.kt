package com.example.mechbanu.packet.instance

import com.example.mechbanu.packet.IPacket

class MoodLampPacket(val temp: Int, val bright: Int) : IPacket {
    override val op: Byte
        get() = 0x02

    override fun body(): ByteArray {
        var body = byteArrayOf((bright * 15).toByte())

        for (i in 0..29) {
            when (temp) {
                1 -> body += byteArrayOf(255.toByte(), (60 + i * 255 / 195).toByte(), (i * 255 / 29).toByte())
                2 -> body += byteArrayOf(255.toByte(), (80 + i * 175 / 29).toByte(), (i * 255 / 29).toByte())
                3 -> body += byteArrayOf(255.toByte(), 255.toByte(), (i * 255 / 29).toByte())
                4 -> body += byteArrayOf((150 + i * 105 / 29).toByte(), 225.toByte(), (30 + i * 225 / 29).toByte())
                5 -> body += byteArrayOf((80 + i * 145 / 29).toByte(), 225.toByte(), (70 + i * 185 / 29).toByte())
                else -> body += byteArrayOf(0.toByte(), 0.toByte(), 0.toByte())
            }
        }

        return body
    }
}