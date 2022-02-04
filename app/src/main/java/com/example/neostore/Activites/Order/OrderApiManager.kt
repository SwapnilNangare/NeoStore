package com.example.neostore.Activites.Order

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object OrderApiManager {
    const val BASE_URL = "http://staging.php-dev.in:8844/trainingapp/api/"

    private val builder = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())

    val retrofit1: Retrofit = builder.build()
    val instance: OrderApi by lazy{
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(OrderApi::class.java)
    }
}

