package com.example.neostore.activities.login_screen

import com.example.neostore.activities.login_screen.model.LoginResponse
import retrofit2.Call
import retrofit2.http.*

interface LoginApi {

    @FormUrlEncoded
    @POST("users/login")
    fun userLogin(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<LoginResponse>



}