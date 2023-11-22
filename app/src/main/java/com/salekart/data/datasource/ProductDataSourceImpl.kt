package com.salekart.data.datasource

import com.salekart.data.api.ApiServices
import com.salekart.data.entity.Product
import com.salekart.data.entity.ProductResponse
import retrofit2.Response
import javax.inject.Inject

class ProductDataSourceImpl @Inject constructor(private  val apiService: ApiServices) : ProductDataSource {

    override suspend fun getListOfProduct(): Response<ProductResponse> {
        return apiService.getListOfProduct()
    }

    override suspend fun getProductDetail(id: String): Response<Product> {
        return apiService.getProductDetail(productId = id)
    }

}