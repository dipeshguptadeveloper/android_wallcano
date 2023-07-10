package com.dkgtech.wallcano

import com.dkgtech.wallcano.model.CuratedWallpaperModel
import com.dkgtech.wallcano.model.WallpaperModel
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiHelper {

    @GET("search")
    fun getSearchWallpaper(
        @Header("Authorization") auth: String,
        @Query("query") query: String,
        @Query("per_page") per_page: Int
    ): Call<WallpaperModel>

    @GET("curated")
    fun getCuratedWallpaper(
        @Header("Authorization") auth: String,
        @Query("per_page") per_page: Int
    ): Call<CuratedWallpaperModel>


    companion object {

        val BASE_URL = "https://api.pexels.com/v1/"

        fun create(): ApiHelper {

            val retrofitClient = Retrofit
                .Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofitClient.create(ApiHelper::class.java)
        }
    }
}