package com.dkgtech.wallcano.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dkgtech.wallcano.CuratedWallpaperActivity
import com.dkgtech.wallcano.MainActivity
import com.dkgtech.wallcano.WallpaperActivity
import com.dkgtech.wallcano.databinding.CuratedWallpaperRowBinding
import com.dkgtech.wallcano.model.Photos

class RecyclerCuratedWallpaperAdapter(val context: Context, val curatedListPhoto: List<Photos>) :
    RecyclerView.Adapter<RecyclerCuratedWallpaperAdapter.ViewHolder>() {
    class ViewHolder(val binding: CuratedWallpaperRowBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            CuratedWallpaperRowBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return curatedListPhoto.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.binding) {
            Glide.with(context).load(Uri.parse(curatedListPhoto[position].src!!.portrait))
                .into(imgSrc)

            context.startActivity(
                Intent(context, WallpaperActivity::class.java).putExtra(
                    CuratedWallpaperActivity.IMAGE_KEY_URL,
                    curatedListPhoto[position].src!!.portrait
                )
            )

        }
    }
}