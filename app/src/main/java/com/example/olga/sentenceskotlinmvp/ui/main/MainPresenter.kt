package com.example.olga.sentenceskotlinmvp.ui.main

import android.content.Context
import android.content.Intent
import com.example.olga.sentenceskotlinmvp.R
import com.example.olga.sentenceskotlinmvp.managers.DownloadSentencesManager
import com.example.olga.sentenceskotlinmvp.managers.DownloadSentencesManagerInterface
import com.example.olga.sentenceskotlinmvp.ui.sentences.SentencesActivity
import retrofit2.Response
import java.util.*

class MainPresenter (view: MainInterface.View): MainInterface.Presenter, DownloadSentencesManagerInterface.ModelListener {

    private val TAG = "MainPresenter"
    private var view: MainInterface.View? = null
    private var downloadManager: DownloadSentencesManagerInterface? = null

    init {
        this.view = view
        this.downloadManager = DownloadSentencesManager()
    }


    override fun makingDataToSentencesActivity(currentSent: String?, context: Context, lng: String) {
        val intent = Intent(context, SentencesActivity::class.java)
        intent.putExtra(context.getString(R.string.sent_str), currentSent)
        intent.putExtra(context.getString(R.string.flag_lang), lng)
        view?.startSentenceActivity(intent)
    }


    override fun downloadSentences(lng: String) {
        downloadManager?.downloadSentences(lng, this)
    }


    override fun downloadSentencesSuccess(response: Response<ArrayList<String>>, lng: String) {
        if (response.body() == null) {
            view?.showInternetDialog()
        } else {
            view?.ifComplete(response.body()!!, lng)
        }
    }


    override fun downloadSentencesFailure() {
        view?.showInternetDialog()
    }
}