package com.example.neostore.activities.order

import com.google.gson.annotations.SerializedName

data class OrderResponseData (

    @SerializedName("id")
    val id : Int,
    @SerializedName("cost")
    val cost : Int,
    @SerializedName("created")
    val created : String
)