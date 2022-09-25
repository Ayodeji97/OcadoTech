package com.danzucker.ocadoproducts.presentation.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.danzucker.ocadoproducts.R

fun ImageView.loadImage(url: String, placeHolder: Int = R.drawable.avatar_icon) {
    Glide.with(this.context)
        .load(url)
        .placeholder(placeHolder)
        .error(placeHolder)
        .into(this)
}