package com.example.mechbanu.speech.classificater

import com.example.mechbanu.MoodLampEditor
import com.example.mechbanu.speech.IClassificater

class Classificater_MoodLampBrightDown : IClassificater {
    override val speechs = arrayListOf(
        "밝기내려",
        "밝기내려줘",
        "무드등밝기내려줘",
        "무드등밝기내려"
    )

    override fun process() {
        MoodLampEditor.brightDown()
    }
}