package com.example.vviped

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import kotlinx.android.synthetic.main.activity_register.*

import kotlinx.android.synthetic.main.activity_user_register.*
import kotlinx.android.synthetic.main.activity_user_register.btn_register_account
import kotlinx.android.synthetic.main.activity_user_register.user_email
import kotlinx.android.synthetic.main.activity_user_register.imageBackspace
import kotlinx.android.synthetic.main.activity_user_register.textLoginHere
import kotlinx.android.synthetic.main.activity_user_register.user_password

class UserRegister : AppCompatActivity() {
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
//            registerUser(username,password)
        }

        imageBackspace.setOnClickListener{
//             onBackPressed()
            startActivity(Intent(this, Landing::class.java).also {
                it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            })
        }

        textLoginHere.setOnClickListener{
//             onBackPressed()
            startActivity(Intent(this, UserLogin::class.java).also {
                it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            })
        }

    }

//    private fun registerUser(username: String, password: String) {
//
//    }
}