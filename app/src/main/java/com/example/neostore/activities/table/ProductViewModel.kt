package com.example.neostore.activities.table

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.neostore.activities.api_manager.ApiManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductViewModel : ViewModel() {

    private var ProductList: MutableLiveData<TableResponse>? = null
    val tables: MutableLiveData<TableResponse>?
        get() {
            if (ProductList == null) {
                ProductList = MutableLiveData<TableResponse>()
                loadTables()
            }
            return ProductList
        }

    val chair: MutableLiveData<TableResponse>?
        get() {
            if (ProductList == null) {
                ProductList = MutableLiveData<TableResponse>()
                loadChairs()
            }
            return ProductList
        }
    val sofa: MutableLiveData<TableResponse>?
        get() {
            if (ProductList == null) {
                ProductList = MutableLiveData<TableResponse>()
                loadSofa()
            }
            return ProductList
        }
    val cupboard: MutableLiveData<TableResponse>?
        get() {
            if (ProductList == null) {
                ProductList = MutableLiveData<TableResponse>()
                loadCupboard()
            }
            return ProductList
        }

    //This method is using Retrofit to get the JSON data from URL
    private fun loadTables() {

        ApiManager.instance10.getAllPhotos(product_category_id = "1", value = 1).enqueue(
            object : Callback<TableResponse> {
                override fun onFailure(call: Call<TableResponse>, t: Throwable) {

                }

                override fun onResponse(
                    call: Call<TableResponse>,
                    response: Response<TableResponse>
                ) {

                    if (response.body() != null) {
                        val res = response
                        ProductList!!.value = response.body()

                    }
                }

            })
    }

    private fun loadChairs() {
        ApiManager.instance10.getAllPhotos(product_category_id = "2", value = 2).enqueue(
            object : Callback<TableResponse> {
                override fun onFailure(call: Call<TableResponse>, t: Throwable) {

                }

                override fun onResponse(
                    call: Call<TableResponse>,
                    response: Response<TableResponse>
                ) {

                    if (response.body() != null) {
                        val res = response
                        ProductList!!.value = response.body()

                    }
                }

            })


    }

    private fun loadSofa() {
        ApiManager.instance10.getAllPhotos(product_category_id = "3", value = 3).enqueue(
            object : Callback<TableResponse> {
                override fun onFailure(call: Call<TableResponse>, t: Throwable) {

                }

                override fun onResponse(
                    call: Call<TableResponse>,
                    response: Response<TableResponse>
                ) {

                    if (response.body() != null) {
                        val res = response
                        ProductList!!.value = response.body()

                    }
                }

            })
    }

    private fun loadCupboard() {
        ApiManager.instance10.getAllPhotos(product_category_id = "4", value = 4).enqueue(
            object : Callback<TableResponse> {
                override fun onFailure(call: Call<TableResponse>, t: Throwable) {

                }

                override fun onResponse(
                    call: Call<TableResponse>,
                    response: Response<TableResponse>
                ) {

                    if (response.body() != null) {
                        val res = response
                        ProductList!!.value = response.body()

                    }
                }

            })
    }
}
