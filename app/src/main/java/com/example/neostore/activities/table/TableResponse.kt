package com.example.neostore.activities.table

import com.google.gson.annotations.SerializedName

data class TableResponse(

    @SerializedName("status")
    val status: Int,
    @SerializedName("data")
    val data: List<TableData>
)