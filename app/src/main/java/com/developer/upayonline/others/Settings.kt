package com.developer.upay.others

import android.content.Context
import android.content.SharedPreferences

class Settings(var context: Context) {
    var editor: SharedPreferences.Editor
    var PRIVATE_MODE = 0

    //получение login
    val getLogin: String?
        get() =//получение login
            sharedPreferences.getString(
                LOGIN,
                ""
            )

    fun saveLogin(text: String?) { //сохранение login
        editor.putString(LOGIN, text)
        editor.commit()
    }

    //получение password
    val getPassword: String?
        get() =//получение password
            sharedPreferences.getString(
                PASSWORD,
                ""
            )

    fun savePassword(text: String?) { //сохранение password
        editor.putString(PASSWORD, text)
        editor.commit()
    }

    //получение token
    val token: String?
        get() =//получение token
            sharedPreferences.getString(
                TOKEN,
                ""
            )

    fun saveToken(text: String?) { //сохранение token
        editor.putString(TOKEN, text)
        editor.commit()
    }

    fun deleteToken() { //delete token
        editor.clear()
        editor.commit()
    }

    //получение phone
    val phoneNumber: String?
        get() =//получение phone
            sharedPreferences.getString(
                PHONE,
                ""
            )

    fun savePhone(text: String?) { //сохранение phone
        editor.putString(PHONE, text)
        editor.commit()
    }

    //получение AVATAR
    val avatar: String?
        get() =//получение AVATAR
            sharedPreferences.getString(
                AVATAR,
                ""
            )

    fun saveAvatar(text: String?) { //сохранение AVATAR
        editor.putString(AVATAR, text)
        editor.commit()
    }

    companion object {
        // LogCat tag
        private val TAG =
            Settings::class.java.simpleName

        // Shared Preferences
        lateinit var sharedPreferences: SharedPreferences

        // Shared preferences file name
        private const val PREF_NAME = "LOGIN"
        const val LOGIN = "login"
        const val PASSWORD = "password"
        const val TOKEN = "token"
        const val PHONE = "phone"
        const val AVATAR = "avatar"
        fun deleteAllSharedPrefs(): Boolean {
            return sharedPreferences.edit().clear()
                .commit()
        }
    }

    init {
        sharedPreferences =
            context.getSharedPreferences(
                PREF_NAME,
                PRIVATE_MODE
            )
        editor = sharedPreferences.edit()
    }
}