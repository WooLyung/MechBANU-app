package com.example.mechbanu.speech.classifier

import com.example.mechbanu.neopixel.MoodLampEditor
import com.example.mechbanu.speech.IClassifier

class Classifier_MoodLampOff : IClassifier {
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