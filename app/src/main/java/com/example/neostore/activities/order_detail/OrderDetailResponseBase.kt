package com.example.neostore.activities.order_detail

import com.google.gson.annotations.SerializedName

data class OrderDetailResponseBase(

    @SerializedName("status")
    val status: Int,
    @SerializedName("data")
    val data: OrderDetailData
)