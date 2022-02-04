package com.example.neostore.Activites.table

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TableApi {

    @GET("products/getList")
    fun getAllPhotos(
        @Query("product_category_id") product_category_id: String,
        @Query("value") value: Int
    )

            : Call<Table_response>
}