package com.example.neostore.Activites.MyAccount

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.NavUtils
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.neostore.Activites.ResetPassword.ResetPasswordActivity
import com.example.neostore.R
import com.example.neostore.Base.BaseClassActivity

class MyAccount : BaseClassActivity() {
    private val mActionBarToolbar by lazy { findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbartable) }
    private val resetbutton by lazy { findViewById<Button>(R.id.resetpwd) }
    private val editbutton by lazy { findViewById<Button>(R.id.editdetail) }
    private val first_name by lazy { findViewById<TextView>(R.id.firstname) }
    private val last_name by lazy { findViewById<TextView>(R.id.lastname) }
    private val emailuser by lazy { findViewById<TextView>(R.id.emailuser) }
    private val phone_no by lazy { findViewById<TextView>(R.id.phone_no) }
    private val birthday by lazy { findViewById<TextView>(R.id.birthday) }
    private val image by lazy { findViewById<ImageView>(R.id.imageprofile) }
    lateinit var model: MyAccountViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.myaccount)
        setSupportActionBar(mActionBarToolbar)
        setEnabledTitle()
        model = ViewModelProvider(this)[MyAccountViewModel::class.java]
        resetbutton.setOnClickListener {
            val i = Intent(applicationContext, ResetPasswordActivity::class.java)
            startActivity(i)
        }
        editbutton.setOnClickListener {
            val i = Intent(applicationContext, EditProfile::class.java)
            startActivity(i)
        }
        model.accountResponseData.observe(this, object : Observer<Myaccountbaseresponse?> {
            override fun onChanged(t: Myaccountbaseresponse?) {
                first_name.setText(t?.data?.user_data?.first_name)
                last_name.setText(t?.data?.user_data?.last_name)
                emailuser.setText(t?.data?.user_data?.email)
                phone_no.setText(t?.data?.user_data?.phone_no).toString()
                birthday.setText(t?.data?.user_data?.dob).toString()
                Glide.with(applicationContext)
                    .load(t?.data?.user_data?.profile_pic)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .into(image)
            }
        })


    }

    override fun onResume() {
        super.onResume()
        model.loadAccountData()
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
}
