package com.example.neostore.Activites.table

import com.google.gson.annotations.SerializedName

data class Table_response (

    @SerializedName("status")
    val status : Int,
    @SerializedName("data")
    val data : List<Tabledata>
)