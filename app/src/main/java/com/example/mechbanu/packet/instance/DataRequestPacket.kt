package com.example.mechbanu.packet.instance

import com.example.mechbanu.packet.IPacket

class DataRequestPacket(var code: Int = 0) : IPacket {
    override val op: Byte
        get() = 0x03

    override fun body(): ByteArray {
        return byteArrayOf(code.toByte())
    }
}