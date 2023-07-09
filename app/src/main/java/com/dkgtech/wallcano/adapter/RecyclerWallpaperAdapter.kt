package com.dkgtech.wallcano.adapter

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dkgtech.wallcano.databinding.WallpaperRowBinding
import com.dkgtech.wallcano.model.PhotosModel

class RecyclerWallpaperAdapter(val context: Context, val listPhoto: List<PhotosModel>) :
    RecyclerView.Adapter<RecyclerWallpaperAdapter.ViewHolder>() {
    class ViewHolder(val binding: WallpaperRowBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(WallpaperRowBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun getItemCount(): Int {
        return listPhoto.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.binding) {
            Glide.with(context).load(Uri.parse(listPhoto[position].srcModel!!.portrait))
                .into(imgSrc)
        }
    }
}