package com.example.vviped.ui

<<<<<<< HEAD
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.vviped.LoginActivity
import com.example.vviped.R
import com.example.vviped.comment_action
import kotlinx.android.synthetic.main.activity_comments.*
import kotlinx.android.synthetic.main.activity_landing.*
=======
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.vviped.R
>>>>>>> 2482bde56db7d78386b5c5362cdfd812072d25c2

class CommentsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comments)
<<<<<<< HEAD

        comment_act.setOnClickListener{
            startActivity(Intent(this, comment_action::class.java))
        }


=======
>>>>>>> 2482bde56db7d78386b5c5362cdfd812072d25c2
    }
}