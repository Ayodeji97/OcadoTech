package com.danzucker.ocadoproducts.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.danzucker.ocadoproducts.business.domain.model.ProductItems
import com.danzucker.ocadoproducts.databinding.ProductListItemBinding

class ProductListAdapter(
    private val onProductClicked: (ProductItems) -> Unit
) : ListAdapter<ProductItems, ProductListViewHolder>(ProductListComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val ui = ProductListItemBinding.inflate(layoutInflater, parent, false)
        return ProductListViewHolder(
            ui,
            onProductClicked = { position ->
                val productItem = getItem(position)
                if (productItem != null) {
                    onProductClicked(productItem)
                }
            }
        )
    }

    override fun onBindViewHolder(holder: ProductListViewHolder, position: Int) {
        val currentBinding = getItem(position)
        currentBinding.let {
            holder.bind(it)
        }
    }
}