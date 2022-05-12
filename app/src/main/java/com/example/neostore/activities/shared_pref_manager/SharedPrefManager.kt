package com.example.neostore.activities.shared_pref_manager

import android.content.Context
import com.example.neostore.activities.login_screen.model.Data

class SharedPrefManager private constructor(private val sn: Context) {

    val isLoggedIn: Boolean
        get() {
            val sharedPreferences = sn.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
            return sharedPreferences.getInt("id", -1) != -1
        }

    val user: Data
        get() {
            val sharedPreferences = sn.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
            return Data(
                sharedPreferences.getInt("id", -1),
                sharedPreferences.getInt("role_id", 0),
                sharedPreferences.getString("first_name", null),
                sharedPreferences.getString("last_name", null),
                sharedPreferences.getString("email", null),
                sharedPreferences.getString("username", null),
                sharedPreferences.getString("profile_pic", null),
                sharedPreferences.getString("country_id", null),
                sharedPreferences.getString("gender", null),
                sharedPreferences.getString("phone_no", null).toString(),
                sharedPreferences.getString("dob", null).toString(),
                sharedPreferences.getBoolean("is_active", false),
                sharedPreferences.getString("modified", null),
                sharedPreferences.getString("created", null),
                sharedPreferences.getString("access_token", null)


            )
        }


    fun saveUser(user: Data) {

        val sharedPreferences = sn.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        editor.putInt("id", user.id)
        editor.putInt("role_id", user.role_id)
        editor.putString("first_name", user.first_name)
        editor.putString("last_name", user.last_name)
        editor.putString("email", user.email)
        editor.putString("username", user.username)
        editor.putString("profile_pic", user.profile_pic)
        editor.putString("country_id", user.country_id)
        editor.putString("gender", user.gender)
        editor.putString("phone_no", user.phone_no)
        editor.putString("dob", user.dob)
        editor.putBoolean("is_active", user.is_active)
        editor.putString("modified", user.modified)
        editor.putString("created", user.created)
        editor.putString("access_token", user.access_token)

        editor.apply()
        //return boolean value commit() returns a boolean value of success or failure immediately by
        //writing data synchronously

    }

    fun clear() {
        val sharedPreferences = sn.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.clear() // It delete only values (means that keys are available) or it delete key value pair.
        editor.apply()


    }

    companion object {
        private val SHARED_PREF_NAME = "my_shared_preff"
        private var mInstance: SharedPrefManager? = null

        @Synchronized
        fun getInstance(mCtx: Context): SharedPrefManager {
            if (mInstance == null) {
                mInstance = SharedPrefManager(mCtx)
            }
            return mInstance as SharedPrefManager
        }
    }

}