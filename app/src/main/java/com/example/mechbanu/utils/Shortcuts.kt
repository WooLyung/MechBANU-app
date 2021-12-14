package com.example.mechbanu.utils

import com.example.mechbanu.bluetooth.BluetoothConnectService
import com.example.mechbanu.packet.PacketReciever
import com.example.mechbanu.packet.PacketSender

val sender: PacketSender?
    get() = BluetoothConnectService.instance?.sender

val reciever: PacketReciever?
    get() = BluetoothConnectService.instance?.reciever