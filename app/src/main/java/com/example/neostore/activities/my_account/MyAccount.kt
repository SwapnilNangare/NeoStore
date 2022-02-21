package com.example.neostore.activities.my_account

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
import com.example.neostore.R
import com.example.neostore.activities.reset_password.ResetPasswordActivity
import com.example.neostore.base.BaseClassActivity

class MyAccount : BaseClassActivity() {
    private val mActionBarToolbar by lazy { findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbartable) }
    private val resetButton by lazy { findViewById<Button>(R.id.resetpwd) }
    private val editButton by lazy { findViewById<Button>(R.id.editdetail) }
    private val firstName by lazy { findViewById<TextView>(R.id.firstname) }
    private val lastName by lazy { findViewById<TextView>(R.id.lastname) }
    private val emailUser by lazy { findViewById<TextView>(R.id.emailuser) }
    private val phoneNo by lazy { findViewById<TextView>(R.id.phone_no) }
    private val birthday by lazy { findViewById<TextView>(R.id.birthday) }
    private val image by lazy { findViewById<ImageView>(R.id.imageprofile) }
    lateinit var model: MyAccountViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.my_account)
        setSupportActionBar(mActionBarToolbar)
        setEnabledTitle()
        model = ViewModelProvider(this)[MyAccountViewModel::class.java]
        resetButton.setOnClickListener {
            val i = Intent(applicationContext, ResetPasswordActivity::class.java)
            startActivity(i)
        }
        editButton.setOnClickListener {
            val i = Intent(applicationContext, EditProfile::class.java)
            startActivity(i)
        }
        model.accountResponseData.observe(this, object : Observer<MyAccountBaseResponse?> {
            override fun onChanged(t: MyAccountBaseResponse?) {
                firstName.setText(t?.data?.user_data?.first_name)
                lastName.setText(t?.data?.user_data?.last_name)
                emailUser.setText(t?.data?.user_data?.email)
                phoneNo.setText(t?.data?.user_data?.phone_no).toString()
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
