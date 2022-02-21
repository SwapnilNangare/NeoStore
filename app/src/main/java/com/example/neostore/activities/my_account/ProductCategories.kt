package com.example.neostore.activities.my_account

import com.google.gson.annotations.SerializedName

data class ProductCategories(

    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("icon_image")
    val icon_image: String,
    @SerializedName("created")
    val created: String,
    @SerializedName("modified")
    val modified: String
)