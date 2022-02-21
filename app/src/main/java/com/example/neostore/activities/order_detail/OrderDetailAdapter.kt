package com.example.neostore.activities.order_detail

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.neostore.R

class OrderDetailAdapter(var context: Context, var tablelist: List<OrderDetailRes>) :
    RecyclerView.Adapter<OrderDetailAdapter.MyViewHolder>() {

    init {
        this.context = context
        this.tablelist = tablelist
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.order_detail_item, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return tablelist.size

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.productname.text = tablelist.get(position).prod_name.toString()
        Glide.with(context).load(tablelist.get(position).prod_image)
            .into(holder.productimage)
        holder.productcategory.text = "(" + tablelist.get(position).prod_cat_name + ")"
        holder.productcost.text = tablelist.get(position).total.toString()
        holder.quantity.text = tablelist.get(position).quantity.toString()
        Log.e("checkkkkk", tablelist.get(position).id.toString())


    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val productname: TextView
        val productimage: ImageView
        val productcategory: TextView
        val productcost: TextView
        val quantity: TextView

        init {

            productname = itemView.findViewById(R.id.name)
            productimage = itemView.findViewById(R.id.productimg)
            productcategory = itemView.findViewById(R.id.productcategory)
            productcost = itemView.findViewById(R.id.cost)
            quantity = itemView.findViewById(R.id.quantityno)
        }

    }
}