package com.example.vviped

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.vviped.model.RetrofitInterface
import com.example.vviped.model.login.Constant
import com.example.vviped.model.login.LoginResponse
import com.example.vviped.model.login.PreferenceHelper
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_user_login.*
import kotlinx.android.synthetic.main.activity_user_register.*
import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlinx.android.synthetic.main.activity_user_login.user_name as user_name1
import kotlinx.android.synthetic.main.activity_user_register.user_password as user_password1


@RequiresApi(Build.VERSION_CODES.KITKAT)
class UserLogin : AppCompatActivity() {
    lateinit var sharedPref: PreferenceHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_login)

        sharedPref = PreferenceHelper(this)


        val buttonLogin = findViewById<Button>(R.id.btn_login_account)
        textCreateAccount.setOnClickListener{
            startActivity(Intent(this, UserRegister::class.java))
        }

        buttonLogin.setOnClickListener{
            val login_username = user_name.text.toString().trim()
            val login_password = user_password.text.toString().trim()

            if( login_username.isEmpty()){
                user_name.error = "username cannot be empty"
                user_name.requestFocus()
                return@setOnClickListener
            }
            if( login_username.length < 6){
                user_name.error = "Username too short"
                user_name.requestFocus()
                return@setOnClickListener
            }
            if(login_password.isEmpty()){
                user_password.error = "field cannot be empty"
                user_password.requestFocus()
                return@setOnClickListener
            }
            if(login_password.length < 6){
                user_password.error = "Password too short"
                user_password.requestFocus()
                return@setOnClickListener
            }
            userLogin(login_username, login_password)
        }

    }

    private fun userLogin(loginUsername: String, loginPassword: String) {
        val username = findViewById<EditText>(R.id.user_name)
        val password = findViewById<EditText>(R.id.user_password)

        val login_username = user_name.text.toString().trim()
        val login_password = user_password.text.toString().trim()

        RetrofitInterface().loginUser(
            RequestBody.create(
                MediaType.parse("multipart/form-data"),
                username.text.toString()
            ),
            RequestBody.create(
                MediaType.parse("multipart/form-data"),
                password.text.toString()
            ),
        ).enqueue(object : Callback<LoginResponse> {

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {

                val snackbar = t.message?.let {
                    Snackbar
//                            .make(layout_userLogin, t.message!!, Snackbar.LENGTH_LONG)
                        .make(layout_userLogin, "Username/password doesn't exist!", Snackbar.LENGTH_LONG)
                }
                snackbar?.show()
            }

            override fun onResponse(
                call: Call<LoginResponse>, response: Response<LoginResponse>
            ) {
                response.body()?.let {
                    if(response.body() != null && response.body()!!.isStatus()!!){
                        // jika password benar
                        Toast.makeText(
                            this@UserLogin,
                            "Welcome, " + response.body()!!.data!!.fullname + "!",
                            Toast.LENGTH_LONG
                        )
                            .show()
                        saveSession(login_username, login_password)
                        nextToMainActivity()
                    } else {
                        // jika salah password
                        Toast.makeText(this@UserLogin, response.body()!!.message, Toast.LENGTH_LONG)
                            .show()

                    }
                }
            }
        })
    }

    private fun nextToMainActivity() {
        startActivity(Intent(this, MainActivity::class.java).also {
            it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        })

    }

    override fun onStart() {
        super.onStart()
        if(sharedPref.getBoolean(Constant.IS_LOGGED_IN)!!){
            startActivity(Intent(this, MainActivity::class.java))
                finish()

            }
    }

    private fun saveSession(username : String, password: String) {
        sharedPref.put(Constant.PREF_USERNAME, username)
        sharedPref.put(Constant.PREF_PASSWORD, password)
        sharedPref.put(Constant.IS_LOGGED_IN, true)

    }


}
