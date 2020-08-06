package com.developer.upayonline.models

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class Transaction {

    @SerializedName("serviceid")
    @Expose
    private var serviceid: Int? = null

    @SerializedName("serviceName")
    @Expose
    private var serviceName: String? = null

    @SerializedName("account")
    @Expose
    private var account: String? = null

    @SerializedName("amount")
    @Expose
    private var amount: Double? = null

    @SerializedName("status")
    @Expose
    private var status: Int? = null

    @SerializedName("dateTime")
    @Expose
    private var dateTime: String? = null

    constructor(
        serviceid: Int?,
        serviceName: String?,
        account: String?,
        amount: Double?,
        status: Int?,
        dateTime: String?
    ) {
        this.serviceid = serviceid
        this.serviceName = serviceName
        this.account = account
        this.amount = amount
        this.status = status
        this.dateTime = dateTime
    }

    fun getServiceid(): Int? {
        return serviceid
    }

    fun setServiceid(serviceid: Int?) {
        this.serviceid = serviceid
    }

    fun getServiceName(): String? {
        return serviceName
    }

    fun setServiceName(serviceName: String?) {
        this.serviceName = serviceName
    }

    fun getAccount(): String? {
        return account
    }

    fun setAccount(account: String?) {
        this.account = account
    }

    fun getAmount(): Double? {
        return amount
    }

    fun setAmount(amount: Double?) {
        this.amount = amount
    }

    fun getStatus(): Int? {
        return status
    }

    fun setStatus(status: Int?) {
        this.status = status
    }

    fun getDateTime(): String? {
        return dateTime
    }

    fun setDateTime(dateTime: String?) {
        this.dateTime = dateTime
    }
}