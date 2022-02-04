package com.example.neostore.Activites.MyAccount

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.neostore.Activites.SharedPrefManager.SharedPrefManager
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyAccountViewModel(context: Application) : AndroidViewModel(context) {
    private val _accountResponseData = MutableLiveData<Myaccountbaseresponse?>()
    val accountResponseData: MutableLiveData<Myaccountbaseresponse?>
        get() = _accountResponseData

    init {
        loadAccountData()
    }

    fun loadAccountData() {
        val token: String =
            SharedPrefManager.getInstance(getApplication()).user.access_token.toString()
        MyAccountApiManager.instance.fetchUser(token)
            .enqueue(object : Callback<Myaccountbaseresponse> {
                override fun onFailure(call: Call<Myaccountbaseresponse>, t: Throwable) {
                    Log.d("res", "" + t)
                    _accountResponseData.value = null
                }

                override fun onResponse(
                    call: Call<Myaccountbaseresponse>,
                    response: Response<Myaccountbaseresponse>
                ) {
                    var res = response

                    if (res.body()?.status == 200) {
                        _accountResponseData.value = response.body()
                    } else {
                        try {
                            val jObjError =
                                JSONObject(response.errorBody()!!.string())
                            Toast.makeText(
                                getApplication(),
                                jObjError.getString("user_msg"),
                                Toast.LENGTH_LONG
                            ).show()
                        } catch (e: Exception) {
                            Log.e("errorrr", e.message)
                        }
                    }
                }
            })
    }

}

