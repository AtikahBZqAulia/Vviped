package com.example.vviped

import android.app.Activity
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.vviped.model.RetrofitInterface
import com.example.vviped.model.UploadRequestBody
import com.example.vviped.model.UploadResponse
import com.example.vviped.model.snackbar
import kotlinx.android.synthetic.main.activity_create_campaign.*
import kotlinx.android.synthetic.main.activity_create_campaign.progress_bar
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_upload_selling.*

import kotlinx.android.synthetic.main.activity_user_register.*
import kotlinx.android.synthetic.main.activity_user_register.btn_register_account
import kotlinx.android.synthetic.main.activity_user_register.user_email
import kotlinx.android.synthetic.main.activity_user_register.imageBackspace
import kotlinx.android.synthetic.main.activity_user_register.textLoginHere
import kotlinx.android.synthetic.main.activity_user_register.user_password
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRegister : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_register)

        btn_register_account.setOnClickListener {

            val email = user_email.text.toString().trim()
            val fullname = user_fullname.text.toString().trim()
            val username = user_name.text.toString().trim()
            val password = user_password.text.toString().trim()

            if(email.isEmpty() ){
                user_email.error = "field cannot be empty"
                user_email.requestFocus()
                return@setOnClickListener
            }
            if(fullname.isEmpty()){
                user_fullname.error = "Name field cannot be empty"
                user_fullname.requestFocus()
                return@setOnClickListener
            }
            if(username.isEmpty()){
                user_name.error = "username field cannot be empty"
                user_name.requestFocus()
                return@setOnClickListener
            }
            if(password.isEmpty()){
                user_password.error = "field cannot be empty"
                user_password.requestFocus()
                return@setOnClickListener
            }

            if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                user_email.error = "Email not valid"
                user_email.requestFocus()
                return@setOnClickListener
            }

            if(password.length < 6){
                user_password.error = "Password too short"
                user_password.requestFocus()
                return@setOnClickListener
            }
            register(email, fullname, username, password)

        }

        imageBackspace.setOnClickListener{
            startActivity(Intent(this, Landing::class.java).also {
                it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            })
        }

        textLoginHere.setOnClickListener{
            startActivity(Intent(this, UserLogin::class.java).also {
                it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            })
        }

    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    private fun register(email: String, fullname:String, username:String, password:String ) {
        val email = findViewById<EditText>(R.id.user_email)
        val fullname = findViewById<EditText>(R.id.user_fullname)
        val username = findViewById<EditText>(R.id.user_name)
        val password = findViewById<EditText>(R.id.user_password)

        RetrofitInterface().registerUser(
            RequestBody.create(MediaType.parse("multipart/form-data"), email.text.toString()),
            RequestBody.create(MediaType.parse("multipart/form-data"), fullname.text.toString()),
            RequestBody.create(MediaType.parse("multipart/form-data"), username.text.toString()),
            RequestBody.create(MediaType.parse("multipart/form-data"), password.text.toString()),
            ).enqueue(object : Callback<UploadResponse> {

            override fun onFailure(call: Call<UploadResponse>, t: Throwable) {
                Toast.makeText(this@UserRegister, "Register Failed! Try Again.", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<UploadResponse>,response: Response<UploadResponse>
            ) {
                response.body()?.let {

                    Toast.makeText(this@UserRegister, "Success! Please Login.", Toast.LENGTH_LONG).show()

                    }
            }
        })

    }

}