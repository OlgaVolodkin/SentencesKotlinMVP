package com.example.olga.sentenceskotlinmvp.managers

import android.util.Log
import com.example.olga.sentenceskotlinmvp.retrofits.RetrofitInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class DownloadSentencesManager: DownloadSentencesManagerInterface {

    private val TAG = "DownloadSentenceManager"

    override fun downloadSentences(lng: String, listener: DownloadSentencesManagerInterface.ModelListener) {
        val call = RetrofitInterface.Factory.instance?.getSent(lng)
        call?.enqueue(object : Callback<ArrayList<String>> {
            override fun onResponse(call: Call<ArrayList<String>>, response: Response<ArrayList<String>>) {
                listener.downloadSentencesSuccess(response, lng)
            }
            override fun onFailure(call: Call<ArrayList<String>>, t: Throwable) {
                Log.d(TAG, t.message)
                listener.downloadSentencesFailure()
            }
        })
    }
}