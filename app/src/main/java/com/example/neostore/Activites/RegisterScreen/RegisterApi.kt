package com.example.neostore.Activites.RegisterScreen

import com.example.neostore.Activites.LoginScreen.model.LoginResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface RegisterApi {

    @FormUrlEncoded
    @POST("users/register")
    fun createUser(
        @Field("first_name") first_name:String,
        @Field("last_name") last_name:String,
        @Field("email") email:String,
        @Field("password") password:String,
        @Field("confirm_password") confirm_password:String,
        @Field("gender") gender:String,
        @Field("phone_no") phone_no:String)
            : Call<LoginResponse>
}