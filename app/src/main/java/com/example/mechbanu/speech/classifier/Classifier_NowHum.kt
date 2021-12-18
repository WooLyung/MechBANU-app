package com.example.mechbanu.speech.classifier

import com.example.mechbanu.packet.instance.DataRequestPacket
import com.example.mechbanu.speech.IClassifier
import com.example.mechbanu.utils.sender

class Classifier_NowHum : IClassifier {
    override val speechs = arrayListOf(
        "방습도",
        "방습도알려줘",
        "방습도어때",
        "실내습도",
        "실내습도알려줘",
        "실내습도어때"
    )

    override fun process() {
        sender?.sendPacket(DataRequestPacket(1))
    }
}