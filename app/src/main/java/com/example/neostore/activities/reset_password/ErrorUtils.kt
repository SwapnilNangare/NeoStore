package com.example.neostore.activities.reset_password

import com.example.neostore.activities.api_manager.ApiManager
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Response
import java.io.IOException


object ErrorUtils {
    fun parseError(response: Response<*>): ResetReponseBase {
        val converter: Converter<ResponseBody, ResetReponseBase> = ApiManager.retrofit9
            .responseBodyConverter(ResetReponseBase::class.java, arrayOfNulls<Annotation>(0))
        val error: ResetReponseBase
        error = try {
            converter.convert(response.errorBody())
        } catch (e: IOException) {
            return ResetReponseBase(status = 500,message = "",error = ResetResponseError(
                ResetResponseConfirmPassword("")
            ),user_msg = "")
        }
        return error
    }
}