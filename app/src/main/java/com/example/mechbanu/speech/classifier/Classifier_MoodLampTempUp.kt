package com.example.mechbanu.speech.classifier

import com.example.mechbanu.neopixel.MoodLampEditor
import com.example.mechbanu.speech.IClassifier

class Classifier_MoodLampTempUp : IClassifier {
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