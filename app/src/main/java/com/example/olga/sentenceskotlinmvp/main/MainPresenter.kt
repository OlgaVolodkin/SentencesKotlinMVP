package com.example.olga.sentenceskotlinmvp.main

import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.olga.sentenceskotlinmvp.R
import com.example.olga.sentenceskotlinmvp.RetrofitInterface
import com.example.olga.sentenceskotlinmvp.sentences.SentencesActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class MainPresenter (view: MainInterface.View): MainInterface.Presenter {

    private val TAG = "MainPresenter"
    private var view: MainInterface.View? = null

    init {
        this.view = view
    }

    override fun makingDataToSentencesActivity(currentSent: String?, context: Context, lng: String) {
        val intent = Intent(context, SentencesActivity::class.java)
        intent.putExtra(context.getString(R.string.sent_str), currentSent)
        intent.putExtra(context.getString(R.string.flag_lang), lng)
        view?.startSentenceActivity(intent)
    }

    override fun downloadSentences(lng: String) {
        val call = RetrofitInterface.Factory.instance?.getSent(lng)
        call?.enqueue(object : Callback<ArrayList<String>> {
            override fun onResponse(call: Call<ArrayList<String>>, response: Response<ArrayList<String>>) {
                if (response.body() == null) {
                    view?.showInternetDialog()
                } else {
                    view?.ifComplete(response.body()!!, lng)
                }
            }
            override fun onFailure(call: Call<ArrayList<String>>, t: Throwable) {
                Log.d(TAG, t.message)
                view?.showInternetDialog()
            }
        })
    }
}