package com.example.neostore.activities.my_cart

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.neostore.activities.api_manager.ApiManager
import com.example.neostore.activities.shared_pref_manager.SharedPrefManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CartViewModel (context: Application): AndroidViewModel(context) {
    private var Cart: MutableLiveData<CartResponse>? = null
    val CartList: MutableLiveData<CartResponse>?
        get() {

            if (Cart == null) {
                Cart = MutableLiveData<CartResponse>()
                loadCartList()
            }
            return Cart
        }
    private fun loadCartList(){
        val token: String =
            SharedPrefManager.getInstance(
                getApplication()
            ).user.access_token.toString()
        ApiManager.instance4.listCart(token).enqueue(object :
            Callback<CartResponse> {
            override fun onFailure(call: Call<CartResponse>, t: Throwable) {
                Toast.makeText(getApplication(), "falied", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(
                call: Call<CartResponse>,
                response: Response<CartResponse>
            ) {

                if (response.isSuccessful) {

                    Cart!!.value = response.body()

                }
            }

        })

    }
}