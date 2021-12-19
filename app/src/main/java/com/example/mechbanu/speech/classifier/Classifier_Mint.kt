package com.example.mechbanu.speech.classifier

import com.example.mechbanu.packet.instance.DisplayUpdatePacket
import com.example.mechbanu.speech.IClassifier
import com.example.mechbanu.utils.sender

class Classifier_Mint : IClassifier {
    override val speechs = arrayListOf(
        "민트초코좋아해",
        "민트좋아해",
        "민트초코좋아",
        "민트좋아",
        "민초좋아해",
        "민초좋아",
        "민태초코좋아해",
        "민태좋아해",
        "민태초코좋아",
        "민태좋아",
        "민채좋아해",
        "민채좋아"
    )

    override fun process() {
        val packet = DisplayUpdatePacket(3, 20)
        packet.draw("" +
                "........................" +
                "........................" +
                "....11............11...." +
                "......11........11......" +
                "........11....11........" +
                ".........1....1........." +
                "........................" +
                "........................", hashMapOf(
            '1' to DisplayUpdatePacket.Pixel(255, 255, 255))
        )
        packet.setColorGradient(hashMapOf(
            DisplayUpdatePacket.Pixel(255, 255, 255) to DisplayUpdatePacket.Gradient(
                255, 187, 0,
                152, 50, 0
            )
        ))

        sender?.sendPacket(packet)
    }
}