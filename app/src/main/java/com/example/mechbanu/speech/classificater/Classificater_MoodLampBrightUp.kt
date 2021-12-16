package com.example.mechbanu.speech.classificater

import com.example.mechbanu.neopixel.MoodLampEditor
import com.example.mechbanu.speech.IClassificater

class Classificater_MoodLampBrightUp : IClassificater {
    override val speechs = arrayListOf(
        "밝기올려",
        "밝기올려줘",
        "무드등밝기올려줘",
        "무드등밝기올려"
    )

    override fun process() {
        MoodLampEditor.brightUp()
    }
}