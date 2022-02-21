package com.example.neostore.activities.order

import com.google.gson.annotations.SerializedName


data class OrderResponseBase(

    @SerializedName("status")
    val status: Int,
    @SerializedName("data")
    val data: List<OrderResponseData>,
    @SerializedName("message")
    val message: String,
    @SerializedName("user_msg")
    val user_msg: String
)
