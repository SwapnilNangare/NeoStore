package com.example.neostore.activities.my_cart

import retrofit2.Call
import retrofit2.http.*

interface MyCartApi {

    @GET("cart")
    fun listCart(
        @Header("access_token") token: String
    ): Call<CartResponse>

    @FormUrlEncoded
    @POST("deleteCart")
    fun deleteCart(
        @Header("access_token") token: String,

        @Field("product_id") product_id: Int
    ): Call<DeleteResponse>

    @FormUrlEncoded
    @POST("editCart")
    fun editCart(
        @Header("access_token") token: String,
        @Field("product_id") product_id: Int,
        @Field("quantity") quantity: Int
    ): Call<DeleteResponse>
}