package com.example.olga.sentenceskotlinmvp.ui.sentences

import android.content.Context
import android.content.Intent
import com.example.olga.sentenceskotlinmvp.consts.Consts
import com.example.olga.sentenceskotlinmvp.R
import com.example.olga.sentenceskotlinmvp.R.string.language
import com.example.olga.sentenceskotlinmvp.R.string.sent_str

class SentencesPresenter(view: SentencesInterface.View): SentencesInterface.Presenter {

    private var view: SentencesInterface.View? = null

    init {
        this.view = view
    }

    override fun setSentence(intent: Intent, context: Context) {
        val flg = intent.getStringExtra(context.getString(language))
        if (flg == Consts.Main.HEB) {
           view?.setBackgroundPic(R.drawable.imageheb)
        }
        else if (flg == Consts.Main.ENG) {
           view?.setBackgroundPic(R.drawable.imageeng)
        }
        view?.setSentenceText(intent.getStringExtra(context.getString(sent_str)))
   }
}