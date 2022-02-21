package com.example.neostore.activities.reset_password

import com.google.gson.annotations.SerializedName

data class ResetReponseBase
    (
    @SerializedName("status")
    val status: Int,
    @SerializedName("message")
    val message: String,
    @SerializedName("error")
    val error: ResetResponseError,
    @SerializedName("user_msg")
    val user_msg: String
)
