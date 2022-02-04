package com.example.neostore.Activites.table

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductViewModel : ViewModel() {

    private var ProductList: MutableLiveData<Table_response>? = null
    val Tabless: MutableLiveData<Table_response>?
        get() {
            if (ProductList == null) {
                ProductList = MutableLiveData<Table_response>()
                loadTables()
            }
            return ProductList
        }

    val Chair: MutableLiveData<Table_response>?
        get() {
            if (ProductList == null) {
                ProductList = MutableLiveData<Table_response>()
                loadChairs()
            }
            return ProductList
        }
    val Sofa: MutableLiveData<Table_response>?
        get() {
            if (ProductList == null) {
                ProductList = MutableLiveData<Table_response>()
                loadSofa()
            }
            return ProductList
        }
    val Cupboard: MutableLiveData<Table_response>?
        get() {
            if (ProductList == null) {
                ProductList = MutableLiveData<Table_response>()
                loadCupboard()
            }
            return ProductList
        }
    //This method is using Retrofit to get the JSON data from URL
    private fun loadTables() {

        TableApiManager.instance.getAllPhotos(product_category_id = "1", value = 1).enqueue(
            object : Callback<Table_response> {
                override fun onFailure(call: Call<Table_response>, t: Throwable) {

                }

                override fun onResponse(
                    call: Call<Table_response>,
                    response: Response<Table_response>
                ) {

                    if (response.body() != null) {
                        val res = response
                        ProductList!!.value = response.body()

                    }
                }

            })
    }
    private fun loadChairs(){
        TableApiManager.instance.getAllPhotos(product_category_id = "2", value = 2).enqueue(
            object : Callback<Table_response> {
                override fun onFailure(call: Call<Table_response>, t: Throwable) {

                }

                override fun onResponse(
                    call: Call<Table_response>,
                    response: Response<Table_response>
                ) {

                    if (response.body() != null) {
                        val res = response
                        ProductList!!.value = response.body()

                    }
                }

            })


    }
    private fun loadSofa(){
        TableApiManager.instance.getAllPhotos(product_category_id = "3", value = 3).enqueue(
            object : Callback<Table_response> {
                override fun onFailure(call: Call<Table_response>, t: Throwable) {

                }

                override fun onResponse(
                    call: Call<Table_response>,
                    response: Response<Table_response>
                ) {

                    if (response.body() != null) {
                        val res = response
                        ProductList!!.value = response.body()

                    }
                }

            })
    }
    private fun loadCupboard(){
        TableApiManager.instance.getAllPhotos(product_category_id = "4", value = 4).enqueue(
            object : Callback<Table_response> {
                override fun onFailure(call: Call<Table_response>, t: Throwable) {

                }

                override fun onResponse(
                    call: Call<Table_response>,
                    response: Response<Table_response>
                ) {

                    if (response.body() != null) {
                        val res = response
                        ProductList!!.value = response.body()

                    }
                }

            })
    }
}
