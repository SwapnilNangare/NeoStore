package com.example.neostore.activities.order_detail

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface OrderDetailApi {
    @GET("orderDetail")
    fun fetchOrderDetail(
        @Header("access_token") token: String,
        @Query("order_id") order_id: String
    ):Call<OrderDetailResponseBase>
}