package com.developer.upayonline.requests

import com.developer.upayonline.models.Autho
import com.developer.upayonline.models.Transaction
import com.developer.upayonline.models.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST


interface JSONPlaceHolderApi {

    @POST("/api/auth/login")
    fun auth(): Call<Autho>

    @GET("/api/wallet/customer-info")
    fun getCustomerInfo(@Header("Authorization") auth: String?): Call<User>


    @GET("/api/wallet/get-transactions")
    fun getTransactions(@Header("Authorization") auth: String?): Call<List<Transaction>>

}