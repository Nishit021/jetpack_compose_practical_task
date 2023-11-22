package com.salekart.data.api

import com.salekart.data.entity.Product
import com.salekart.data.entity.ProductResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiServices {

    @GET("products")
    suspend fun getListOfProduct(): Response<ProductResponse>

    @GET("products/{id}")
    suspend fun getProductDetail(@Path("id") productId: String): Response<Product>
}