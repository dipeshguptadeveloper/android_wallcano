package com.dkgtech.wallcano

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.dkgtech.wallcano.adapter.RecyclerWallpaperAdapter
import com.dkgtech.wallcano.databinding.ActivityMainBinding
import com.dkgtech.wallcano.repo.WallpaperRepository

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    lateinit var mainActivityViewModel: MainActivityViewModel

    companion object {
        val IMAGE_KEY = "imgUrl"
    }

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

            mainActivityViewModel.getSearchWallpaper(
                "rAwzTmcXYsSfCf6C5uUThO0izNTor82oYH3S78hab8COjw9IpYCC6FA3",
                "technology",
                20
            )

            mainActivityViewModel.listPhotos.observe(this@MainActivity) { photos ->
                rcViewWallpaper.adapter = RecyclerWallpaperAdapter(this@MainActivity, photos)
            }

            mainActivityViewModel.errMsg.observe(this@MainActivity) { errorMsg ->
                Toast.makeText(this@MainActivity, errorMsg, Toast.LENGTH_SHORT).show()

            }


            btnSearch.setOnClickListener {
                val searchText = edtSearch.text.toString()

                mainActivityViewModel.getSearchWallpaper(
                    "rAwzTmcXYsSfCf6C5uUThO0izNTor82oYH3S78hab8COjw9IpYCC6FA3",
                    searchText,
                    80
                )
            }

            mainActivityViewModel.listPhotos.observe(this@MainActivity) { photos ->
                rcViewWallpaper.adapter = RecyclerWallpaperAdapter(this@MainActivity, photos)
            }

            mainActivityViewModel.errMsg.observe(this@MainActivity) { errorMsg ->
                Toast.makeText(this@MainActivity, errorMsg, Toast.LENGTH_SHORT).show()

            }

            btnCuratedWallpaper.setOnClickListener {
                startActivity(Intent(this@MainActivity, CuratedWallpaperActivity::class.java))
            }

        }

    }
}