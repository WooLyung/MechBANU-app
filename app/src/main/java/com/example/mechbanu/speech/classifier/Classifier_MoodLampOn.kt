package com.example.mechbanu.speech.classifier

import com.example.mechbanu.neopixel.MoodLampEditor
import com.example.mechbanu.speech.IClassifier

class Classifier_MoodLampOn : IClassifier {
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