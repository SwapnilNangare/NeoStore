package com.example.neostore.Activites.Order

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.neostore.Activites.OrderDetail.Order_Detail_Response_Base
import com.example.neostore.Activites.SharedPrefManager.SharedPrefManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class OrderListViewModel(context: Application): AndroidViewModel(context) {

    private var OrderLists: MutableLiveData<Order_Response_Base>? = null
    val orderList: MutableLiveData<Order_Response_Base>?
        get() {
            if (OrderLists == null) {
                OrderLists = MutableLiveData<Order_Response_Base>()
                loadOrderList()
            }
            return OrderLists
        }


    private var OrderDetails: MutableLiveData<Order_Detail_Response_Base>? = null
    val orderdetails: MutableLiveData<Order_Detail_Response_Base>?
        get() {
            if (OrderDetails == null) {
                OrderDetails = MutableLiveData<Order_Detail_Response_Base>()
               // loadOrderDetails()
            }
            return OrderDetails
        }

    val token: String =
        SharedPrefManager.getInstance(
            getApplication()
        ).user.access_token.toString()

    //This method is using Retrofit to get the JSON data from URL
    private fun loadOrderList() {


        OrderApiManager.instance.getAllOrderList(token).enqueue(object :
            Callback<Order_Response_Base> {
            override fun onFailure(call: Call<Order_Response_Base>, t: Throwable) {
            }

            override fun onResponse(
                call: Call<Order_Response_Base>,
                response: Response<Order_Response_Base>
            ) {

                if (response.isSuccessful) {

                    OrderLists!!.value = response.body()

                }
            }

        })
    }
}
