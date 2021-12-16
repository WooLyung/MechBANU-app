package com.example.mechbanu.speech.classificater

import com.example.mechbanu.packet.instance.DisplayUpdatePacket
import com.example.mechbanu.speech.IClassificater
import com.example.mechbanu.utils.sender

class Classificater_Unknown : IClassificater {
    override val speechs = arrayListOf(
        ""
    )

    override fun process() {
        val packet = DisplayUpdatePacket(3, 20)
        packet.draw("" +
                "........................" +
                "...........111.........." +
                "..........1...1........." +
                "..............1........." +
                "............11.........." +
                "............1..........." +
                "........................" +
                "............1...........", hashMapOf(
            '1' to DisplayUpdatePacket.Pixel(255, 255, 255))
        )
        packet.setColorGradient(hashMapOf(
            DisplayUpdatePacket.Pixel(255, 255, 255) to DisplayUpdatePacket.Gradient(
                255, 223, 36,
                255, 94, 0
            )
        ))

        sender?.sendPacket(packet)
    }

    override fun classificate(text: String): Boolean {
        return true
    }
}