package com.salekart.data.datasource

import com.salekart.data.entity.Product
import com.salekart.data.entity.ProductResponse
import retrofit2.Response

interface ProductDataSource {

    suspend fun getListOfProduct(): Response<ProductResponse>

    suspend fun getProductDetail(productId: String): Response<Product>
}