package com.example.neostore.activities.reset_password

import com.google.gson.annotations.SerializedName

data class ResetResponseError(
    @SerializedName("confirm_password") val confirm_password: ResetResponseConfirmPassword

)