package com.example.mechbanu.speech.classificater

import com.example.mechbanu.packet.instance.DataRequestPacket
import com.example.mechbanu.speech.IClassificater
import com.example.mechbanu.utils.sender

class Classificater_NowDust : IClassificater {
    override val speechs = arrayListOf(
        "지금먼지",
        "지금먼지알려줘",
        "지금먼지어때",
        "지금뭔지",
        "지금뭔지알려줘",
        "지금뭔지어때"
    )

    override fun process() {
        sender?.sendPacket(DataRequestPacket(2))
    }
}