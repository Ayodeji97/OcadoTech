package com.danzucker.ocadoproducts.presentation.utils

import android.widget.ImageView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.danzucker.ocadoproducts.R

fun ImageView.loadImage(url: String, placeHolder: Int = R.drawable.avatar_icon) {
    Glide.with(this.context)
        .load(url)
        .placeholder(placeHolder)
        .error(placeHolder)
        .into(this)
}

fun Fragment.onBackPress(
    toolbar: Toolbar,
    destinationId : Int,
    inclusive : Boolean,
    saveState: Boolean = false
) {
    toolbar.setNavigationIcon(R.drawable.back_arrow)
    toolbar.setOnClickListener {
        findNavController().popBackStack(destinationId, inclusive, saveState)
    }

}