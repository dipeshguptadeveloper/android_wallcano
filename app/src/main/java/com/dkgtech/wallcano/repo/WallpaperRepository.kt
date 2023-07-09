package com.dkgtech.wallcano.repo

import com.dkgtech.wallcano.ApiHelper
import com.dkgtech.wallcano.model.WallpaperModel
import retrofit2.Call

class WallpaperRepository(val apiHelper: ApiHelper) {

    fun getSearchWallpaper(search: String, perPage: Int): Call<WallpaperModel> {
        return apiHelper.getSearchWallpaper(query = search, per_page = perPage)
    }

}