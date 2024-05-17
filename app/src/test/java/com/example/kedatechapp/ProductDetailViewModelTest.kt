package com.example.kedatechapp

import android.app.Application
import android.os.Bundle
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import com.example.kedatechapp.data.db.entity.Product
import com.example.kedatechapp.ui.detail.ProductDetailViewModel
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class ProductDetailViewModelTest {
    private val fakeProduct = Product(0, "random", "random", "random", 0L, false)

    private val applicationMock = Mockito.mock(Application::class.java)
    private val viewmodel = ProductDetailViewModel(applicationMock, SavedStateHandle())
    val bundle = mockk<Bundle>()

    @Before
    fun setUp() {
        every { bundle.getParcelable<Product>(ProductDetailViewModel.KEY_PRODUCT) } returns fakeProduct
        viewmodel.extractArguments(bundle)
    }

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Test
    fun `when run extractArguments state product should not be null`() {
        viewmodel.extractArguments(bundle)
        assertNotNull(viewmodel.uiState.value?.product)
    }
}