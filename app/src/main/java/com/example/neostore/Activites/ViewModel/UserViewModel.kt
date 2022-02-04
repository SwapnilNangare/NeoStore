package com.example.neostore.Activites.ViewModel

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.example.neostore.Activites.ForgotPasswordActivity.ForgotPasswordManager
import com.example.neostore.Activites.ForgotPasswordActivity.model.ForgotResponse
import com.example.neostore.Activites.LoginScreen.LoginApiManager
import com.example.neostore.Activites.LoginScreen.model.LoginResponse
import com.example.neostore.Activites.RegisterScreen.RegisterApiManager
import com.example.neostore.Activites.ResetPassword.ErrorUtils
import com.example.neostore.Activites.ResetPassword.ResetPasswordApiManager
import com.example.neostore.Activites.ResetPassword.Reset_Reponse_Base
import com.example.neostore.Activites.SharedPrefManager.SharedPrefManager
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserViewModel(context: Application, private val savedStateHandle: SavedStateHandle) :
    AndroidViewModel(context) {

    private var _loginResponseData = MutableLiveData<LoginResponse?>()
    private var _regResponseData = MutableLiveData<LoginResponse?>()
    private var _forgotPassword = MutableLiveData<ForgotResponse?>()
    private var _resetPassword = MutableLiveData<Reset_Reponse_Base?>()


    val firstname: MutableLiveData<String> = savedStateHandle.getLiveData("firstname", "")
    val lastname: MutableLiveData<String> = savedStateHandle.getLiveData("lastname", "")
    val user: MutableLiveData<String> = savedStateHandle.getLiveData("user", "")
    val password: MutableLiveData<String> = savedStateHandle.getLiveData("password", "")
    val email: MutableLiveData<String> = savedStateHandle.getLiveData("email", "")
    val gender: String? = null
    val phone_no: MutableLiveData<String> = savedStateHandle.getLiveData("phone_no", "")
    val old: String? = null
    val newpwd: String? = null
    val confpwd: String? = null


    val LoginResponseData: LiveData<LoginResponse?>
        get() {

            if (_loginResponseData == null) {
                _loginResponseData = MutableLiveData<LoginResponse?>()
                loadLogin(user.toString(), password.toString())
            }
            return _loginResponseData
        }

    val RegisterResponseData: LiveData<LoginResponse?>
        get() {

            if (_regResponseData == null) {
                _regResponseData = MutableLiveData<LoginResponse?>()
                loadreg(firstname.toString(),
                    lastname.toString(),
                    user.toString(),
                    password.toString(),
                    confpwd.toString(),
                    gender.toString(),
                    phone_no.toString())
            }
            return _regResponseData
        }

    val ForgotPasswordData: LiveData<ForgotResponse?>
        get() {
            if (_forgotPassword == null) {
                _forgotPassword = MutableLiveData<ForgotResponse?>()
                loadForgot(email.toString())
            }
            return _forgotPassword
        }

    val Resetpwd: LiveData<Reset_Reponse_Base?>
        get() {
            if (_resetPassword == null) {
                _resetPassword = MutableLiveData<Reset_Reponse_Base?>()
                resetpwd(old.toString(), newpwd.toString(), confpwd.toString())
            }
            return _resetPassword
        }


    fun loadLogin(email: String, password: String) {
        LoginApiManager.instance.userLogin(email, password)
            .enqueue(object : Callback<LoginResponse> {
                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    Log.d("res", "" + t)
                }

                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    var res = response

                    if (res.body()?.status == 200) {
                        SharedPrefManager.getInstance(getApplication())
                            .saveUser(response.body()?.data!!)
                        _loginResponseData.postValue(response.body())
                        Toast.makeText(getApplication(), res.body()?.user_msg,
                            Toast.LENGTH_LONG
                        ).show()
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

    fun loadreg(
        firstname: String,
        lastname: String,
        user: String,
        password: String,
        confpwd: String,
        gender: String,
        phone_no: String
    ) {
        RegisterApiManager.instance.createUser(firstname,
            lastname,
            user,
            password,
            confpwd,
            gender,
            phone_no)
            .enqueue(object : Callback<LoginResponse> {
                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    Toast.makeText(getApplication(), t.message, Toast.LENGTH_LONG).show()
                }

                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {

                    var res = response
                    if (response.isSuccessful() && response.body() != null) {
                        SharedPrefManager.getInstance(getApplication())
                            .saveUser(response.body()?.data!!)


                        Toast.makeText(
                            getApplication(),
                            res.body()?.message,
                            Toast.LENGTH_LONG
                        ).show()

                        _regResponseData.postValue(response.body())

                    } else {
                        try {
                            val jObjError =
                                JSONObject(response.errorBody()!!.string())
                            Toast.makeText(
                                getApplication(),
                                jObjError.getString("message"),
                                Toast.LENGTH_LONG
                            ).show()
                        } catch (e: Exception) {
                            Toast.makeText(getApplication(), e.message, Toast.LENGTH_LONG).show()
                            Log.e("errorrr", e.message)
                        }
                    }
                }
            })
    }

    fun loadForgot(email: String) {
        ForgotPasswordManager.instance.emailForgotpwd(email)
            .enqueue(object : Callback<ForgotResponse> {
                override fun onFailure(call: Call<ForgotResponse>, t: Throwable) {

                    Log.d("res", "" + t)
                    _forgotPassword.value = null

                }

                override fun onResponse(
                    call: Call<ForgotResponse>,
                    response: Response<ForgotResponse>
                ) {
                    var res = response

                    Log.d("response check ", "" + response.body()?.status.toString())
                    if (res.body()?.status == 200) {
                        Toast.makeText(getApplication(), res.body()?.user_msg, Toast.LENGTH_LONG)
                            .show()
                        // forgotCallBack.onSuccess()
                        _forgotPassword.postValue(response.body())

                    } else {
                        try {
                            val jObjError =
                                JSONObject(response.errorBody()!!.string())
                            Toast.makeText(getApplication(),
                                jObjError.getString("user_msg"),
                                Toast.LENGTH_LONG).show()

                        } catch (e: Exception) {
                            Toast.makeText(getApplication(), e.message, Toast.LENGTH_LONG).show()


                        }
                    }
                }
            })

    }

    fun resetpwd(old: String, newpwd: String, confpwd: String) {
        val token: String =
            SharedPrefManager.getInstance(
                getApplication()
            ).user.access_token.toString()
        ResetPasswordApiManager.instance.resetpassword(token, old, newpwd, confpwd)
            .enqueue(object : Callback<Reset_Reponse_Base> {
                override fun onFailure(call: Call<Reset_Reponse_Base>, t: Throwable) {
                    Log.d("res", "" + t)


                }

                override fun onResponse(
                    call: Call<Reset_Reponse_Base>,
                    response: Response<Reset_Reponse_Base>
                ) {
                    var res = response

                    if (res.body()?.status == 200) {

                        Log.d("response check ", "" + response.body()?.status.toString())
                        Toast.makeText(getApplication(), res.body()?.user_msg, Toast.LENGTH_LONG)
                            .show()
                        _resetPassword.postValue(response.body())


                    } else {
                        val error: Reset_Reponse_Base = ErrorUtils.parseError(response)

                        if (newpwd.length <= 5 && confpwd.length <= 5) {
                            Toast.makeText(getApplication(),
                                "password should more than 6 letters or digits",
                                Toast.LENGTH_LONG).show()

                        } else {

                            Toast.makeText(getApplication(),
                                error.error.confirm_password.compareWith,
                                Toast.LENGTH_LONG).show()
                        }
                    }

                }


            })
    }


}