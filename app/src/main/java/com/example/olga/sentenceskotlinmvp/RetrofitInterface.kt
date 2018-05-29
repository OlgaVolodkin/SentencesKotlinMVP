package com.example.olga.sentenceskotlinmvp

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


interface RetrofitInterface {

    @GET("eng.json")
    fun getSentEng(): retrofit2.Call<ArrayList<String>>

    @GET("heb.json")
    fun getSentHeb(): retrofit2.Call<ArrayList<String>>

/*
    @GET("{id}?api_key=dc310ae1f74743b9985a714c9e201984&language=en-US&append_to_response=videos")
    fun getItemResults(@Path("id") id: Int?): Call<PopularMovieDetailPojo>
*/

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