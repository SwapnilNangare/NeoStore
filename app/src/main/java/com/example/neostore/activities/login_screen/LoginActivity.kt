package com.example.neostore.activities.login_screen

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.neostore.activities.login_screen.model.LoginResponse
import com.example.neostore.activities.register_screen.RegisterActivity
import com.example.neostore.activities.view_model.UserViewModel
import com.example.neostore.activities.forgot_password.ForgotPassword
import com.example.neostore.activities.home_screen.HomeActivity
import com.example.neostore.R
import kotlinx.android.synthetic.main.login_activity.*


class LoginActivity : AppCompatActivity() {
    lateinit var model: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)

        val button = findViewById<ImageView>(R.id.plusbutton)
        val forgotpassword = findViewById<TextView>(R.id.forgotpassword)
        button.setOnClickListener {
            val i = Intent(applicationContext, RegisterActivity::class.java)
            startActivity(i)
        }
        forgotpassword.setOnClickListener {
            val i = Intent(applicationContext, ForgotPassword::class.java)
            startActivity(i)
        }
        model = ViewModelProvider(this)[UserViewModel::class.java]
        model.loginResponseData.observe(this, object : Observer<LoginResponse?> {
            override fun onChanged(t: LoginResponse?) {
                val intent = Intent(this@LoginActivity, HomeActivity::class.java)

                startActivity(intent)
                finish()
            }

        })
        loginbtn.setOnClickListener {
            val email = loginuser.text.toString().trim()
            val password = loginpassword.text.toString().trim()

            if (email.isEmpty()) {
                Toast.makeText(
                    applicationContext, "Data is missing", Toast.LENGTH_LONG
                ).show()
                loginuser.error = "Email required"
                loginuser.requestFocus()
                return@setOnClickListener
            } else if (password.isEmpty()) {
                loginpassword.error = "Password required"
                loginpassword.requestFocus()
                return@setOnClickListener
            } else {
                model.loadLogin(email, password)


            }
        }
    }
}
