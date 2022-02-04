package com.example.neostore.Activites.ForgotPasswordActivity

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST

interface ForgotPasswordApi {


    @FormUrlEncoded
    @POST("users/forgot")
    fun emailForgotpwd(
        @Field("email") email:String)
            : Call<ForgotResponse>


    @FormUrlEncoded
    @POST("order")
    fun ordernow(
        @Header("access_token") token: String,

        @Field("address") address: String
    )
            : Call<ForgotResponse>




}