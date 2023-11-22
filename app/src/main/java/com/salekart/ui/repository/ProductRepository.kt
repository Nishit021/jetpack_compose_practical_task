package com.salekart.ui.repository

import com.salekart.data.datasource.ProductDataSource
import com.salekart.data.entity.ProductResponse
import com.salekart.utilities.ResourceState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val productDataSource: ProductDataSource
) {
    suspend fun getListProducts() : Flow<ResourceState<ProductResponse>> {
        return flow {
            emit(ResourceState.Loading())

            val response = productDataSource.getListOfProduct()
            val responseBody = response.body()

            if (response.isSuccessful && responseBody != null) {
                emit(ResourceState.Success(responseBody))
            } else {
                emit(ResourceState.Error("Error fetching product data"))
            }
        }.catch { e -> emit(ResourceState.Error(e.localizedMessage ?: "Unknown error occurred")) }
    }

}