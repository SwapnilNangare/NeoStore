package com.example.neostore.activities.address

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnChildAttachStateChangeListener
import com.example.neostore.R
import com.example.neostore.activities.address.room.Address
import com.example.neostore.activities.api_manager.ApiManager
import com.example.neostore.activities.forgot_password.model.ForgotResponse
import com.example.neostore.activities.my_cart.AddToCart
import com.example.neostore.activities.shared_pref_manager.SharedPrefManager
import com.example.neostore.base.BaseClassActivity
import kotlinx.android.synthetic.main.address.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddressActivity : BaseClassActivity() {
    private lateinit var data: LiveData<MutableList<Address>>
    var adapter: AddressAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.address)
        var mActionBarToolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbartable);
        setSupportActionBar(mActionBarToolbar);
        setScreenTitle("Address List")
        mActionBarToolbar.setNavigationOnClickListener(View.OnClickListener {
            onBackPressed()
        })
        addbutton.findViewById<View>(R.id.addbutton).setOnClickListener {
            val intent = Intent(this, AddAddressActivity::class.java)
            startActivity(intent)
        }
        LocalBroadcastManager.getInstance(this).registerReceiver(
            mMessageReceiver,
            IntentFilter("custom-message1")
        )
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val adapter = AddressAdapter(applicationContext)

        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                DividerItemDecoration.VERTICAL
            )
        )

        recyclerView.addOnChildAttachStateChangeListener(object : OnChildAttachStateChangeListener {
            override fun onChildViewAttachedToWindow(view: View) {
                emptytext.setVisibility(View.INVISIBLE)
            }

            override fun onChildViewDetachedFromWindow(view: View) {
                emptytext.setVisibility(View.VISIBLE)
            }
        })
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                Log.e("RecyclerView", "onScrollStateChanged")
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
            }
        })
        val application = application as CustomApplication
        data = application.database.AddressDao().getAddressesWithChanges()

        data.observe(this, Observer { words1 ->

            if (words1.size > 0) {
                ordernow.setVisibility(View.VISIBLE)

                emptytext.setVisibility(View.GONE)

            } else {
                ordernow.setVisibility(View.GONE)

                emptytext.setVisibility(View.VISIBLE)

            }

            words1?.let { adapter.updateData(it) }
        })


    }
    var mMessageReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent) {
            val token: String = SharedPrefManager.getInstance(applicationContext).user.access_token.toString()
            val itemName = intent.getStringExtra("item1")
            ordernow.setOnClickListener {

                if (itemName != null) {
                    ApiManager.instance.orderNow(token, itemName).enqueue(object : Callback<ForgotResponse> {
                            override fun onFailure(call: Call<ForgotResponse>, t: Throwable) {
                                Log.d("res", "" + t)
                            }

                            override fun onResponse(call: Call<ForgotResponse>, response: Response<ForgotResponse>) {
                                var res = response
                                if (res.body()?.status == 200) {
                                    Log.d("response check ", "" + response.body()?.status.toString())
                                    showToast(applicationContext, res.body()?.user_msg)
                                    val i = Intent(applicationContext, AddToCart::class.java)
                                    startActivity(i)
                                    Log.d("swapnil", response.body()?.user_msg.toString())
                                } else {
                                    try {
                                        val jObjError = JSONObject(response.errorBody()!!.string())

                                        showToast(applicationContext, jObjError.getString("user_msg")
                                        )

                                    } catch (e: Exception) {
                                        showToast(applicationContext, e.message)
                                        e.message?.let { it1 -> Log.e("errorrr", it1) }
                                    }
                                }
                            }
                        })
                }
            }
        }
    }

}