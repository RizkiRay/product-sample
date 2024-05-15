package com.example.kedatechapp.ui.home

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.widget.doAfterTextChanged
import androidx.navigation.fragment.findNavController
import com.example.kedatechapp.R
import com.example.kedatechapp.data.db.entity.Product
import com.example.kedatechapp.databinding.FragmentHomeBinding
import com.example.kedatechapp.ui.detail.ProductDetailViewModel

typealias State = HomeViewModel.State

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val productAdapter by lazy {
        ProductAdapter(::goToDetailPage)
    }

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addListeners()
        setupRecyclerView()
        addObserver()
        viewModel.getProducts()
    }

    private fun render(state: State) {
        productAdapter.items = state.products
        with(binding.tvFavorite) {
            background = if (state.isFavorite) {
                setTextColor(ContextCompat.getColor(requireContext(), R.color.red))
                ContextCompat.getDrawable(requireContext(), R.drawable.bg_fill_pink_8dp)
            } else {
                setTextColor(ContextCompat.getColor(requireContext(), R.color.soft_pink))
                ContextCompat.getDrawable(requireContext(), R.drawable.bg_stroke_pink_8dp)
            }
        }
    }

    private fun setupRecyclerView() {
        binding.rvProduct.adapter = productAdapter
    }

    private fun addObserver() {
        viewModel.uiState.observe(viewLifecycleOwner) {
            render(it)
        }
    }

    private fun addListeners() = with(binding) {
        etSearch.doAfterTextChanged { viewModel.setKeyword(it.toString()) }
        tvFavorite.setOnClickListener { viewModel.setFavorite() }
    }

    private fun goToDetailPage(product: Product) {
        findNavController().navigate(
            R.id.action_navigation_home_to_productDetailFragment,
            Bundle().apply {
                putParcelable(ProductDetailViewModel.KEY_PRODUCT, product)
            })
    }
}