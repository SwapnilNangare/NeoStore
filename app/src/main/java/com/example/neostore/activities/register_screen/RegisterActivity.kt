package com.example.neostore.activities.register_screen

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NavUtils
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.neostore.R
import com.example.neostore.activities.home_screen.HomeActivity
import com.example.neostore.activities.login_screen.LoginActivity
import com.example.neostore.activities.login_screen.model.LoginResponse
import com.example.neostore.activities.view_model.UserViewModel
import kotlinx.android.synthetic.main.register_activity.*


class RegisterActivity : AppCompatActivity() {
    lateinit var model: UserViewModel

    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_activity)

        val toolbar: ImageView = findViewById(R.id.toolbar)


        toolbar.setOnClickListener {
            NavUtils.navigateUpFromSameTask(this)

        }
        // add back arrow to toolbar
        if (getSupportActionBar() != null) {
            getSupportActionBar()?.setDisplayHomeAsUpEnabled(true);
            getSupportActionBar()?.setDisplayShowHomeEnabled(true);
        }

        model = ViewModelProvider(this)[UserViewModel::class.java]
        model.registerResponseData.observe(this, object : Observer<LoginResponse?> {
            override fun onChanged(t: LoginResponse?) {
                val intent = Intent(this@RegisterActivity, HomeActivity::class.java)
                startActivity(intent)
                finish()
            }

        })
        registerbtn.setOnClickListener {

            val firstName = registerfirstname.text.toString().trim()
            val lastName = registerlastname.text.toString().trim()
            val email = emailregister.text.toString().trim()
            val password = regpass.text.toString().trim()
            val confirmPassword = confregpass.text.toString().trim()
            var checkedId = 1
            val selectedId: Int = radioGrp.checkedRadioButtonId
            var gender = if (selectedId != -1) {
                val selectedRadioButton =
                    findViewById<RadioButton>(selectedId)
                selectedRadioButton.text.toString()
            } else {
                ""
            }
            val phone_no = phonereg.text.toString().trim()


            if (firstName.isEmpty()) {
                registerfirstname.error = "Firstname required"
                registerfirstname.requestFocus()
                return@setOnClickListener
            }

            if (lastName.isEmpty()) {
                registerlastname.error = "Lastname required"
                registerlastname.requestFocus()
                return@setOnClickListener
            }

            if (email.isEmpty()) {
                emailregister.error = "email required"
                emailregister.requestFocus()
                return@setOnClickListener
            }

            if (password.isEmpty()) {
                regpass.error = "password required"
                regpass.requestFocus()
                return@setOnClickListener
            }

            if (confirmPassword.isEmpty()) {
                confregpass.error = "confirm_password required"
                confregpass.requestFocus()
                return@setOnClickListener
            }

            if (phone_no.isEmpty()) {
                phonereg.error = "phoneno required"
                confregpass.requestFocus()
                return@setOnClickListener
            }

            model.loadReg(
                firstName,
                lastName,
                email,
                password,
                confirmPassword,
                gender,
                phone_no
            )
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
        val intent = Intent(this, LoginActivity::class.java)
        startActivityForResult(intent, 2)
        super.onBackPressed()
    }
}