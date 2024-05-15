package com.example.kedatechapp.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.kedatechapp.R
import com.example.kedatechapp.data.db.entity.Product
import com.example.kedatechapp.databinding.FragmentProductDetailBinding
import com.example.kedatechapp.utils.addMargin
import com.example.kedatechapp.utils.getStatusBarHeight
import com.example.kedatechapp.utils.load
import com.example.kedatechapp.utils.setColoredStatusBar
import com.example.kedatechapp.utils.setTranslucentStatusBar

typealias State = ProductDetailViewModel.State

class ProductDetailFragment : Fragment() {

    private var _binding: FragmentProductDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ProductDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        requireActivity().setTranslucentStatusBar()
        _binding = FragmentProductDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        requireActivity().setColoredStatusBar()
        super.onDestroyView()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.extractArguments(arguments)
        setupToolbar()
        addObservable()
        addListeners()
    }

    private fun render(state: State) {
        renderFavorite(state.product?.isFavorite == true)
        state.product?.let { renderContent(it) }
    }

    private fun renderContent(product: Product) = with(binding) {
        ivProduct.load(product.picUrl)
        tvProduct.text = product.name
        tvDesc.text = product.desc
    }

    private fun renderFavorite(isFavorite: Boolean) {
        binding.ivFavorite.setImageResource(
            if (isFavorite) R.drawable.ic_bookmark_fill
            else R.drawable.ic_bookmark_outline
        )
    }

    private fun setupToolbar() = with(binding.toolbar) {
        addMargin(0, requireActivity().getStatusBarHeight(), 0, 0)
        requireActivity().setActionBar(this)
        requireActivity().actionBar?.setDisplayHomeAsUpEnabled(true)
        requireActivity().actionBar?.title = ""
        setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun addListeners() = with(binding) {
        ivFavorite.setOnClickListener {
            viewModel.setFavorite()
        }
    }

    private fun addObservable() {
        viewModel.uiState.observe(viewLifecycleOwner) {
            render(it)
        }
    }
}