package com.developer.upayonline.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Autho {
    @SerializedName("token")
    @Expose
    private var token: String? = null

    @SerializedName("subagentId")
    @Expose
    private var subagentId: Int? = null

    @SerializedName("loggedinasparent")
    @Expose
    private var loggedinasparent: Int? = null

    @SerializedName("role")
    @Expose
    private var role: String? = null

    fun getToken(): String? {
        return token
    }

    fun setToken(token: String?) {
        this.token = token
    }

    fun getSubagentId(): Int? {
        return subagentId
    }

    fun setSubagentId(subagentId: Int?) {
        this.subagentId = subagentId
    }

    fun getLoggedinasparent(): Int? {
        return loggedinasparent
    }

    fun setLoggedinasparent(loggedinasparent: Int?) {
        this.loggedinasparent = loggedinasparent
    }

    fun getRole(): String? {
        return role
    }

    fun setRole(role: String?) {
        this.role = role
    }

}