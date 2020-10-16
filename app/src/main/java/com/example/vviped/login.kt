package com.example.vviped

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login.*

class login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

//        btn_login_account.setOnClickListener{
//            startActivity(Intent(this, MainActivity::class.java))
//        }

        textCreateAccount.setOnClickListener{
            startActivity(Intent(this, Register::class.java))
        }
    }
}