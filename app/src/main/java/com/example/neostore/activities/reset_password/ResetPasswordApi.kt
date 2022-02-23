package com.example.neostore.activities.reset_password

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST

interface ResetPasswordApi {

    @FormUrlEncoded
    @POST("users/change")
    fun resetPassword(
        @Header("access_token") token: String,
        @Field("old_password") old_password: String,
        @Field("password") password: String,
        @Field("confirm_password") confirm_password: String
    )
            : Call<ResetReponseBase>
}