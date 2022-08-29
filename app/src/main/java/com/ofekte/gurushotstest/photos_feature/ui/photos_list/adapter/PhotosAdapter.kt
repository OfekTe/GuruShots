package com.ofekte.gurushotstest.photos_feature.ui.photos_list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.ofekte.gurushotstest.R
import com.ofekte.gurushotstest.databinding.ViewAdapterPhotoBinding
import com.ofekte.gurushotstest.photos_feature.domain.model.Photo


val DIFF_CALLBACK: DiffUtil.ItemCallback<Photo> =
    object : DiffUtil.ItemCallback<Photo>() {
        override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            return oldItem == newItem
        }
    }

class PhotosAdapter(
    private val photoOnClickListener: PhotoOnClickListener
) : ListAdapter<Photo, PhotosAdapter.PhotoViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        return PhotoViewHolder.create(parent, photoOnClickListener)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val photo = getItem(position)
        holder.bind(photo)
    }

    class PhotoViewHolder private constructor(
        private val binding: ViewAdapterPhotoBinding,
        private val photoOnClickListener: PhotoOnClickListener
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(photo: Photo) = with(binding) {
            photoIv.load(photo.squareImageUrl)

            with(binding.root.context) {
                tvLikes.text = getString(R.string.likes, photo.likes)
                tvVotes.text = getString(R.string.votes, photo.votes)
                tvViews.text = getString(R.string.views, photo.views)
                tvHasLiked.text = getString(if (photo.hasLiked) R.string.liked else R.string.not_liked)
            }

            root.setOnClickListener { photoOnClickListener.photoOnClick(photo) }
        }

        companion object {
            fun create(parent: ViewGroup, photoOnClickListener: PhotoOnClickListener): PhotoViewHolder {
                val binding = ViewAdapterPhotoBinding.inflate(LayoutInflater.from(parent.context))
                return PhotoViewHolder(binding, photoOnClickListener)
            }
        }
    }

    interface PhotoOnClickListener {
        fun photoOnClick(photo: Photo)
    }
}