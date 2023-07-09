package com.dkgtech.wallcano

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dkgtech.wallcano.model.PhotosModel
import com.dkgtech.wallcano.model.WallpaperModel
import com.dkgtech.wallcano.repo.WallpaperRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityViewModel(val wallpaperRepository: WallpaperRepository) : ViewModel() {

    val listPhotos = MutableLiveData<List<PhotosModel>>()
    val errMsg = MutableLiveData<String>()

    fun getSearchWallpaper(search: String, perPage: Int) {

        wallpaperRepository.getSearchWallpaper(search, perPage)
            .enqueue(object : Callback<WallpaperModel> {
                override fun onResponse(
                    call: Call<WallpaperModel>?,
                    response: Response<WallpaperModel>?
                ) {
                    if (response?.code() == 200) {
                        listPhotos.postValue(response.body()!!.photosModel)
                        Log.d("Response", response.body().toString())
                    } else {
                        Log.d("Error", "${response?.errorBody()}, ${response?.code()}")
                        errMsg.postValue(
                            "Error : ${
                                response?.errorBody()
                            },${
                                response?.code()
                            }"
                        )
                    }
                }

                override fun onFailure(call: Call<WallpaperModel>?, t: Throwable?) {
                    Log.d("Network Error", "${t?.message}")
                    t?.printStackTrace()
                    errMsg.postValue("Network Error : ${t?.message}")
                }

            })

    }
}