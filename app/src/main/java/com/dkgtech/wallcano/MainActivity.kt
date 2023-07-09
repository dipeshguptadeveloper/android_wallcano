package com.dkgtech.wallcano

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.dkgtech.wallcano.adapter.RecyclerWallpaperAdapter
import com.dkgtech.wallcano.databinding.ActivityMainBinding
import com.dkgtech.wallcano.repo.WallpaperRepository

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    lateinit var mainActivityViewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val apiHelper = ApiHelper.create()

        val repo = WallpaperRepository(apiHelper)

        mainActivityViewModel =
            ViewModelProvider(
                this@MainActivity,
                MainActivityViewModelFactory(repo)
            )[MainActivityViewModel::class.java]

        with(binding) {
            rcViewWallpaper.layoutManager = GridLayoutManager(this@MainActivity, 2)

            mainActivityViewModel.getSearchWallpaper("nature", 20)

            mainActivityViewModel.listPhotos.observe(this@MainActivity) { photos ->
                rcViewWallpaper.adapter = RecyclerWallpaperAdapter(this@MainActivity, photos)
            }

            mainActivityViewModel.errMsg.observe(this@MainActivity) { errorMsg ->
                Toast.makeText(this@MainActivity, errorMsg, Toast.LENGTH_SHORT).show()

            }

        }

    }
}