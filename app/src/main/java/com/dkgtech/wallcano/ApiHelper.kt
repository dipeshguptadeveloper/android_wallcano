package com.dkgtech.wallcano

import com.dkgtech.wallcano.model.WallpaperModel
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiHelper {

    @Headers("Authorization : rAwzTmcXYsSfCf6C5uUThO0izNTor82oYH3S78hab8COjw9IpYCC6FA3")
    @GET("search")
    fun getSearchWallpaper(
        @Query("query") query: String,
        @Query("per_page") per_page: Int
    ): Call<WallpaperModel>


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