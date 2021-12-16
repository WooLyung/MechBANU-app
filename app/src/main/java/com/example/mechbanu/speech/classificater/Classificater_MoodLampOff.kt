package com.example.mechbanu.speech.classificater

import com.example.mechbanu.MoodLampEditor
import com.example.mechbanu.speech.IClassificater

class Classificater_MoodLampOff : IClassificater {
    override val speechs = arrayListOf(
        "불꺼",
        "무드등꺼",
        "밝아",
        "무드등오프"
    )

    override fun process() {
        MoodLampEditor.off()
    }
}