package com.example.neostore.activities.reset_password

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.neostore.base.BaseClassActivity
import com.example.neostore.R
import com.example.neostore.activities.home_screen.HomeActivity
import com.example.neostore.activities.view_model.UserViewModel

class ResetPasswordActivity : BaseClassActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.reset_password_layout)
        lateinit var model: UserViewModel

        var mActionBarToolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbartable);
        setSupportActionBar(mActionBarToolbar);
        setEnabledTitle()
        var old_password = findViewById<EditText>(R.id.old_password)
        var new_password = findViewById<EditText>(R.id.new_password)
        var confirm_password = findViewById<EditText>(R.id.confirm_new_password)

        val resetpwdbutton = findViewById<Button>(R.id.resetpwdbtn)

        model = ViewModelProvider(this)[UserViewModel::class.java]
        model.resetpwd.observe(this, object : Observer<ResetReponseBase?> {
            override fun onChanged(t: ResetReponseBase?) {

                val intent = Intent(applicationContext, HomeActivity::class.java)
                startActivity(intent)
                finish()
            }

        })
        resetpwdbutton.setOnClickListener {

            val old = old_password.text.toString().trim()
            val new = new_password.text.toString().trim()
            val confirm = confirm_password.text.toString().trim()
            if (old.isEmpty()) {

                old_password.error = "old password is empty"
                old_password.requestFocus()
                return@setOnClickListener
            }
          else  if (new.isEmpty()) {
                new_password.error = "new password is empty"
                new_password.requestFocus()
                return@setOnClickListener
            }
          else  if (confirm.isEmpty()) {
                confirm_password.error = "confirm  password is empty"
                confirm_password.requestFocus()
                return@setOnClickListener
            }
            else{
                model.resetPwd(old,new,confirm)
            }
        }
    }
}
