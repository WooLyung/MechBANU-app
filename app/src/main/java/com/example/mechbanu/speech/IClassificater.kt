package com.example.mechbanu.speech

interface IClassificater {
    val speechs: List<String>

    fun classificate(text: String) : Boolean {
        return text in speechs
    }

    fun process()
}