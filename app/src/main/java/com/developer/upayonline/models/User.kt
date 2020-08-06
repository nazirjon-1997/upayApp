package com.developer.upayonline.models

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class User {
    @SerializedName("fullName")
    @Expose
    private var fullName: String? = null

    @SerializedName("avatar")
    @Expose
    private var avatar: Any? = null

    @SerializedName("balance")
    @Expose
    private var balance: Double? = null

    fun getFullName(): String? {
        return fullName
    }

    fun setFullName(fullName: String?) {
        this.fullName = fullName
    }

    fun getAvatar(): Any? {
        return avatar
    }

    fun setAvatar(avatar: Any?) {
        this.avatar = avatar
    }

    fun getBalance(): Double? {
        return balance
    }

    fun setBalance(balance: Double?) {
        this.balance = balance
    }
}