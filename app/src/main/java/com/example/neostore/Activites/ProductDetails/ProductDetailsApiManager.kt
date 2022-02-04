package com.example.neostore.Activites.ProductDetails

import com.example.neostore.Activites.Order.OrderApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ProductDetailsApiManager {
    const val BASE_URL = "http://staging.php-dev.in:8844/trainingapp/api/"

    private val builder = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())

    val retrofit1: Retrofit = builder.build()
    val instance: ProductDetailsApi by lazy{
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(ProductDetailsApi::class.java)
    }
}

