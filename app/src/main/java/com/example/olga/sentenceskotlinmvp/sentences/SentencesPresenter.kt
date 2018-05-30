package com.example.olga.sentenceskotlinmvp.sentences

import android.content.Context
import android.content.Intent
import com.example.olga.sentenceskotlinmvp.Consts
import com.example.olga.sentenceskotlinmvp.R
import com.example.olga.sentenceskotlinmvp.R.string.flag_lang
import com.example.olga.sentenceskotlinmvp.R.string.sent_str

class SentencesPresenter(view: SentencesInterface.View): SentencesInterface.Presenter {

    private var view: SentencesInterface.View? = null

    init {
        this.view = view
    }

    override fun setSentence(intent: Intent, context: Context) {
        val flg = intent.getStringExtra(context.getString(flag_lang))
        if (flg == Consts.Main.HEB) {
           view?.setBackgroundPic(R.drawable.imageheb)
        }
        else if (flg == Consts.Main.ENG) {
           view?.setBackgroundPic(R.drawable.imageeng)
        }
        view?.setSentenceText(intent.getStringExtra(context.getString(sent_str)))
   }
}