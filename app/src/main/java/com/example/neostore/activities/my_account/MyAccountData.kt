package com.example.neostore.activities.my_account

import com.google.gson.annotations.SerializedName

data class MyAccountData(

    @SerializedName("user_data")
    val user_data: UserData,
    @SerializedName("product_categories")
    val product_categories: List<ProductCategories>,
    @SerializedName("total_carts")
    val total_carts: Int,
    @SerializedName("total_orders")
    val total_orders: Int
)