package com.example.mechbanu.speech.classificater

import com.example.mechbanu.packet.instance.DataRequestPacket
import com.example.mechbanu.speech.IClassificater
import com.example.mechbanu.utils.sender

class Classificater_NowDust : IClassificater {
    override val speechs = arrayListOf(
        "방먼지",
        "방먼지알려줘",
        "방먼지어때",
        "실내먼지",
        "실내먼지알려줘",
        "실내먼지어때",
        "방뭔지",
        "방뭔지알려줘",
        "방뭔지어때"
    )

    override fun process() {
        sender?.sendPacket(DataRequestPacket(2))
    }
}