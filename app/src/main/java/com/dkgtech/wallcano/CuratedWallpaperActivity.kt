package com.dkgtech.wallcano

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.dkgtech.wallcano.adapter.RecyclerCuratedWallpaperAdapter
import com.dkgtech.wallcano.adapter.RecyclerWallpaperAdapter
import com.dkgtech.wallcano.databinding.ActivityCuratedWallpaperBinding
import com.dkgtech.wallcano.repo.WallpaperRepository

class CuratedWallpaperActivity : AppCompatActivity() {

    lateinit var binding: ActivityCuratedWallpaperBinding

    lateinit var mainActivityViewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCuratedWallpaperBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val apiHelper = ApiHelper.create()

        val repo = WallpaperRepository(apiHelper)

        mainActivityViewModel =
            ViewModelProvider(
                this@CuratedWallpaperActivity,
                MainActivityViewModelFactory(repo)
            )[MainActivityViewModel::class.java]

        with(binding) {
            rcViewCuratedWallpaper.layoutManager =
                GridLayoutManager(this@CuratedWallpaperActivity, 2)

            mainActivityViewModel.getCuratedWallpaper(
                "rAwzTmcXYsSfCf6C5uUThO0izNTor82oYH3S78hab8COjw9IpYCC6FA3", 80
            )

            mainActivityViewModel.curatedListPhotos.observe(this@CuratedWallpaperActivity) { photos ->
                rcViewCuratedWallpaper.adapter =
                    RecyclerCuratedWallpaperAdapter(this@CuratedWallpaperActivity, photos)
            }

            mainActivityViewModel.errMsg.observe(this@CuratedWallpaperActivity) { errorMsg ->
                Toast.makeText(this@CuratedWallpaperActivity, errorMsg, Toast.LENGTH_SHORT).show()

            }
        }

    }
}