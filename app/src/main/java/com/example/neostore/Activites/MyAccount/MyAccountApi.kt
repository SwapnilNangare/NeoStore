package com.example.neostore.Activites.MyAccount

import com.example.neostore.Activites.LoginScreen.model.LoginResponse
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface MyAccountApi {
    @GET("users/getUserData")
    fun fetchUser(@Header("access_token") token: String)
            : Call<Myaccountbaseresponse>
    @Multipart
    @POST("users/update")
    fun useredit(
        @Header("access_token") token: String,
        @PartMap() images: MutableMap<String, RequestBody>
    )
            : Call<LoginResponse>
}