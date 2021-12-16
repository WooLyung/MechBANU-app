package com.example.mechbanu.speech.classificater

import com.example.mechbanu.MoodLampEditor
import com.example.mechbanu.speech.IClassificater

class Classificater_MoodLampOn : IClassificater {
    override val speechs = arrayListOf(
        "불켜",
        "무드등켜",
        "어두워",
        "밝혀줘",
        "밝혀",
        "무드등온"
    )

    override fun process() {
        MoodLampEditor.on()
    }
}