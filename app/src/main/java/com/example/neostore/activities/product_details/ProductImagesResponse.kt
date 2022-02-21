package com.example.neostore.activities.product_details

import com.google.gson.annotations.SerializedName

data class ProductImagesResponse (

    @SerializedName("id")
    val id : Int,
    @SerializedName("product_id")
    val product_id : Int,
    @SerializedName("image")
    val image : String,
    @SerializedName("created")
    val created : String,
    @SerializedName("modified")
    val modified : String
)