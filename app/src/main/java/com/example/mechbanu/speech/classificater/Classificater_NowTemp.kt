package com.example.mechbanu.speech.classificater

import com.example.mechbanu.packet.instance.DataRequestPacket
import com.example.mechbanu.speech.IClassificater
import com.example.mechbanu.utils.sender

class Classificater_NowTemp : IClassificater {
    override val speechs = arrayListOf(
        "지금 온도",
        "지금 온도 알려줘",
        "지금 온도 어때"
    )

    override fun process() {
        sender?.sendPacket(DataRequestPacket(0))
    }
}