package com.example.neostore.activities.order

import android.os.Bundle
import android.view.MenuItem
import androidx.core.app.NavUtils
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.neostore.R
import com.example.neostore.base.BaseClassActivity

class OrderListActivity : BaseClassActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.table_activity)

        var mActionBarToolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbartable);
        setSupportActionBar(mActionBarToolbar);
        setScreenTitle("My Orders")

        val model = ViewModelProvider(this)[OrderListViewModel::class.java]

        model.orderList?.observe(this, object : Observer<OrderResponseBase> {
            override fun onChanged(t: OrderResponseBase?) {

                generateDataList(t?.data!!)
            }


        })
    }

    fun generateDataList(dataList: List<OrderResponseData>) {
        val recyclerView = findViewById<RecyclerView>(R.id.recyleview)
        val linear: LinearLayoutManager =
            LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = linear
        val adapter = OrderAdapter(this@OrderListActivity, dataList)
        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(true)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                DividerItemDecoration.VERTICAL
            )
        )

        adapter.notifyDataSetChanged()

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                NavUtils.navigateUpFromSameTask(this)

                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}
