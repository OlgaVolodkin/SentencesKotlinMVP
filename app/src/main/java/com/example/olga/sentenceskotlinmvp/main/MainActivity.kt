package com.example.olga.sentenceskotlinmvp.main

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.olga.sentenceskotlinmvp.Consts.Main.ENG
import com.example.olga.sentenceskotlinmvp.Consts.Main.HEB
import com.example.olga.sentenceskotlinmvp.NoInternetDialogFragment
import com.example.olga.sentenceskotlinmvp.R
import com.example.olga.sentenceskotlinmvp.R.string.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_main.*
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.io.Reader
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.util.*


class MainActivity : AppCompatActivity(), MainInterface.View {

    private var presenter: MainPresenter? = null
    private val TAG = "SentencesActivity"
    var flag: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupListeners()
        setInitial()
    }

    private fun setupListeners(){
        engBtn.setOnClickListener{
            flag = ENG
            StartDownload().execute()
        }
        hebBtn.setOnClickListener{
            flag = HEB
            StartDownload().execute()
        }
    }

    private fun setInitial() {
        presenter = MainPresenter(this)
    }

    inner class StartDownload : AsyncTask<Void, Void, ArrayList<String>>() {
        override fun doInBackground(vararg p0: Void?): ArrayList<String>? {

            var sentList: ArrayList<String>? = null
            var connection: HttpURLConnection? = null
            var url: URL? = null
            try {
                if (flag == ENG){
                    url = URL(getString(eng_url))
                }
                else if (flag == HEB) {
                    url = URL(getString(heb_url))
                }
                if (url == null) {
                    return null
                }

                connection = url.openConnection() as HttpURLConnection?

                val inputStream: InputStream? = connection?.inputStream
                val reader: Reader = InputStreamReader(inputStream,"UTF-8")
                val gson = Gson()
                sentList = gson.fromJson(reader,object: TypeToken<ArrayList<String>>() {}.type)

                return sentList
            }
            catch (e: MalformedURLException) {
                e.printStackTrace()
            }
            catch (e: IOException) {
                e.printStackTrace()
            }
            catch (e: Exception) {
                e.printStackTrace()
            }
            finally {
                connection?.disconnect()
            }
            return sentList

            return null
        }


        override fun onPostExecute(sents: ArrayList<String>) {
            super.onPostExecute(sents)

            if (sents.size != 0) {
                val i = Random().nextInt(sents.size)
                val currentSent = sents[i]

                presenter?.startSentencesActivity(currentSent, this@MainActivity, flag)

            }
            else {  //if list is empty (no sents or not internet) - alert dialog (pop up window)
                supportFragmentManager.beginTransaction().add(NoInternetDialogFragment(), getString(internet)).commitAllowingStateLoss()
            }
        }
    }


    
    override fun startSentenceActivity(intent: Intent) {
        startActivity(intent)
    }

}
