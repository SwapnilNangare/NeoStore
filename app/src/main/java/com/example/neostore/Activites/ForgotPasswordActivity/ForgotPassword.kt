package com.example.neostore.Activites.ForgotPasswordActivity

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.neostore.Activites.ForgotPasswordActivity.model.ForgotResponse
import com.example.neostore.Activites.LoginScreen.*
import com.example.neostore.Activites.ViewModel.UserViewModel
import com.example.neostore.Base.BaseClassActivity
import com.example.neostore.R

class ForgotPassword : BaseClassActivity() {
    private lateinit var model: UserViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.forgotpassword)

        var forgotemailedt =findViewById<EditText>(R.id.forgotemailedt)
        var sendmailforgot =findViewById<Button>(R.id.sendmailforgot)
        model = ViewModelProvider(this)[UserViewModel::class.java]

        model.ForgotPasswordData.observe(this, object : Observer<ForgotResponse?> {
            override fun onChanged(t: ForgotResponse?) {


                       }

        })
        sendmailforgot.setOnClickListener {
            val email = forgotemailedt.text.toString().trim()

            if (email.isEmpty()) {
               showToast(applicationContext,"Data is missing")
                forgotemailedt.error = "Email required"
                forgotemailedt.requestFocus()
                return@setOnClickListener
            }
            model.loadForgot(email)


        }
    }
    override fun onBackPressed() {
        super.onBackPressed()
    }
}