package com.example.mechbanu.speech.classifier

import com.example.mechbanu.packet.instance.DataRequestPacket
import com.example.mechbanu.speech.IClassifier
import com.example.mechbanu.utils.sender

class Classifier_NowTemp : IClassifier {
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