package com.example.mechbanu.speech.classificater

import com.example.mechbanu.packet.instance.DataRequestPacket
import com.example.mechbanu.speech.IClassificater
import com.example.mechbanu.utils.sender

class Classificater_NowTemp : IClassificater {
    override val speechs = arrayListOf(
        "방온도",
        "방온도알려줘",
        "방온도어때",
        "실내온도",
        "실내온도알려줘",
        "실내온도어때"
    )

    override fun process() {
        sender?.sendPacket(DataRequestPacket(0))
    }
}