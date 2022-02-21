package com.example.neostore.activities.my_account

import com.google.gson.annotations.SerializedName

data class MyAccountBaseResponse (

    @SerializedName("status")
    val status : Int,
    @SerializedName("data")
    val data : MyAccountData
)