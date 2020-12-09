package com.mobcomp.vviped

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mobcomp.vviped.model.login.Constant
import com.mobcomp.vviped.model.login.PreferenceHelper
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_edit_profile.*

class EditProfile : AppCompatActivity() {
    private var selectedImageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        val sharedPref = PreferenceHelper(this)

        Picasso.get().load(sharedPref.getString(Constant.PREF_PROFPIC)).into(profpic_edit)
        fullname_edit.setText(sharedPref.getString(Constant.PREF_FULLNAME))
        username_edit.setText(sharedPref.getString(Constant.PREF_USERNAME))

        backspace_btn.setOnClickListener {
            onBackPressed()
        }

        editprofpic_btn.setOnClickListener {
            openImageChooser()
        }

        btn_save.setOnClickListener {
            val username = username_edit.text.toString()
            if(username.isEmpty()){
                username_edit.error = "username field cannot be empty"
                username_edit.requestFocus()
                return@setOnClickListener
            }
        }
    }

    private fun openImageChooser() {
        Intent(Intent.ACTION_PICK).also {
            it.type = "image/*"
            val mimeTypes = arrayOf("image/jpeg", "image/png")
            it.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes)
            startActivityForResult(it, UploadSellingActivity.REQUEST_CODE_PICK_IMAGE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                UploadSellingActivity.REQUEST_CODE_PICK_IMAGE -> {
                    selectedImageUri = data?.data
                    profpic_edit.setImageURI(selectedImageUri)
                }
            }
        }
    }

    override fun onBackPressed() {
        if ( supportFragmentManager.getBackStackEntryCount() > 0)
        {
            supportFragmentManager.popBackStack();
        }
        super.onBackPressed();
    }
}