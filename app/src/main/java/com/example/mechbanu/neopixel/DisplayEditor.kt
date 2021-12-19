package com.example.mechbanu.neopixel

import android.util.Log
import com.example.mechbanu.packet.instance.DisplayUpdatePacket
import com.example.mechbanu.utils.sender
import java.lang.Math.abs

object DisplayEditor {
    fun sendWeatherPacket(weather: String?) {
        val packet = DisplayUpdatePacket(5, 20)

        when (weather) {
            "Thunderstorm" -> { // 뇌우
                packet.draw("" +
                        ".........1111.111......." +
                        "........111111111......." +
                        "........1111111111......" +
                        ".........12111111......." +
                        "...........2..2........." +
                        "..........2..2.........." +
                        "...........2..2........." +
                        ".............2..........", hashMapOf(
                            '1' to DisplayUpdatePacket.Pixel(255, 255, 255),
                            '2' to DisplayUpdatePacket.Pixel(255, 255, 0)
                        )
                )
                packet.setColorGradient(hashMapOf(
                    DisplayUpdatePacket.Pixel(255, 255, 255) to DisplayUpdatePacket.Gradient(
                        100, 100, 100,
                        0, 0, 0),
                    DisplayUpdatePacket.Pixel(255, 255, 0) to DisplayUpdatePacket.Gradient(
                        255, 255, 0,
                        255, 100, 0)
                ))
            }
            "Drizzle" -> { // 이슬비
                packet.draw("" +
                        ".........1111.111......." +
                        "........111111111......." +
                        "........1111111111......" +
                        ".........11111111......." +
                        "..........2.2...2......." +
                        "..............2........." +
                        "...........2............" +
                        "...............2........", hashMapOf(
                    '1' to DisplayUpdatePacket.Pixel(255, 255, 255),
                    '2' to DisplayUpdatePacket.Pixel(0, 0, 255)
                )
                )
                packet.setColorGradient(hashMapOf(
                    DisplayUpdatePacket.Pixel(255, 255, 255) to DisplayUpdatePacket.Gradient(
                        200, 200, 200,
                        100, 100, 100),
                    DisplayUpdatePacket.Pixel(0, 0, 255) to DisplayUpdatePacket.Gradient(
                        0, 216, 255,
                        0, 84, 255)
                ))
            }
            "Rain" -> { // 비
                packet.draw("" +
                        ".........1111.111......." +
                        "........111111111......." +
                        "........1111111111......" +
                        ".........11121111......." +
                        "..........2.2...2......." +
                        "..........2...2.2......." +
                        "............2.2........." +
                        "............2...........", hashMapOf(
                    '1' to DisplayUpdatePacket.Pixel(255, 255, 255),
                    '2' to DisplayUpdatePacket.Pixel(0, 0, 255)
                )
                )
                packet.setColorGradient(hashMapOf(
                    DisplayUpdatePacket.Pixel(255, 255, 255) to DisplayUpdatePacket.Gradient(
                        150, 150, 150,
                        50, 50, 50),
                    DisplayUpdatePacket.Pixel(0, 0, 255) to DisplayUpdatePacket.Gradient(
                        0, 150, 255,
                        0, 54, 255)
                ))
            }
            "Snow" -> { // 눈
                packet.draw("" +
                        ".........1111.111......." +
                        "........111111111......." +
                        "........1111111111......" +
                        ".........11111111......." +
                        "..........2.2...2......." +
                        "..............2........." +
                        "...........2............" +
                        ".........2.....2........", hashMapOf(
                    '1' to DisplayUpdatePacket.Pixel(255, 255, 255),
                    '2' to DisplayUpdatePacket.Pixel(0, 0, 255)
                )
                )
                packet.setColorGradient(hashMapOf(
                    DisplayUpdatePacket.Pixel(255, 255, 255) to DisplayUpdatePacket.Gradient(
                        100, 100, 100,
                        50, 50, 50),
                    DisplayUpdatePacket.Pixel(0, 0, 255) to DisplayUpdatePacket.Gradient(
                        255, 255, 255,
                        255, 255, 255)
                ))
            }
            "Clear" -> { // 맑음
                packet.draw("" +
                        "...........22..........." +
                        ".........221122........." +
                        ".........211112........." +
                        "........21111112........" +
                        "........21111112........" +
                        ".........211112........." +
                        ".........221122........." +
                        "...........22...........", hashMapOf(
                    '1' to DisplayUpdatePacket.Pixel(255, 255, 0),
                    '2' to DisplayUpdatePacket.Pixel(255, 0, 0)
                )
                )
                packet.setColorGradient(hashMapOf(
                    DisplayUpdatePacket.Pixel(255, 255, 0) to DisplayUpdatePacket.Gradient(
                        255, 255, 0,
                        255, 187, 0),
                    DisplayUpdatePacket.Pixel(255, 0, 0) to DisplayUpdatePacket.Gradient(
                        255, 94, 0,
                        152, 0, 0)
                ))
            }
            "Clouds" -> { // 구름낌
                packet.draw("" +
                        "........................" +
                        "........................" +
                        ".........1111.111......." +
                        "........111111111......." +
                        "........1111111111......" +
                        ".........11111111......." +
                        "........................" +
                        "........................", hashMapOf(
                    '1' to DisplayUpdatePacket.Pixel(255, 255, 255)
                )
                )
                packet.setColorGradient(hashMapOf(
                    DisplayUpdatePacket.Pixel(255, 255, 255) to DisplayUpdatePacket.Gradient(
                        100, 100, 100,
                        50, 50, 50)
                ))
            }
            else -> { // 안개
                packet.draw("" +
                        "........................" +
                        "...........111.........." +
                        ".......11.....1111......" +
                        ".........111............." +
                        ".....111......1111......" +
                        ".........1111..........." +
                        "........................" +
                        "........................", hashMapOf(
                    '1' to DisplayUpdatePacket.Pixel(255, 255, 255)
                )
                )
                packet.setColorGradient(hashMapOf(
                    DisplayUpdatePacket.Pixel(255, 255, 255) to DisplayUpdatePacket.Gradient(
                        100, 100, 100,
                        50, 50, 50)
                ))
            }
        }
        sender?.sendPacket(packet)
    }

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