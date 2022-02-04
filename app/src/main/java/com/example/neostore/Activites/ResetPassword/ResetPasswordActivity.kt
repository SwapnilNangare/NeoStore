package com.example.neostore.Activites.ResetPassword

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.neostore.Base.BaseClassActivity
import com.example.neostore.R
import com.example.neostore.Activites.HomeScreenActivity.HomeActivity
import com.example.neostore.Activites.ViewModel.UserViewModel

class ResetPasswordActivity : BaseClassActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.resetpasswordlayout)
        lateinit var model: UserViewModel

        var mActionBarToolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbartable);
        setSupportActionBar(mActionBarToolbar);
        setEnabledTitle()
        var old_password = findViewById<EditText>(R.id.old_password)
        var new_password = findViewById<EditText>(R.id.new_password)
        var confirm_password =
            findViewById<EditText>(R.id.confirm_new_password)
        val resetpwdbutton = findViewById<Button>(R.id.resetpwdbtn)
        model = ViewModelProvider(this)[UserViewModel::class.java]
        model.Resetpwd.observe(this, object : Observer<Reset_Reponse_Base?> {
            override fun onChanged(t: Reset_Reponse_Base?) {

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
                model.resetpwd(old,new,confirm)
            }
        }
    }
}
