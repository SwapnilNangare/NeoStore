package com.example.neostore.activities.order_detail

import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.neostore.R
import com.example.neostore.activities.api_manager.ApiManager
import com.example.neostore.activities.shared_pref_manager.SharedPrefManager
import com.example.neostore.base.BaseClassActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OrderDetailList : BaseClassActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getWindow().setExitTransition(null)
        getWindow().setEnterTransition(null)
        setContentView(R.layout.order_detail_item_activity)
        var mActionBarToolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbartable);
        setSupportActionBar(mActionBarToolbar);
        // add back arrow to toolbar
        if (getSupportActionBar() != null) {
            getSupportActionBar()?.setDisplayHomeAsUpEnabled(true);
            getSupportActionBar()?.setDisplayShowHomeEnabled(true);
            getSupportActionBar()?.setHomeAsUpIndicator(R.drawable.ic_keyboard_arrow_left_black_24dp);
        }
        val intent1: Intent = getIntent()

        val id: String = intent1.getStringExtra("id")
        supportActionBar!!.title = id
        getSupportActionBar()?.setTitle((Html.fromHtml("<font color=\"#FFFFFF\"" + "<b>" + "ORDER ID:" + id + "</font>")));


        //  supportActionBar!!.title = id
        Log.e("getId", id)
        val token: String = SharedPrefManager.getInstance(applicationContext).user.access_token.toString()
        ApiManager.instance6.fetchOrderDetail(token, id).enqueue(object : Callback<OrderDetailResponseBase> {
            override fun onFailure(call: Call<OrderDetailResponseBase>, t: Throwable) {
                Toast.makeText(applicationContext, "falied", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(
                call: Call<OrderDetailResponseBase>,
                response: Response<OrderDetailResponseBase>
            ) {

                if (response.isSuccessful) {
                    var res = response
                    Log.e("checkresponsee", response.body().toString())
                    val retro: List<OrderDetailRes> = response.body()!!.data.order_details
                    generateDataList(retro)
                }
            }

        })
    }

    fun generateDataList(dataList: List<OrderDetailRes>) {
        val recyclerView = findViewById<RecyclerView>(R.id.recyleview)
        val linear: LinearLayoutManager =
            LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = linear
        val adapter = OrderDetailAdapter(this@OrderDetailList, dataList)
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                DividerItemDecoration.VERTICAL
            )
        )
        recyclerView.setHasFixedSize(true)

        adapter.notifyDataSetChanged()

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // handle arrow click here
        if (item.itemId == android.R.id.home) {

            finish()
            overridePendingTransition(0, 0)

        }
        return super.onOptionsItemSelected(item)
    }

}



