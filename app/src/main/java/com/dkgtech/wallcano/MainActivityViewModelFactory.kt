package com.dkgtech.wallcano

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dkgtech.wallcano.repo.WallpaperRepository

class MainActivityViewModelFactory(val wallpaperRepository: WallpaperRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainActivityViewModel(wallpaperRepository) as T
    }
}