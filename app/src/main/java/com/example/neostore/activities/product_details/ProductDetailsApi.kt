package com.example.neostore.activities.product_details

import retrofit2.Call
import retrofit2.http.*

interface ProductDetailsApi {

    @GET("products/getDetail")
    fun fetchUserdetail(@Query("product_id") product_id: Int)
            : Call<ProductBaseResponse>

    @FormUrlEncoded
    @POST("addToCart")
    fun buynow(
        @Header("access_token") token: String,

        @Field("product_id") product_id: Int,
        @Field("quantity") quantity: Int
    )
            : Call<ResponseBaseCartAdd>

    @FormUrlEncoded
    @POST("products/setRating")
    fun setRating(
        @Field("product_id") product_id: Int,
        @Field("rating") value: Float
    )

            : Call<RateResponse>
}