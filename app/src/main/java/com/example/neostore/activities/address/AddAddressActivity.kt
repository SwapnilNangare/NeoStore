package com.example.neostore.activities.address

import android.os.Bundle
import android.view.MenuItem
import android.widget.EditText
import androidx.core.app.NavUtils
import androidx.lifecycle.lifecycleScope
import com.example.neostore.activities.address.room.Address
import com.example.neostore.base.BaseClassActivity
import com.example.neostore.R
import kotlinx.android.synthetic.main.add_address.*
import kotlinx.coroutines.launch


class  AddAddressActivity : BaseClassActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_address)

        var mActionBarToolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbartable);
        setSupportActionBar(mActionBarToolbar);
        setScreenTitle("Add Address")

        val editText: EditText = findViewById(R.id.longaddress)

        val application = application as CustomApplication

        saveaddress.setOnClickListener {
            val address = Address()
            address.address = editText.getText().toString()


            val longaddresss = longaddress.text.toString().trim()

            if (longaddresss.isEmpty()) {
                showToast(applicationContext,"address is missing")
                longaddress.error = "address required"
                longaddress.requestFocus()
                return@setOnClickListener
            }


            lifecycleScope.launch {
                 application.addressDao.addData(address)
                finish()
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