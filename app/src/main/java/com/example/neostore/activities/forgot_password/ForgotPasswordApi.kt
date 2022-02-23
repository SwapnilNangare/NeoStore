package com.example.neostore.activities.forgot_password

import com.example.neostore.activities.forgot_password.model.ForgotResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST

interface ForgotPasswordApi {


    @FormUrlEncoded
    @POST("users/forgot")
    fun emailForgotPwd(@Field("email") email:String)
            : Call<ForgotResponse>


    @FormUrlEncoded
    @POST("order")
    fun orderNow(
        @Header("access_token") token: String,
        @Field("address") address: String
    ): Call<ForgotResponse>




}