package com.example.neostore.Activites.MyAccount

import com.google.gson.annotations.SerializedName

data class Myaccountbaseresponse (

    @SerializedName("status")
    val status : Int,
    @SerializedName("data")
    val data : Myaccountdata
)