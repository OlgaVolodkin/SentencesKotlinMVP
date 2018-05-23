package com.example.olga.sentenceskotlinmvp.main

import android.content.Context
import android.content.Intent
import com.example.olga.sentenceskotlinmvp.R
import com.example.olga.sentenceskotlinmvp.sentences.SentencesActivity


class MainPresenter (view: MainInterface.View): MainInterface.Presenter {

    private var view: MainInterface.View? = null

    init {
        this.view = view
    }


    override fun startSentencesActivity(currentSent: String, context: Context, flag: Int) {

        val intent = Intent(context, SentencesActivity::class.java)
        intent.putExtra(context.getString(R.string.sent_str), currentSent)
        intent.putExtra(context.getString(R.string.flag_lang), flag)

        view?.startSentenceActivity(intent)

    }


}