package com.example.neostore.activities.my_account

import com.example.neostore.activities.login_screen.model.LoginResponse
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface MyAccountApi {
    @GET("users/getUserData")
    fun fetchUser(@Header("access_token") token: String)
            : Call<MyAccountBaseResponse>
    @Multipart
    @POST("users/update")
    fun useredit(
        @Header("access_token") token: String,
        @PartMap() images: MutableMap<String, RequestBody>
    )
            : Call<LoginResponse>
}