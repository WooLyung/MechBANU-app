package com.example.mechbanu.packet

interface IPacket {
    val length: Int
        get() = body().size

    val op: Byte

    private fun head() : ByteArray {
        return byteArrayOf(0x48, op, (length / 16).toByte(), (length % 16).toByte())
    }

    fun toByteArray() : ByteArray {
        return head() + body()
    }

    fun body() : ByteArray
}