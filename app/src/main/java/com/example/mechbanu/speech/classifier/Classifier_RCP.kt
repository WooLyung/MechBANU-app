package com.example.mechbanu.speech.classifier

import com.example.mechbanu.packet.instance.DisplayUpdatePacket
import com.example.mechbanu.speech.IClassifier
import com.example.mechbanu.utils.sender
import java.util.*

class Classifier_RCP : IClassifier {
    override val speechs = arrayListOf(
        "가위바위보"
    )

    override fun process() {
        val packet = DisplayUpdatePacket(3, 20)
        when (Random().nextInt(3)) {
            0 -> {
                packet.draw("" +
                        "...........1.1.........." +
                        "...........1.1.........." +
                        "...........1.1.........." +
                        "...........1.1.........." +
                        "..........1111.........." +
                        ".........11111.........." +
                        ".........11111.........." +
                        "..........1111..........", hashMapOf(
                    '1' to DisplayUpdatePacket.Pixel(255, 255, 255))
                )
            }
            1 -> {
                packet.draw("" +
                        "........................" +
                        "........................" +
                        "........................" +
                        "..........1111.........." +
                        ".........111111........." +
                        ".........111111........." +
                        ".........111111........." +
                        "..........1111..........", hashMapOf(
                    '1' to DisplayUpdatePacket.Pixel(255, 255, 255))
                )
            }
            2 -> {
                packet.draw("" +
                        "...........11..........." +
                        "..........1111.........." +
                        "..........1111.........." +
                        "..........1111.........." +
                        "..........1111.........." +
                        ".........11111.........." +
                        ".........11111.........." +
                        "..........1111..........", hashMapOf(
                    '1' to DisplayUpdatePacket.Pixel(255, 255, 255))
                )
            }
        }

        packet.setColorGradient(hashMapOf(
            DisplayUpdatePacket.Pixel(255, 255, 255) to DisplayUpdatePacket.Gradient(
                255, 255, 0,
                255, 112, 0
            )
        ))

        sender?.sendPacket(packet)
    }
}