package com.salekart.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.salekart.data.entity.ProductResponse
import com.salekart.ui.repository.ProductRepository
import com.salekart.utilities.ResourceState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(private val productRepository: ProductRepository) : ViewModel() {

    private val _product : MutableStateFlow<ResourceState<ProductResponse>> = MutableStateFlow(ResourceState.Loading())
    val products : StateFlow<ResourceState<ProductResponse>> = _product

    init {
        getProducts()
    }

    private fun getProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            productRepository.getListProducts()
                .collectLatest { productResponse ->
                    _product.value = productResponse
                }
        }
    }
}