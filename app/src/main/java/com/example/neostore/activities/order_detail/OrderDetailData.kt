package com.example.neostore.activities.order_detail

import com.google.gson.annotations.SerializedName

data class OrderDetailData(

    @SerializedName("id")
    val id: Int,
    @SerializedName("cost")
    val cost: Int,
    @SerializedName("address")
    val address: String,
    @SerializedName("order_details")
    val order_details: List<OrderDetailRes>
)