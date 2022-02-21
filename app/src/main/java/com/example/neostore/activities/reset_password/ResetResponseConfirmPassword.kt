package com.example.neostore.activities.reset_password

import com.google.gson.annotations.SerializedName

data class ResetResponseConfirmPassword(
    @SerializedName("compareWith") val compareWith: String
)