package com.example.mechbanu.neopixel

import android.util.Log
import com.example.mechbanu.packet.instance.DisplayUpdatePacket
import com.example.mechbanu.utils.sender
import java.lang.Math.abs

object DisplayEditor {
    fun sendTempPacket(data: Float) {
        val packet = DisplayUpdatePacket(5, 20)
        packet.draw(getString(0, data), hashMapOf(
            'a' to DisplayUpdatePacket.Pixel(255, 255, 255),
            'b' to DisplayUpdatePacket.Pixel(255, 255, 255),
            'c' to DisplayUpdatePacket.Pixel(255, 255, 255),
            'd' to DisplayUpdatePacket.Pixel(255, 255, 255),
            'e' to DisplayUpdatePacket.Pixel(255, 255, 255),
            'f' to DisplayUpdatePacket.Pixel(255, 255, 255)
        ))
        packet.setColorGradient(hashMapOf(
            DisplayUpdatePacket.Pixel(255, 255, 255) to
            DisplayUpdatePacket.Gradient(
                173, 10, 33,
                244, 61, 6)
        ))
        sender?.sendPacket(packet)
    }

    fun sendHumPacket(data: Float) {
        val packet = DisplayUpdatePacket(5, 20)
        packet.draw(getString(1, data), hashMapOf(
            'a' to DisplayUpdatePacket.Pixel(255, 255, 255),
            'b' to DisplayUpdatePacket.Pixel(255, 255, 255),
            'c' to DisplayUpdatePacket.Pixel(255, 255, 255),
            'd' to DisplayUpdatePacket.Pixel(255, 255, 255),
            'e' to DisplayUpdatePacket.Pixel(255, 255, 255),
            'f' to DisplayUpdatePacket.Pixel(255, 255, 255)
        ))
        packet.setColorGradient(hashMapOf(
            DisplayUpdatePacket.Pixel(255, 255, 255) to
                    DisplayUpdatePacket.Gradient(
                        178, 235, 244,
                        0, 84, 255)
        ))
        sender?.sendPacket(packet)
    }

    fun sendDustPacket(data: Float) {
        val packet = DisplayUpdatePacket(5, 20)
        packet.draw(getString(2, data), hashMapOf(
            'a' to DisplayUpdatePacket.Pixel(255, 255, 255),
            'b' to DisplayUpdatePacket.Pixel(255, 255, 255),
            'c' to DisplayUpdatePacket.Pixel(255, 255, 255),
            'd' to DisplayUpdatePacket.Pixel(255, 255, 255),
            'e' to DisplayUpdatePacket.Pixel(255, 255, 255),
            'f' to DisplayUpdatePacket.Pixel(255, 255, 255)
        ))
        packet.setColorGradient(hashMapOf(
            DisplayUpdatePacket.Pixel(255, 255, 255) to
                    DisplayUpdatePacket.Gradient(
                        229, 216, 92,
                        107, 153, 0)
        ))
        sender?.sendPacket(packet)
    }

    private fun getString(type: Int, data: Float) : String {
        val minus = data < 0
        val ten = (abs(data) * 10).toInt() / 100
        val one = (abs(data) * 10).toInt() / 10 % 10
        val dot = (abs(data) * 10).toInt() % 10

        var str = "12345678"
        str = appendMinus(str, minus, 'a')
        str = appendDigit(str, ten, 'b')
        str = appendDigit(str, one, 'c')
        str = appendDot(str, 'd')
        str = appendDigit(str, dot, 'e')
        str = appendEnd(str, type, 'f')

        return str
    }

    private fun appendMinus(str: String, minus: Boolean, c: Char) : String {
        var nstr = str

        nstr = nstr.replace("1", "     1")
        nstr = nstr.replace("2", "     2")
        nstr = nstr.replace("3", "     3")
        nstr = nstr.replace("4", "     4")
        if (minus)
            nstr = nstr.replace("5", " !!! 5")
        else
            nstr = nstr.replace("5", "     5")
        nstr = nstr.replace("6", "     6")
        nstr = nstr.replace("7", "     7")
        nstr = nstr.replace("8", "     8")

        nstr = nstr.replace("!", c.toString())

        return nstr
    }

    private fun appendDigit(str: String, digit: Int, c: Char) : String {
        var nstr = str

        when (digit) {
            0 -> {
                nstr = nstr.replace("1", "    1")
                nstr = nstr.replace("2", "!!! 2")
                nstr = nstr.replace("3", "! ! 3")
                nstr = nstr.replace("4", "! ! 4")
                nstr = nstr.replace("5", "! ! 5")
                nstr = nstr.replace("6", "! ! 6")
                nstr = nstr.replace("7", "! ! 7")
                nstr = nstr.replace("8", "!!! 8")
            }
            1 -> {
                nstr = nstr.replace("1", "    1")
                nstr = nstr.replace("2", "  ! 2")
                nstr = nstr.replace("3", "  ! 3")
                nstr = nstr.replace("4", "  ! 4")
                nstr = nstr.replace("5", "  ! 5")
                nstr = nstr.replace("6", "  ! 6")
                nstr = nstr.replace("7", "  ! 7")
                nstr = nstr.replace("8", "  ! 8")
            }
            2 -> {
                nstr = nstr.replace("1", "    1")
                nstr = nstr.replace("2", "!!! 2")
                nstr = nstr.replace("3", "  ! 3")
                nstr = nstr.replace("4", "  ! 4")
                nstr = nstr.replace("5", "!!! 5")
                nstr = nstr.replace("6", "!   6")
                nstr = nstr.replace("7", "!   7")
                nstr = nstr.replace("8", "!!! 8")
            }
            3 -> {
                nstr = nstr.replace("1", "    1")
                nstr = nstr.replace("2", "!!! 2")
                nstr = nstr.replace("3", "  ! 3")
                nstr = nstr.replace("4", "  ! 4")
                nstr = nstr.replace("5", "!!! 5")
                nstr = nstr.replace("6", "  ! 6")
                nstr = nstr.replace("7", "  ! 7")
                nstr = nstr.replace("8", "!!! 8")
            }
            4 -> {
                nstr = nstr.replace("1", "    1")
                nstr = nstr.replace("2", "! ! 2")
                nstr = nstr.replace("3", "! ! 3")
                nstr = nstr.replace("4", "! ! 4")
                nstr = nstr.replace("5", "!!! 5")
                nstr = nstr.replace("6", "  ! 6")
                nstr = nstr.replace("7", "  ! 7")
                nstr = nstr.replace("8", "  ! 8")
            }
            5 -> {
                nstr = nstr.replace("1", "    1")
                nstr = nstr.replace("2", "!!! 2")
                nstr = nstr.replace("3", "!   3")
                nstr = nstr.replace("4", "!   4")
                nstr = nstr.replace("5", "!!! 5")
                nstr = nstr.replace("6", "  ! 6")
                nstr = nstr.replace("7", "  ! 7")
                nstr = nstr.replace("8", "!!! 8")
            }
            6 -> {
                nstr = nstr.replace("1", "    1")
                nstr = nstr.replace("2", "!!! 2")
                nstr = nstr.replace("3", "!   3")
                nstr = nstr.replace("4", "!   4")
                nstr = nstr.replace("5", "!!! 5")
                nstr = nstr.replace("6", "! ! 6")
                nstr = nstr.replace("7", "! ! 7")
                nstr = nstr.replace("8", "!!! 8")
            }
            7 -> {
                nstr = nstr.replace("1", "    1")
                nstr = nstr.replace("2", "!!! 2")
                nstr = nstr.replace("3", "  ! 3")
                nstr = nstr.replace("4", "  ! 4")
                nstr = nstr.replace("5", "  ! 5")
                nstr = nstr.replace("6", "  ! 6")
                nstr = nstr.replace("7", "  ! 7")
                nstr = nstr.replace("8", "  ! 8")
            }
            8 -> {
                nstr = nstr.replace("1", "    1")
                nstr = nstr.replace("2", "!!! 2")
                nstr = nstr.replace("3", "! ! 3")
                nstr = nstr.replace("4", "! ! 4")
                nstr = nstr.replace("5", "!!! 5")
                nstr = nstr.replace("6", "! ! 6")
                nstr = nstr.replace("7", "! ! 7")
                nstr = nstr.replace("8", "!!! 8")
            }
            9 -> {
                nstr = nstr.replace("1", "    1")
                nstr = nstr.replace("2", "!!! 2")
                nstr = nstr.replace("3", "! ! 3")
                nstr = nstr.replace("4", "! ! 4")
                nstr = nstr.replace("5", "!!! 5")
                nstr = nstr.replace("6", "  ! 6")
                nstr = nstr.replace("7", "  ! 7")
                nstr = nstr.replace("8", "!!! 8")
            }
        }

        nstr = nstr.replace("!", c.toString())

        return nstr
    }

    private fun appendDot(str: String, c: Char) : String {
        var nstr = str

        nstr = nstr.replace("1", "  1")
        nstr = nstr.replace("2", "  2")
        nstr = nstr.replace("3", "  3")
        nstr = nstr.replace("4", "  4")
        nstr = nstr.replace("5", "  5")
        nstr = nstr.replace("6", "  6")
        nstr = nstr.replace("7", "  7")
        nstr = nstr.replace("8", "! 8")

        nstr = nstr.replace("!", c.toString())

        return nstr
    }

    private fun appendEnd(str: String, type: Int, c: Char) : String {
        var nstr = str

        when (type) {
            0 -> { // temp
                nstr = nstr.replace("1", "     ")
                nstr = nstr.replace("2", "!    ")
                nstr = nstr.replace("3", "  !!!")
                nstr = nstr.replace("4", " !   ")
                nstr = nstr.replace("5", " !   ")
                nstr = nstr.replace("6", " !   ")
                nstr = nstr.replace("7", " !   ")
                nstr = nstr.replace("8", "  !!!")
            }
            1 -> { // hum
                nstr = nstr.replace("1", "     ")
                nstr = nstr.replace("2", "     ")
                nstr = nstr.replace("3", "!   !")
                nstr = nstr.replace("4", "   ! ")
                nstr = nstr.replace("5", "  !  ")
                nstr = nstr.replace("6", " !   ")
                nstr = nstr.replace("7", "!   !")
                nstr = nstr.replace("8", "     ")
            }
            2 -> { // dust
                nstr = nstr.replace("1", "     ")
                nstr = nstr.replace("2", "     ")
                nstr = nstr.replace("3", "     ")
                nstr = nstr.replace("4", "!  ! ")
                nstr = nstr.replace("5", "!  ! ")
                nstr = nstr.replace("6", "!  ! ")
                nstr = nstr.replace("7", "!!!  ")
                nstr = nstr.replace("8", "!    ")
            }
        }

        nstr = nstr.replace("!", c.toString())

        return nstr
    }
}