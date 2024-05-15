package com.example.kedatechapp.ui.detail

import android.app.Application
import android.os.Bundle
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

class ProductDetailViewModel(application: Application, savedStateHandle: SavedStateHandle) :
    AndroidViewModel(application) {
    @Parcelize
    data class State(
        val product: Product? = null
    ) : Parcelable

    private var state by StateDelegate(savedStateHandle, State())
    private var _uiState = MutableLiveData<State>()
    private val productDao = AppDatabase.invoke(application).ProductDao()
    val uiState: LiveData<State> get() = _uiState

    fun extractArguments(bundle: Bundle?) {
        updateState(state.copy(product = bundle?.getParcelable(KEY_PRODUCT)))
    }

    fun setFavorite() {
        val isFavorite = state.product?.isFavorite ?: false
        state = state.copy(product = state.product?.copy(isFavorite = !isFavorite))
        addToFavorite()
    }

    private fun addToFavorite() {
        state.product?.let {
            viewModelScope.launch(Dispatchers.IO) {
                productDao.updateProduct(it)
                withContext(Dispatchers.Main){
                    updateState(state)
                }
            }
        }
    }

    private fun updateState(state: State) {
        this.state = state
        _uiState.value = this.state
    }

    companion object {
        const val KEY_PRODUCT = "KEY_PRODUCT"
    }
}