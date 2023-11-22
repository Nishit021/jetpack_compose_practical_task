package com.salekart.data.entity

data class ProductResponse(
    val products: List<Product> = mutableListOf(),
    val total : Int,
    val skip: Int,
    val limit: Int
)

data class Product(
    val id: Int = 0,
    val title: String = "",
    val description: String = "",
    val price: Int = 0,
    val discountPercentage: Double = 0.00,
    val rating: Double = 0.00,
    val stock: Int = 0,
    val brand: String = "",
    val category: String = "",
    val thumbnail: String = "",
    val images: List<String> = mutableListOf()
)
