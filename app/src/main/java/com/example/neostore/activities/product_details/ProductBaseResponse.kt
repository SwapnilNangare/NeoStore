package com.example.neostore.activities.product_details

import com.google.gson.annotations.SerializedName

data class ProductBaseResponse(

    @SerializedName("status")
    val status: Int,
    @SerializedName("data")
    val data: ProductDataResponse
)