package com.danzucker.ocadoproducts.presentation.productlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.danzucker.ocadoproducts.databinding.FragmentProductListBinding
import com.danzucker.ocadoproducts.presentation.adapter.ProductListAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class ProductListFragment : Fragment() {

    private var currentBinding : FragmentProductListBinding? = null
    private val ui get() = currentBinding!!

    private val productListViewModel : ProductListViewModel by viewModels()
    private lateinit var productListAdapter : ProductListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // return super.onCreateView(inflater, container, savedInstanceState)
        currentBinding = FragmentProductListBinding.inflate(inflater)
        return ui.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        uiEvent()
        initRecyclerView ()
        subscribeObserver()


    }

    private fun initRecyclerView () {
        productListAdapter = ProductListAdapter(
            onProductClicked = { productItem ->
                // navigate with the product item Id
            }
        )

        ui.apply {
            recyclerViewProductList.apply {
                adapter = productListAdapter
                layoutManager = GridLayoutManager(
                    requireContext(),
                    2
                )
                setHasFixedSize(true)
            }
        }

    }

    private fun uiEvent() {
        productListViewModel.onTriggeredEvent(ProductListEvent.GetProductList)
    }

    private fun subscribeObserver() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            productListViewModel.productListViewState.collectLatest {
               ui.apply {
                   progressBar.isVisible = it.isLoading
                   if (it.error != "") {
                       fragmentErrorTv.visibility = View.VISIBLE
                       recyclerViewProductList.visibility = View.GONE
                       fragmentErrorTv.text = it.error
                   } else {
                       fragmentErrorTv.visibility = View.GONE
                       productListAdapter.submitList(it.productList)
                   }
               }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        currentBinding = null
    }
}