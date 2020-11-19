package com.example.vviped

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.vviped.model.RetrofitInterface
import com.example.vviped.model.UploadResponse
import com.example.vviped.model.snackbar
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_user_login.*
import kotlinx.android.synthetic.main.activity_user_register.*
import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



@RequiresApi(Build.VERSION_CODES.KITKAT)
class UserLogin : AppCompatActivity() {
    var CheckEditText: Boolean? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_login)



        val buttonLogin = findViewById<Button>(R.id.btn_login_account)
        textCreateAccount.setOnClickListener{
            startActivity(Intent(this, UserRegister::class.java))
        }

        buttonLogin.setOnClickListener{
            val login_username = username_login.text.toString().trim()
            val login_password = user_password_login.text.toString().trim()

            if( login_username.isEmpty()){
                username_login.error = "username cannot be empty"
                username_login.requestFocus()
                return@setOnClickListener
            }
            if( login_username.length < 6){
                user_name.error = "Username too short"
                user_name.requestFocus()
                return@setOnClickListener
            }
            if(login_password.isEmpty()){
                user_password_login.error = "field cannot be empty"
                user_password_login.requestFocus()
                return@setOnClickListener
            }
            val username = findViewById<EditText>(R.id.username_login)
            val password = findViewById<EditText>(R.id.user_password_login)

            RetrofitInterface().loginUser(
                RequestBody.create(
                    MediaType.parse("multipart/form-data"),
                    username.text.toString()
                ),
                RequestBody.create(
                    MediaType.parse("multipart/form-data"),
                    password.text.toString()
                ),
            ).enqueue(object : Callback<UploadResponse> {

                override fun onFailure(call: Call<UploadResponse>, t: Throwable) {


                    val snackbar = t.message?.let {
                        Snackbar
                            .make(layout_userLogin, "Username doesn't exist!", Snackbar.LENGTH_LONG)
                    }
                    snackbar?.show()
                }

                override fun onResponse(
                    call: Call<UploadResponse>, response: Response<UploadResponse>
                ) {
                    response.body()?.let {
                        if(response.body() != null && response.isSuccessful()){
                            Toast.makeText(this@UserLogin,"Login success!", Toast.LENGTH_LONG)
                                .show()
                        }
                    }
                    nextToMainActivity()
                }
            })
        }

        }
    private fun nextToMainActivity() {
        startActivity(Intent(this, MainActivity::class.java).also {
            it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        })

    }
    }
