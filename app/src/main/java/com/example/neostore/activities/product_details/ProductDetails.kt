package com.example.neostore.activities.product_details

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.*
import android.widget.RatingBar.OnRatingBarChangeListener
import androidx.appcompat.app.AlertDialog
import androidx.core.app.NavUtils
import androidx.core.content.FileProvider
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.target.CustomTarget
import com.example.neostore.BuildConfig
import com.example.neostore.R
import com.example.neostore.activities.api_manager.ApiManager
import com.example.neostore.activities.shared_pref_manager.SharedPrefManager
import com.example.neostore.base.BaseClassActivity
import kotlinx.android.synthetic.main.product_details_activity.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.io.FileOutputStream

class ProductDetails : BaseClassActivity() {
    private var rateValue = 0.0f
    var imagemain: ImageView? = null

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.product_details_activity)


        var mActionBarToolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbartable)
        setSupportActionBar(mActionBarToolbar);


        val descript: TextView = findViewById(R.id.descript)
        val intent1: Intent = getIntent()

        val id: Int = intent1.getIntExtra("id", -1)

        val name: String = intent1.getStringExtra("name")
        setScreenTitle(name)

        val rating: String = intent1.getStringExtra("rating")

        val productImages: String = intent1.getStringExtra("image")
        Log.e("product_images", productImages)
        Log.e("sdjhj", productImages)
        Log.e("checkname", name)


        val text = findViewById<TextView>(R.id.text1) as TextView
        val text2 = findViewById<TextView>(R.id.text2) as TextView
        val rateButton = findViewById<Button>(R.id.rate)
        val buyNow = findViewById<Button>(R.id.buynow)


        LocalBroadcastManager.getInstance(this)
            .registerReceiver(mMessageReceiver, IntentFilter("custom-message"))
        imagemain = findViewById<ImageView>(R.id.viewimage_main) as ImageView


        ApiManager.instance7.fetchUserDetail(id)
            .enqueue(object : Callback<ProductBaseResponse> {
                override fun onFailure(call: Call<ProductBaseResponse>, t: Throwable) {
                    Log.d("res", "" + t)

                }

                override fun onResponse(
                    call: Call<ProductBaseResponse>,
                    response: Response<ProductBaseResponse>
                ) {
                    var res = response

                    val ret: ProductDataResponse? = res.body()?.data
                    Log.e("checkdata", ret?.name.toString())
                    val ygd = ret?.name.toString()
                    text.setText(ygd)
                    textcost.setText(ret?.cost.toString())
                    text2.setText(ret?.producer.toString())
                    descript.setText(ret?.description)
                    myRatingBar.rating = rating.toFloat()
                    Glide.with(getApplicationContext())
                        .load(res?.body()!!.data.product_images.get(0).image).into(
                            imagemain!!
                        );

                    shareimage.setOnClickListener {
                        Glide.with(applicationContext).asBitmap()
                            .load(
                                res?.body()!!.data.product_images.get(
                                    0
                                ).image
                            )
                            .into(object : CustomTarget<Bitmap>() {

                                override fun onLoadCleared(placeholder: Drawable?) {

                                }

                                override fun onResourceReady(
                                    resource: Bitmap,
                                    transition: com.bumptech.glide.request.transition.Transition<in Bitmap>?
                                ) {


                                    val cachePath = File(applicationContext.cacheDir, "images")
                                    cachePath.mkdirs() // don't forget to make the directory
                                    val stream =
                                        FileOutputStream(cachePath.toString() + "/image.png") // overwrites this image every time
                                    resource.compress(Bitmap.CompressFormat.PNG, 100, stream)
                                    stream.close()

                                    val imagePath = File(applicationContext.cacheDir, "images")
                                    val newFile = File(imagePath, "image.png")
                                    val contentUri: Uri = FileProvider.getUriForFile(
                                        applicationContext,
                                        "${BuildConfig.APPLICATION_ID}.provider",
                                        newFile
                                    )

                                    val intent = Intent(Intent.ACTION_SEND)
                                    intent.type = "image/*"
                                    intent.putExtra(Intent.EXTRA_STREAM, contentUri)
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

                                    startActivity(
                                        Intent.createChooser(
                                            intent,
                                            "Choose..."
                                        )
                                    )

                                }
                            })

                    }


                    val bundle: Bundle = getIntent().getExtras()!!

                    val imgUrl: String = bundle.getString("image")!!
                    val imageUri = Uri.parse(imgUrl)

                    Log.e("imgUrl", imgUrl)
                    Log.e("imageUri", imageUri.toString())


                    val retro11: List<ProductImagesResponse> =
                        response?.body()!!.data.product_images
                    generateDataList(retro11)
                    rateButton.setOnClickListener {
                        rateButton.setBackgroundResource(R.drawable.mybuttonred)
                        // change to original after 5 secs.
                        Handler().postDelayed(
                            Runnable {
                                rateButton.setBackgroundResource(R.drawable.mybutton)
                            },
                            1000
                        )
                        val mBuild: AlertDialog.Builder = AlertDialog.Builder(this@ProductDetails)
                        val mView: View = layoutInflater.inflate(R.layout.rate_dialog, null)

                        val ratebar = mView.findViewById(R.id.ratingBaruser) as RatingBar

                        val titleimage = mView.findViewById<TextView>(R.id.titleimage) as TextView
                        val imagerate = mView.findViewById<ImageView>(R.id.imagerate) as ImageView
                        titleimage.setText(ygd)
                        Glide.with(getApplicationContext()).load(
                            res?.body()!!.data.product_images.get(
                                0
                            ).image
                        ).into(imagerate);
                        ratebar.setRating(getDefaults("numStars", applicationContext))
                        ratebar.onRatingBarChangeListener =
                            OnRatingBarChangeListener { ratingBar, rating1, fromUser ->
                                setDefaults("numStars", rating1, applicationContext)
                                rateValue = rating1
                            }


                        mBuild.setView(mView)
                        val dialog: AlertDialog = mBuild.create()

                        val btnSubmit =
                            mView.findViewById(R.id.btnSubRating) as Button
                        btnSubmit.setOnClickListener(object : View.OnClickListener {
                            override fun onClick(v: View?) {
                                ApiManager.instance7.setRating(id, rateValue)
                                    .enqueue(object : Callback<RateResponse> {
                                        override fun onFailure(
                                            call: Call<RateResponse>,
                                            t: Throwable
                                        ) {
                                            Log.d("res", "" + t)
                                        }

                                        override fun onResponse(
                                            call: Call<RateResponse>,
                                            response: Response<RateResponse>
                                        ) {
                                            var res = response
                                            if (res.isSuccessful) {
                                                showToast(applicationContext, res.body()?.user_msg)
                                                dialog.dismiss()
                                            } else {
                                                try {
                                                    val jObjError =
                                                        JSONObject(response.errorBody()!!.string())
                                                    showToast(
                                                        applicationContext, jObjError.getString(
                                                            "user_msg"
                                                        )
                                                    )
                                                } catch (e: Exception) {
                                                    showToast(applicationContext, e.message)
                                                    Log.e("errorrr", e.message)
                                                }
                                            }
                                        }
                                    })

                            }
                        })
                        dialog.show()
                        //  dialog.getWindow()?.setLayout(1000, 1500);
                        dialog.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));


                    }
                    buyNow.setOnClickListener {
                        buyNow.setBackgroundResource(R.drawable.mybuttonred)
                        // change to original after 5 secs.
                        Handler().postDelayed(
                            Runnable {
                                buyNow.setBackgroundResource(R.drawable.mybutton)
                            },
                            1000
                        )

                        val mBuild: AlertDialog.Builder = AlertDialog.Builder(this@ProductDetails)
                        val mView: View = layoutInflater.inflate(R.layout.buy_now_dialog, null)

                        val titleimage = mView.findViewById<TextView>(R.id.titleimage2) as TextView
                        val imagerate = mView.findViewById<ImageView>(R.id.imagebuy) as ImageView
                        titleimage.setText(ygd)
                        Glide.with(getApplicationContext()).load(
                            res?.body()!!.data.product_images.get(
                                0
                            ).image
                        ).into(imagerate);


                        val buynumber = mView.findViewById<EditText>(R.id.editbuy)

                        val btnSubmit =
                            mView.findViewById(R.id.btnbuynow) as Button
                        Log.e("checkid", id.toString())


                        mBuild.setView(mView)
                        val dialog: AlertDialog = mBuild.create()

                        btnSubmit.setOnClickListener(object : View.OnClickListener {
                            override fun onClick(v: View?) {
                                val value: String = buynumber.getText().toString()

                                if (value.isNotEmpty()) {
                                    val finalValue = value.toInt()
                                    val token: String =
                                        SharedPrefManager.getInstance(
                                            applicationContext
                                        ).user.access_token.toString()
                                    ApiManager.instance7.buyNow(token, id, finalValue)
                                        .enqueue(object : Callback<ResponseBaseCartAdd> {
                                            override fun onFailure(
                                                call: Call<ResponseBaseCartAdd>,
                                                t: Throwable
                                            ) {
                                                Log.d("res", "" + t)


                                            }

                                            override fun onResponse(
                                                call: Call<ResponseBaseCartAdd>,
                                                response: Response<ResponseBaseCartAdd>
                                            ) {
                                                var res = response
                                                Log.e("checkres", res.toString())
                                                Log.d(
                                                    "response check ",
                                                    "" + response.body()?.status.toString()
                                                )
                                                if (res.isSuccessful) {
                                                    showToast(
                                                        applicationContext,
                                                        res.body()?.user_msg
                                                    )
                                                    dialog.dismiss()
                                                    Log.d(
                                                        "kjsfgxhufb",
                                                        response.body()?.user_msg.toString()
                                                    )
                                                } else {
                                                    try {
                                                        val jObjError =
                                                            JSONObject(
                                                                response.errorBody()!!.string()
                                                            )
                                                        showToast(
                                                            applicationContext, jObjError.getString(
                                                                "user_msg"
                                                            )
                                                        )
                                                    } catch (e: Exception) {
                                                        showToast(applicationContext, e.message)
                                                        Log.e("errorrr", e.message)
                                                    }
                                                }
                                            }
                                        })

                                } else {
                                    buynumber.error = "field is required"
                                    buynumber.requestFocus()
                                }

                            }
                        })
                        dialog.show()
                        dialog.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));

                    }

                }
            })


    }


    fun generateDataList(dataList: List<ProductImagesResponse>) {
        val recyclerView = findViewById<RecyclerView>(R.id.Recycleviewdetails)
        val adapter = CustomDetailAdapter(applicationContext, dataList)
        recyclerView.adapter = adapter
        val linear: LinearLayoutManager =
            LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.layoutManager = linear
        recyclerView.setHasFixedSize(true)
    }

    var mMessageReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent) {
            // Get extra data included in the Intent

            val itemName = intent.getStringExtra("item")
            Glide.with(applicationContext).load(itemName)
                .thumbnail(0.5f)

                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imagemain!!);
            shareimage.setOnClickListener {
                Glide.with(applicationContext).asBitmap().load(itemName)
                    .into(object : CustomTarget<Bitmap>() {

                        override fun onLoadCleared(placeholder: Drawable?) {
                        }

                        override fun onResourceReady(
                            resource: Bitmap,
                            transition: com.bumptech.glide.request.transition.Transition<in Bitmap>?
                        ) {


                            val cachePath = File(applicationContext.cacheDir, "images")
                            cachePath.mkdirs() // don't forget to make the directory
                            val stream =
                                FileOutputStream(cachePath.toString() + "/image.png") // overwrites this image every time
                            resource.compress(Bitmap.CompressFormat.PNG, 100, stream)
                            stream.close()

                            val imagePath = File(applicationContext.cacheDir, "images")
                            val newFile = File(imagePath, "image.png")
                            val contentUri: Uri = FileProvider.getUriForFile(
                                applicationContext,
                                "${BuildConfig.APPLICATION_ID}.provider",
                                newFile
                            )

                            val intent = Intent(Intent.ACTION_SEND)
                            intent.type = "image/*"
                            intent.putExtra(Intent.EXTRA_STREAM, contentUri)
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

                            startActivity(
                                Intent.createChooser(
                                    intent,
                                    "Choose..."
                                )
                            )

                        }
                    })

            }

        }
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