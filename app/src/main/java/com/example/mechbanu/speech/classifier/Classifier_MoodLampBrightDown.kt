package com.example.mechbanu.speech.classifier

import com.example.mechbanu.neopixel.MoodLampEditor
import com.example.mechbanu.speech.IClassifier

class Classifier_MoodLampBrightDown : IClassifier {
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