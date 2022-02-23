package com.example.neostore.activities.my_cart

import android.annotation.SuppressLint
import android.app.Activity
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.daimajia.swipe.SwipeLayout
import com.daimajia.swipe.SwipeLayout.SwipeListener
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter
import com.example.neostore.R
import com.example.neostore.activities.api_manager.ApiManager
import com.example.neostore.activities.shared_pref_manager.SharedPrefManager
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CartAdapter(private val context: Context, private val dataList: MutableList<DataCart?>?) :
    RecyclerSwipeAdapter<CartAdapter.CustomViewHolder>(),
    AdapterView.OnItemSelectedListener { //added RecyclerSwipeAdapter and override

    var country = arrayOf(1, 2, 3, 4, 5, 6, 7, 8)
    var progressDialog: ProgressDialog? = null


    inner class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mView: View
        val swipeLayout: SwipeLayout
        val productImage: ImageView
        val productName: TextView
        val productCategory: TextView
        val productPrice: TextView
        val tvDelete: TextView
        val spin: Spinner

        init {
            mView = itemView
            productImage = mView.findViewById(R.id.imagecart)
            productName = mView.findViewById(R.id.imagenamecart)
            productCategory = mView.findViewById(R.id.imagecategory)
            productPrice = mView.findViewById(R.id.price)
            swipeLayout = mView.findViewById(R.id.swipe)
            tvDelete = mView.findViewById(R.id.tvDelete)
            spin = mView.findViewById(R.id.spinner) as Spinner

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view: View = layoutInflater.inflate(R.layout.add_to_cart_item, parent, false)

        return CustomViewHolder(view)
    }

    override fun getSwipeLayoutResourceId(position: Int): Int {
        return R.id.swipe

    }

    override fun onBindViewHolder(holder: CustomViewHolder, @SuppressLint("RecyclerView") position: Int) {
        val progressDialog: ProgressDialog = ProgressDialog(context);
        holder.productName.text = dataList?.get(position)?.product?.name ?: null
        holder.productCategory.text = "(" + dataList?.get(position)?.product?.product_category + ")"

        holder.productPrice.text = dataList?.get(position)?.product?.cost.toString()
        Glide.with(context).load(dataList?.get(position)?.product?.product_images).into(holder.productImage)

        holder.swipeLayout.setShowMode(SwipeLayout.ShowMode.PullOut)
        Log.e("checkidd", dataList?.get(position)?.product?.id.toString())
        // Drag From Right

        // Drag From Right
        holder.swipeLayout.addDrag(SwipeLayout.DragEdge.Right, holder.swipeLayout.findViewById(R.id.bottom_wrapper))
        val id = dataList?.get(position)?.product?.id

        holder.swipeLayout.addSwipeListener(object : SwipeListener {
            override fun onClose(layout: SwipeLayout) {
                //when the SurfaceView totally cover the BottomView.
            }

            override fun onUpdate(layout: SwipeLayout, leftOffset: Int, topOffset: Int) {
                //you are swiping.
            }

            override fun onStartOpen(layout: SwipeLayout) {}
            override fun onOpen(layout: SwipeLayout) {
            }

            override fun onStartClose(layout: SwipeLayout) {}
            override fun onHandRelease(
                layout: SwipeLayout,
                xvel: Float,
                yvel: Float
            ) {
            }
        })


        holder.swipeLayout.getSurfaceView()
            .setOnClickListener(View.OnClickListener {
            })

        val aa: ArrayAdapter<*> = ArrayAdapter<Any?>(context, android.R.layout.simple_spinner_item, country)
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        holder.spin.setAdapter(aa)
        holder.spin.setSelection(dataList?.get(position)?.quantity!! - 1)

        holder.spin.setSelection(dataList?.get(position)?.quantity!! - 1, false)

        holder.spin.setOnItemSelectedListener(object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position1: Int,
                id: Long
            ) {
                progressDialog.show()

                val id = dataList?.get(position)!!.product.id

                val token: String = SharedPrefManager.getInstance(context).user.access_token.toString()
                ApiManager.instance4.editCart(token, id, country[position1])
                    .enqueue(object : Callback<DeleteResponse> {
                        override fun onFailure(call: Call<DeleteResponse>, t: Throwable) {
                            Log.d("res", "" + t)
                        }

                        override fun onResponse(
                            call: Call<DeleteResponse>,
                            response: Response<DeleteResponse>
                        ) {
                            progressDialog.dismiss()
                            (context as Activity).finish()

                            var res = response
                            Log.e("checkres", res.toString())
                            Log.d("response check ", "" + response.body()?.status.toString())
                            if (res.isSuccessful) {
                                Toast.makeText(
                                    context,
                                    res.body()?.user_msg,
                                    Toast.LENGTH_LONG
                                ).show()
                                progress()

                                Log.d("kjsfgxhufb", response.body()?.user_msg.toString())
                            } else {
                                try {
                                    val jObjError =
                                        JSONObject(response.errorBody()!!.string())
                                    Toast.makeText(
                                        context,
                                        jObjError.getString("message") + jObjError.getString("user_msg"),
                                        Toast.LENGTH_LONG
                                    ).show()
                                } catch (e: Exception) {
                                    Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
                                    Log.e("errorrr", e.message)
                                }
                            }
                        }
                    })
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // todo for nothing selected
            }
        })
        holder.tvDelete.setOnClickListener(View.OnClickListener { view ->
            progressDialog.show()

            val token: String = SharedPrefManager.getInstance(context).user.access_token.toString()
            ApiManager.instance4.deleteCart(token, id!!)
                .enqueue(object : Callback<DeleteResponse> {
                    override fun onFailure(call: Call<DeleteResponse>, t: Throwable) {

                        Log.d("res", "" + t)

                    }

                    override fun onResponse(
                        call: Call<DeleteResponse>,
                        response: Response<DeleteResponse>
                    ) {
                        var res = response
                        progressDialog.dismiss()
                        (context as Activity).finish()

                        if (res.body()?.status == 200) {
                            Toast.makeText(
                                context,
                                res.body()?.message,
                                Toast.LENGTH_LONG
                            ).show()
                            progress()

                            mItemManger.removeShownLayouts(holder.swipeLayout)
                            notifyItemChanged(position)
                            notifyItemRemoved(position)
                            dataList?.removeAt(position)
                            notifyItemRangeChanged(position, dataList?.size!!)
                            mItemManger.closeAllItems()
                        } else {
                            try {
                                val jObjError =
                                    JSONObject(response.errorBody()!!.string())
                                Toast.makeText(
                                    context,
                                    jObjError.getString("message") + jObjError.getString("user_msg"),
                                    Toast.LENGTH_LONG
                                ).show()
                            } catch (e: Exception) {
                                Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
                                Log.e("errorrr", e.message)
                            }
                        }
                    }
                })

            mItemManger.bindView(holder.itemView, position)

        })


    }

    override fun getItemCount() = dataList?.size ?: 0

    fun progress() {
        progressDialog?.dismiss()
        val intent = Intent(context.applicationContext, AddToCart::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_MULTIPLE_TASK
        context.applicationContext.startActivity(intent)
        (context as Activity?)!!.overridePendingTransition(0, 0)



    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

    }


}




