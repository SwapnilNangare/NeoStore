package com.example.neostore.activities.product_details

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.neostore.R


class CustomDetailAdapter(context: Context, dataList: List<ProductImagesResponse>): RecyclerView.Adapter<CustomDetailAdapter.CustomViewHolder>() {
    private val dataList: List<ProductImagesResponse>
    private val context: Context

    private var selectedPos: Int = 0

    inner class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mView: View
        val avatar: ImageView
        val relativeitem: RelativeLayout

        init {
            mView = itemView
            avatar = mView.findViewById(R.id.view3)
            relativeitem = mView.findViewById(R.id.relativeitem)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view: View = layoutInflater.inflate(R.layout.product_detail_item, parent, false)
        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, @SuppressLint("RecyclerView") position: Int) {
        ///  holder.relativeitem.setBackgroundColor(Color.parseColor("#000000"));
        holder.relativeitem.setSelected(selectedPos == position);
        Glide.with(context).load(dataList[position].image)
            .thumbnail(0.5f)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(holder.avatar)


        holder.avatar.setOnClickListener(View.OnClickListener {
            notifyItemChanged(selectedPos)
            selectedPos = position
            notifyItemChanged(selectedPos)


            val intent = Intent("custom-message")

            intent.putExtra("item", dataList.get(position).image)
            LocalBroadcastManager.getInstance(context).sendBroadcast(intent)
        })
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    init {
        this.context = context
        this.dataList = dataList
    }


}

