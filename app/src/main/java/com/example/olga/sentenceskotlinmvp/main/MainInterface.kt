package com.example.olga.sentenceskotlinmvp.main

import android.content.Context
import android.content.Intent

interface MainInterface {

    interface View {
        fun startSentenceActivity(intent: Intent)
    }

    interface Model {}

    interface Presenter {
      fun  startSentencesActivity(currentSent: String, context: Context, flag: Int)
    }

}