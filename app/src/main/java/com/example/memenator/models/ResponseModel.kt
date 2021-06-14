package com.example.memenator.models

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

data class ResponseModel (val count : String? = null,
                          val memes : List<Memes>? = null

) {

    data class Memes (val subreddit : String? = null,
                      val title : String? = null,
                      val url : String? = null,
                      val author : String? = null){

    }



}



