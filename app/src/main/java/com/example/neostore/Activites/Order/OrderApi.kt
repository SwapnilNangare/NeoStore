package com.example.neostore.Activites.Order

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface OrderApi {

    @GET("orderList")
    fun getAllOrderList(@Header("access_token") token: String)

            : Call<Order_Response_Base>
}