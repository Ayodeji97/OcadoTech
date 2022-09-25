package com.danzucker.ocadoproducts.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.danzucker.ocadoproducts.business.domain.model.ProductItems

class ProductListComparator : DiffUtil.ItemCallback<ProductItems>() {
    override fun areItemsTheSame(oldItem: ProductItems, newItem: ProductItems): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: ProductItems, newItem: ProductItems): Boolean =
        oldItem == newItem
}