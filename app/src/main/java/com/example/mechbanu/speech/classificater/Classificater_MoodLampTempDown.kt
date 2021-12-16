package com.example.mechbanu.speech.classificater

import com.example.mechbanu.neopixel.MoodLampEditor
import com.example.mechbanu.speech.IClassificater

class Classificater_MoodLampTempDown : IClassificater {
    override val speechs = arrayListOf(
        "색온도내려",
        "색온도내려줘",
        "무드등색온도내려줘",
        "무드등색온도내려"
    )

    override fun process() {
        MoodLampEditor.tempDown()
    }
}