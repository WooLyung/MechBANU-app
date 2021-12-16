package com.example.mechbanu.speech.classificater

import com.example.mechbanu.MoodLampEditor
import com.example.mechbanu.speech.IClassificater

class Classificater_MoodLampTempUp : IClassificater {
    override val speechs = arrayListOf(
        "색온도올려",
        "색온도올려줘",
        "무드등색온도올려줘",
        "무드등색온도올려"
    )

    override fun process() {
        MoodLampEditor.tempUp()
    }
}