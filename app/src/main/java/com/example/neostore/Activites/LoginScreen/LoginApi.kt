package com.example.neostore.Activites.LoginScreen

import com.example.neostore.Activites.LoginScreen.model.LoginResponse
import okhttp3.RequestBody
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