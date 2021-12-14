package com.example.mechbanu.packet.instance

import com.example.mechbanu.packet.IPacket

class PingPacket : IPacket {
    override val op: Byte
        get() = 0x00

    override fun body(): ByteArray {
        return byteArrayOf()
    }
}