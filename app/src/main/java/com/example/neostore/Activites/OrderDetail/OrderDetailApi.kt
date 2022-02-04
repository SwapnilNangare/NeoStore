package com.example.neostore.Activites.OrderDetail

import com.example.neostore.Activites.ProductDetails.Product_base_response
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface OrderDetailApi {




    @GET("orderDetail")
    fun fetchorderdetail(
        @Header("access_token") token: String,
        @Query("order_id") order_id: String
    )
            : Call<Order_Detail_Response_Base>
}