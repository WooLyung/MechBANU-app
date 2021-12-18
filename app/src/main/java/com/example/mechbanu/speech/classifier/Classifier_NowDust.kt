package com.example.mechbanu.speech.classifier

import com.example.mechbanu.packet.instance.DataRequestPacket
import com.example.mechbanu.speech.IClassifier
import com.example.mechbanu.utils.sender

class Classifier_NowDust : IClassifier {
    override val speechs = arrayListOf(
        "방먼지",
        "방먼지알려줘",
        "방먼지어때",
        "실내먼지",
        "실내먼지알려줘",
        "실내먼지어때",
        "방뭔지",
        "방뭔지알려줘",
        "방뭔지어때",
        "실내뭔지",
        "실내뭔지알려줘",
        "실내뭔지어때",
        "방미세먼지",
        "방미세먼지알려줘",
        "방미세먼지어때",
        "실내미세먼지",
        "실내미세먼지알려줘",
        "실내미세먼지어때",
        "방미세뭔지",
        "방미세뭔지알려줘",
        "방미세뭔지어때",
        "실내미세뭔지",
        "실내미세뭔지알려줘",
        "실내미세뭔지어때",
    )

    override fun process() {
        sender?.sendPacket(DataRequestPacket(2))
    }
}