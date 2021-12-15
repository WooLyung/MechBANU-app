package com.example.mechbanu.speech.classificater

import com.example.mechbanu.packet.instance.DataRequestPacket
import com.example.mechbanu.speech.IClassificater
import com.example.mechbanu.utils.sender

class Classificater_NowDust : IClassificater {
    override val speechs = arrayListOf(
        "지금 먼지",
        "지금 먼지 알려줘",
        "지금 먼지 어때"
    )

    override fun process() {
        sender?.sendPacket(DataRequestPacket(2))
    }
}