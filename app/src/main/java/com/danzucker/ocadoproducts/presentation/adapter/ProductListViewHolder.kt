package com.danzucker.ocadoproducts.presentation.adapter

import androidx.recyclerview.widget.RecyclerView
import com.danzucker.ocadoproducts.R
import com.danzucker.ocadoproducts.business.domain.model.ProductItems
import com.danzucker.ocadoproducts.databinding.ProductListItemBinding
import com.danzucker.ocadoproducts.presentation.utils.loadImage

class ProductListViewHolder(
    private val ui: ProductListItemBinding,
    private val onProductClicked: (Int) -> Unit
) : RecyclerView.ViewHolder(ui.root) {

    fun bind(productItem: ProductItems) {
        ui.apply {
            productTitleTv.text = productItem.title
            productPriceTv.text =
                root.context.getString(R.string.currency_str, productItem.price)
            productAvatarIv.loadImage(productItem.imageUrl)
        }
    }

    init {
        ui.apply {
            root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onProductClicked(position)
                }
            }
        }
    }

}