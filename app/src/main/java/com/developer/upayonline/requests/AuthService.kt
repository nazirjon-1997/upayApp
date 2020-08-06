package com.developer.restaurantapp.others

import android.text.TextUtils
import com.developer.upayonline.others.CURL
import okhttp3.Credentials.basic
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object AuthService {
    private val BASE_URL = CURL.SERVER
    private val httpClient = OkHttpClient.Builder()
    private val builder = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
    private var retrofit = builder.build()
    fun <S> createService(serviceClass: Class<S>?): S {
        return createService(serviceClass, null, null)
    }

    public fun <S> createService(
        serviceClass: Class<S>?, username: String?, password: String?
    ): S {
        if (!TextUtils.isEmpty(username)
            && !TextUtils.isEmpty(password)
        ) {
            val authToken: String = basic(username!!, password!!)
            return createService(serviceClass, authToken)
        }
        return createService(serviceClass, null)
    }

    fun <S> createService(
        serviceClass: Class<S>?, authToken: String?
    ): S {
        if (!TextUtils.isEmpty(authToken)) {
            val interceptor = AuthInterceptor(authToken!!)
            if (!httpClient.interceptors().contains(interceptor)) {
                httpClient.addInterceptor(interceptor)
                builder.client(httpClient.build())
                retrofit = builder.build()
            }
        }
        return retrofit.create(serviceClass)
    }
}