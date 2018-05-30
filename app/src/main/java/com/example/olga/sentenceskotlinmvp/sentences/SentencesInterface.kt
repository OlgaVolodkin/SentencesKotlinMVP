package com.example.olga.sentenceskotlinmvp.sentences

import android.content.Context
import android.content.Intent

interface SentencesInterface {
    interface View {
        fun setBackgroundPic(pic: Int)
        fun setSentenceText(currentSentence: String)
    }

    interface Model {}

    interface Presenter {
        fun setSentence(intent: Intent, context: Context)
    }
}