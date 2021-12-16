package com.example.mechbanu.packet.instance

import com.example.mechbanu.packet.IPacket

class DisplayUpdatePacket(var duration: Int = 1, var brightness: Int = 20) : IPacket {
    companion object {
        val black: Pixel
            get() = Pixel(0, 0, 0)

        val white: Pixel
            get() = Pixel(255, 255, 255)
    }

    data class Pixel(var r: Int = 0, var g: Int = 0, var b: Int = 0)
    data class Gradient(var r1: Int = 0, var g1: Int = 0, var b1: Int = 0, var r2 : Int = 0, var g2 : Int = 0, var b2: Int = 0) {
        fun parse(i: Int) : Pixel {
            val y = 7 - i % 8
            return Pixel(r1 + (r2 - r1) * y / 7, g1 + (g2 - g1) * y / 7, b1 + (b2 - b1) * y / 7)
        }
    }

    val pixels: Array<Pixel> = (1..192).map { Pixel(0, 0, 0) }.toTypedArray()

    override val op: Byte
        get() = 0x01

    override fun body(): ByteArray {
        var body = byteArrayOf(duration.toByte(), brightness.toByte())

        pixels.forEach { pixel ->
            body += byteArrayOf(pixel.r.toByte(), pixel.g.toByte(), pixel.b.toByte())
        }

        return body
    }

    fun setZero() {
        for (i in 0..191)
            pixels[i] = Pixel(0, 0, 0)
    }

    fun setWhite() {
        for (i in 0..191)
            pixels[i] = Pixel(255, 255, 255)
    }

    fun draw(string: String) {
        for (i in 0..191) {
            val x = i % 24
            val y = i / 24
            val offset = (7 - y) + x * 8

            if (string[i] == '0')
                pixels[offset] = Pixel(255, 255, 255)
            else
                pixels[offset] = Pixel(0, 0, 0)
        }
    }

    fun draw(string: String, map: HashMap<Char, Pixel>) {
        for (i in 0..191) {
            val x = i % 24
            val y = i / 24
            val offset = (7 - y) + x * 8

            pixels[offset] = map.get(string[i]) ?: Pixel(0, 0, 0)
        }
    }

    fun setColor(map: HashMap<Pixel, Pixel>) {
        for (i in 0..191)
            pixels[i] = map.get(pixels[i]) ?: pixels[i]
    }

    fun setColorGradient(map: HashMap<Pixel, Gradient>) {
        for (i in 0..191)
            pixels[i] = map.get(pixels[i])?.parse(i) ?: pixels[i]
    }

    fun setColor(r: Int, g: Int, b: Int) {
        for (i in 0..191)
            if (pixels[i] != Pixel(0, 0, 0))
                pixels[i] = Pixel(r, g, b)
    }

    fun getColorPixel(x: Int, y: Int) : Pixel {
        val i = x * 8 + y
        return pixels[i]
    }

    fun setColorPixel(x: Int, y: Int, r: Int, g: Int, b: Int) {
        val i = x * 8 + y
        pixels[i] = Pixel(r, g, b)
    }
}