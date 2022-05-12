package com.example.neostore.activities.order

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.neostore.activities.api_manager.ApiManager
import com.example.neostore.activities.order_detail.OrderDetailResponseBase
import com.example.neostore.activities.shared_pref_manager.SharedPrefManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class OrderListViewModel(context: Application): AndroidViewModel(context) {

    private var orderLists: MutableLiveData<OrderResponseBase>? = null
    val orderList: MutableLiveData<OrderResponseBase>?
        get() {
            if (orderLists == null) {
                orderLists = MutableLiveData<OrderResponseBase>()
                loadOrderList()
            }
            return orderLists
        }


    private var OrderDetails: MutableLiveData<OrderDetailResponseBase>? = null
    val orderDetails: MutableLiveData<OrderDetailResponseBase>?
        get() {
            if (OrderDetails == null) {
                OrderDetails = MutableLiveData<OrderDetailResponseBase>()

            }
            return OrderDetails
        }

    val token: String = SharedPrefManager.getInstance(getApplication()).user.access_token.toString()

    //This method is using Retrofit to get the JSON data from URL
    private fun loadOrderList() {


        ApiManager.instance5.getAllOrderList(token).enqueue(object :
            Callback<OrderResponseBase> {
            override fun onFailure(call: Call<OrderResponseBase>, t: Throwable) {
            }

            override fun onResponse(
                call: Call<OrderResponseBase>,
                response: Response<OrderResponseBase>
            ) {

                if (response.isSuccessful) {

                    orderLists!!.value = response.body()

                }
            }

        })
    }
}
