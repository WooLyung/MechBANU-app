package com.example.mechbanu.packet

import com.example.mechbanu.bluetooth.BluetoothConnectService

class PacketSender(val service: BluetoothConnectService) {
    fun classification(text: String) {

    }

    fun sendPacket(packet: IPacket) {
        service.write(packet.toByteArray())
    }
}