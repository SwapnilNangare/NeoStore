package com.example.neostore.activities.order

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface OrderApi {

    @GET("orderList")
    fun getAllOrderList(@Header("access_token") token: String)

            : Call<OrderResponseBase>
}