package com.dkgtech.wallcano

import android.app.WallpaperManager
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings.Global
import android.widget.Toast
import com.bumptech.glide.Glide
import com.dkgtech.wallcano.databinding.ActivityWallpaperBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class WallpaperActivity : AppCompatActivity() {
    lateinit var binding: ActivityWallpaperBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWallpaperBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            val imageUrl = intent.getStringExtra(MainActivity.IMAGE_KEY)

            imageUrl?.let {
                Glide.with(this@WallpaperActivity).load(Uri.parse(imageUrl)).into(imgWallpaper)
            }

            val image_URL = intent.getStringExtra(CuratedWallpaperActivity.IMAGE_KEY_URL)

            imageUrl?.let {
                Glide.with(this@WallpaperActivity).load(Uri.parse(image_URL)).into(imgWallpaper)
            }


            btnSet.setOnClickListener {

                GlobalScope.launch(Dispatchers.IO) {
                    val bitmap = Picasso.get().load(Uri.parse(imageUrl)).get()
                    val wallManager = WallpaperManager.getInstance(this@WallpaperActivity)
                    wallManager.setBitmap(bitmap)
                }
                Toast.makeText(
                    this@WallpaperActivity,
                    "Wallpaper Set Successfully",
                    Toast.LENGTH_SHORT
                ).show()
            }

        }

    }
}