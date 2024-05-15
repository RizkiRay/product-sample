package com.example.kedatechapp

import android.app.Application
import android.os.Bundle
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import com.example.kedatechapp.data.db.entity.Product
import com.example.kedatechapp.ui.detail.ProductDetailViewModel
import com.example.kedatechapp.ui.home.HomeViewModel
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class HomeViewModelTest {
    private val fakeProduct = Product(0, "random", "random", "random", 0L, false)

    private val applicationMock = Mockito.mock(Application::class.java)
    private val viewmodel = HomeViewModel(applicationMock, SavedStateHandle())

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Test
    fun `when setKeyword should set keyword state`() {
        val keyword = "sampleKeyword"
        viewmodel.setKeyword(keyword)
        assertEquals(viewmodel.uiState.value?.keyword, keyword)
    }

    @Test
    fun `when setFavorite should set isFavorite state`() {
        val isFavorite = viewmodel.uiState.value?.isFavorite ?: false
        viewmodel.setFavorite()
        assertEquals(viewmodel.uiState.value?.isFavorite, !isFavorite)
    }
}