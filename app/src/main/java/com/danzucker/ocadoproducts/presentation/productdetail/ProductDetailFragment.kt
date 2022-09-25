package com.danzucker.ocadoproducts.presentation.productdetail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.danzucker.ocadoproducts.R
import com.danzucker.ocadoproducts.databinding.FragmentProductDetailsBinding
import com.danzucker.ocadoproducts.presentation.utils.loadImage
import com.danzucker.ocadoproducts.presentation.utils.onBackPress
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class ProductDetailFragment : Fragment() {

    private var currentBinding : FragmentProductDetailsBinding? = null
    private val ui get() = currentBinding!!

    private val productDetailViewModel : ProductDetailViewModel by viewModels()

    private lateinit var productId : String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       // return super.onCreateView(inflater, container, savedInstanceState)
        currentBinding = FragmentProductDetailsBinding.inflate(inflater)
        return ui.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onBackPress(
            ui.toolbar.appToolbar,
            R.id.productListFragment,
            false,
        )

        productId = ProductDetailFragmentArgs.fromBundle(requireArguments()).productId


        productDetailAction ()
        subscriberObserver ()

        ui.backBtn.setOnClickListener { findNavController().navigate(R.id.productListFragment) }

    }


    private fun productDetailAction () {
        productDetailViewModel.onTriggeredEvent(ProductDetailsEvent.ProductDetails(
            productId
        ))
    }

    private fun subscriberObserver () {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            productDetailViewModel.productDetailViewState.collectLatest {
                ui.apply {
                    progressBar.isVisible = it.isLoading
                    if (it.error != "") {
                        fragmentErrorTv.visibility = View.VISIBLE
                        fragmentErrorTv.text = it.error
                        fragmentCharacterDetailBpTv.visibility = View.GONE
                        fragmentCharacterDetailCl.visibility = View.GONE
                        fragmentCharacterPlanetCl.visibility = View.GONE
                        fragmentCharacterDetailPlanetTv.visibility = View.GONE
                    } else {
                        productAvatarIv.loadImage(it.productDetail?.imageUrl ?: "")
                        fragmentTitleTv.text = it.productDetail?.title
                        productDescriptionTv.text = it.productDetail?.description
                        productAllergiesTv.text = it.productDetail?.allergyInformation
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