package com.example.neostore.activities.order_detail

import com.google.gson.annotations.SerializedName

data class OrderDetailRes(

    @SerializedName("id")
    val id: Int,
    @SerializedName("order_id")
    val order_id: Int,
    @SerializedName("product_id")
    val product_id: Int,
    @SerializedName("quantity")
    val quantity: Int,
    @SerializedName("total")
    val total: Int,
    @SerializedName("prod_name")
    val prod_name: String,
    @SerializedName("prod_cat_name")
    val prod_cat_name: String,
    @SerializedName("prod_image")
    val prod_image: String
)