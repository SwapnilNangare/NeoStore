package com.example.neostore.activities.api_manager

import com.example.neostore.activities.forgot_password.ForgotPasswordApi
import com.example.neostore.activities.login_screen.LoginApi
import com.example.neostore.activities.my_account.MyAccountApi
import com.example.neostore.activities.my_cart.MyCartApi
import com.example.neostore.activities.order.OrderApi
import com.example.neostore.activities.order_detail.OrderDetailApi
import com.example.neostore.activities.product_details.ProductDetailsApi
import com.example.neostore.activities.register_screen.RegisterApi
import com.example.neostore.activities.reset_password.ResetPasswordApi
import com.example.neostore.activities.table.TableApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiManager {

    private const val BASE_URL = "http://staging.php-dev.in:8844/trainingapp/api/"

    private val builder = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())

    // retrofit 1
    val instance: ForgotPasswordApi by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(ForgotPasswordApi::class.java)
    }

    // retrofit2

    val instance2: LoginApi by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(LoginApi::class.java)
    }
    //retrofit3


    val instance3: MyAccountApi by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(MyAccountApi::class.java)
    }
    //retrofit4


    val instance4: MyCartApi by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(MyCartApi::class.java)
    }

    //  retrofit5

    val instance5: OrderApi by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(OrderApi::class.java)
    }

    //  retrofit6

    val instance6: OrderDetailApi by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(OrderDetailApi::class.java)
    }

    //  retrofit7

    val instance7: ProductDetailsApi by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(ProductDetailsApi::class.java)
    }

    //  retrofit8

    val instance8: RegisterApi by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(RegisterApi::class.java)
    }

    //  retrofit9
    val retrofit9: Retrofit = builder.build()
    val instance9: ResetPasswordApi by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(ResetPasswordApi::class.java)
    }

    //  retrofit10
    val instance10: TableApi by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(TableApi::class.java)
    }


}

