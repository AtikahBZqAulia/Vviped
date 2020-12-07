package com.mobcomp.vviped

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_notification_details.*

class NotificationDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification_details)

        val details = intent.getStringExtra("details")
        notification_details.text = details

        backspace.setOnClickListener {
            onBackPressed()
        }

    }
}