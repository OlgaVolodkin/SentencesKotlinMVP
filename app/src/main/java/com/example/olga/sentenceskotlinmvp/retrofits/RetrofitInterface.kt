package com.example.olga.sentenceskotlinmvp.retrofits

import com.example.olga.sentenceskotlinmvp.consts.Consts
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitInterface {

    // @GET("eng.json")
    // fun getSentEng(): retrofit2.Call<ArrayList<String>>

    @GET("{lng}.json")
    fun getSent(@Path("lng") lng: String): retrofit2.Call<ArrayList<String>>

    //factory
    object Factory {
        private var service: RetrofitInterface? = null
        private val baseUrl: String = Consts.Retrofit.BASE_URL

        val instance: RetrofitInterface? get() {
            return if (service == null) {
                val retrofit = Retrofit.Builder()
                        .baseUrl(baseUrl)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                service = retrofit.create(RetrofitInterface::class.java)
                service
            } else {
                service
            }
        }
    }
}