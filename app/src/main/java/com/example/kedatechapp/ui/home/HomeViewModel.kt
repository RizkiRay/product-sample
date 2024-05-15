package com.example.kedatechapp.ui.home

import android.app.Application
import android.os.Parcelable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.kedatechapp.data.db.AppDatabase
import com.example.kedatechapp.data.db.entity.Product
import com.example.kedatechapp.utils.StateDelegate
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.parcelize.Parcelize

class HomeViewModel(application: Application, savedStateHandle: SavedStateHandle) :
    AndroidViewModel(application) {
    @Parcelize
    data class State(
        val products: List<Product> = listOf(),
        val keyword: String = "",
        val isFavorite: Boolean = false
    ) : Parcelable

    private var state by StateDelegate(savedStateHandle, State())
    private var _uiState = MutableLiveData<State>()
    private val productDao = AppDatabase.invoke(application).ProductDao()

    val uiState: LiveData<State> get() = _uiState

    fun setKeyword(keyword: String) {
        updateState(state.copy(keyword = keyword))
        getProducts()
    }

    fun setFavorite() {
        updateState(state.copy(isFavorite = !state.isFavorite))
        getProducts()
    }

    fun getProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            val products =
                if (!state.isFavorite) productDao.searchAllProducts(state.keyword) else productDao.searchProductWithFilter(
                    state.keyword,
                    state.isFavorite
                )
            withContext(Dispatchers.Main) {
                updateState(state.copy(products = products))
            }
        }
    }

    private fun updateState(state: State) {
        this.state = state
        _uiState.value = this.state
    }
}