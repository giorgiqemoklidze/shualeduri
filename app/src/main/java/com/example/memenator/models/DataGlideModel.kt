package com.example.memenator.models

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

data class DataGlideModel(
    val url: String
)

@BindingAdapter("android:glideImage")
fun glidetImage(imageView : ImageView, url : String) {
    Glide.with(imageView)
        .load(url)
        .into(imageView)
}