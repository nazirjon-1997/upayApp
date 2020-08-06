package com.developer.upayonline

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.developer.restaurantapp.others.AuthService
import com.developer.upay.others.Settings
import com.developer.upayonline.models.Autho
import com.developer.upayonline.requests.JSONPlaceHolderApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException


class LoginActivity : AppCompatActivity() {


    lateinit var settings: Settings
    lateinit var login: EditText
    lateinit var password: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        login = findViewById(R.id.phone_number)
        password = findViewById(R.id.password)
        val button = findViewById<Button>(R.id.btn_login)
        settings = Settings(this)
        if (!settings.getLogin!!.isEmpty() && !settings.getPassword!!.isEmpty()){
            login.setText(settings!!.getLogin.toString())
            password.setText(settings!!.getPassword.toString())
        }

        button.setOnClickListener(){
            requestLogin()
        }
    }

    private fun requestLogin() {
        val loginService: JSONPlaceHolderApi = AuthService.createService(JSONPlaceHolderApi::class.java, login.text.toString().toString(), password.text.toString().toString())
        val call: Call<Autho> = loginService.auth()
        call.enqueue(object : Callback<Autho?> {
            override fun onFailure(call: Call<Autho?>?, t: Throwable) {
                Log.d("Error", t.message!!)
            }
            override fun onResponse(call: Call<Autho?>?, response: Response<Autho?>?) {
                if (response!!.isSuccessful()) {
                    val user: Autho? = response.body()
                    val token: String? = user?.getToken()
                    Log.e("stag", token.toString())

                    if (!token!!.isEmpty()) {
                        settings.saveToken(token)
                        if (settings.getLogin!!.isEmpty() && settings.getPassword!!.isEmpty()){
                            settings.saveLogin(login.text.toString().trim())
                            settings.saveLogin(password.text.toString().trim())
                            Log.d("stag", login.text.toString())
                        }
                        startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                    }
                } else {
                    try {
                        Log.e("stag", response.errorBody().string())
                    } catch (e: IOException) {
                        Log.e("stag", e.message!!)
                    }
                }
            }
        })
    }

}