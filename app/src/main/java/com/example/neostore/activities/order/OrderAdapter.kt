package com.example.neostore.activities.order

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.recyclerview.widget.RecyclerView
import com.example.neostore.R
import com.example.neostore.activities.order_detail.OrderDetailList

class OrderAdapter(var context: Context, var tablelist: List<OrderResponseData>) :
    RecyclerView.Adapter<OrderAdapter.MyViewHolder>() {

    init {
        this.context = context
        this.tablelist = tablelist
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.order_list_item,
            parent,
            false
        )
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return tablelist.size

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.orderid.text = tablelist.get(position).id.toString()
        holder.orderdate.text = tablelist.get(position).created
        holder.ordercost.text = tablelist.get(position).cost.toString()
        Log.e("checkkkkk", tablelist.get(position).id.toString())

        holder.itemView!!.setOnClickListener {


            val context: Context = holder.itemView.context

            val intent = Intent("custom-message1")
            intent.putExtra("itemid", tablelist.get(position).id.toString());
            LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
            val i = Intent(
                context,
                OrderDetailList::class.java
            )
            i.putExtra("id", tablelist.get(position).id.toString())
            Log.e("checkid", tablelist.get(position).id.toString())
            context.startActivity(i)
        }
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val orderid: TextView
        val orderdate: TextView
        val ordercost: TextView

        init {

            orderid = itemView.findViewById(R.id.ordertitle)
            orderdate = itemView.findViewById(R.id.orderdate)
            ordercost = itemView.findViewById(R.id.ordercost)
        }

    }
}