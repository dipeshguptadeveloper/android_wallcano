package com.dkgtech.wallcano.repo

import com.dkgtech.wallcano.ApiHelper
import com.dkgtech.wallcano.model.CuratedWallpaperModel
import com.dkgtech.wallcano.model.WallpaperModel
import retrofit2.Call

class WallpaperRepository(val apiHelper: ApiHelper) {

    fun getSearchWallpaper(auth: String, search: String, perPage: Int): Call<WallpaperModel> {
        return apiHelper.getSearchWallpaper(auth, query = search, per_page = perPage)
    }

    fun getCuratedWallpaper(auth: String, perPage: Int): Call<CuratedWallpaperModel> {
        return apiHelper.getCuratedWallpaper(auth, perPage)
    }
}