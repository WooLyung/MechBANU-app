package com.example.mechbanu.speech.classifier

import com.example.mechbanu.packet.instance.DisplayUpdatePacket
import com.example.mechbanu.speech.IClassifier
import com.example.mechbanu.utils.sender

class Classifier_Smile : IClassifier {
    override val speechs = arrayListOf(
        "안녕",
        "반가워",
        "잘지냈니"
    )

    override fun process() {
        val packet = DisplayUpdatePacket(3, 20)
        packet.draw("" +
                "........................" +
                "........................" +
                "......1..........1......" +
                ".....1.1........1.1....." +
                "....1...1......1...1...." +
                "........................" +
                "........................" +
                "........................", hashMapOf(
            '1' to DisplayUpdatePacket.Pixel(255, 255, 255))
        )
        packet.setColorGradient(hashMapOf(
            DisplayUpdatePacket.Pixel(255, 255, 255) to DisplayUpdatePacket.Gradient(
                92, 209, 229,
                67, 117, 217
            )
        ))

        sender?.sendPacket(packet)
    }
}