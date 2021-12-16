package com.example.mechbanu.packet.instance

import com.example.mechbanu.packet.IPacket

class MoodLampPacket(val temp: Int, val bright: Int) : IPacket {
    override val op: Byte
        get() = 0x02

    override fun body(): ByteArray {
        var body = byteArrayOf((bright * 20).toByte())

        for (i in 0..29) {
            body += byteArrayOf((temp * 10).toByte(), (temp * 10).toByte(), 0)
        }

        return body
    }
}