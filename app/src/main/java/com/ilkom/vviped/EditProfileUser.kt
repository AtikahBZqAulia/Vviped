package com.ilkom.vviped

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.widget.EditText
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.ilkom.vviped.model.*
import com.ilkom.vviped.model.login.Constant
import com.ilkom.vviped.model.login.PreferenceHelper
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_edit_profile_user.*
import kotlinx.android.synthetic.main.activity_edit_profile_user.fullname_edit
import kotlinx.android.synthetic.main.activity_edit_profile_user.username_edit
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

class EditProfileUser : AppCompatActivity(), UploadRequestBody.UploadCallback {

    private var selectedImageUri: Uri? = null

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile_user)

        val sharedPref = PreferenceHelper(this)
        Picasso.get().load(sharedPref.getString(Constant.PREF_PROFPIC)).into(profpicture_edit)
        fullname_edit.setText(sharedPref.getString(Constant.PREF_FULLNAME))
        username_edit.setText(sharedPref.getString(Constant.PREF_USERNAME))

        backspace_button.setOnClickListener {
            onBackPressed()
        }

        editprofpic_button.setOnClickListener {
            openImageChooser()
        }

        button_save.setOnClickListener {
            val username = username_edit.text.toString()
            if(username.isEmpty()){
                username_edit.error = "username field cannot be empty"
                username_edit.requestFocus()
                return@setOnClickListener
            }
            uploadImage()
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
                    profpicture_edit.setImageURI(selectedImageUri)
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    private fun uploadImage() {

        val sharedPref = PreferenceHelper(this)

        if (selectedImageUri == null) {
            val fullname_edit = findViewById<EditText>(R.id.fullname_edit)
            val username_edit = findViewById<EditText>(R.id.username_edit)
            val empty_val = RequestBody.create(MediaType.parse("text/plain"), "")
            RetrofitInterface().editUserProfile(
                MultipartBody.Part.createFormData("image", "", empty_val),
                sharedPref.getInt(Constant.PREF_ID)!!,
                RequestBody.create(MediaType.parse("multipart/form-data"), fullname_edit.text.toString()),
                RequestBody.create(MediaType.parse("multipart/form-data"), username_edit.text.toString()),
            ).enqueue(object : Callback<UpdateUserResponse> {
                override fun onFailure(call: Call<UpdateUserResponse>, t: Throwable) {
                    layout_editprofileuser.snackbar(t.message!!)
                    progress_bar.progress = 0
                }

                override fun onResponse(
                    call: Call<UpdateUserResponse>,
                    response: Response<UpdateUserResponse>
                ) {
                    response.body()?.let {
                        layout_editprofileuser.snackbar(it.message)
                        progress_bar.progress = 100
                        sharedPref.put(Constant.PREF_FULLNAME, it.fullname)
                        sharedPref.put(Constant.PREF_USERNAME, it.username)
                        sharedPref.put(Constant.PREF_PROFPIC, it.profpic)
                    }
                }
            })
        } else {
            val fullname_edit = findViewById<EditText>(R.id.fullname_edit)
            val username_edit = findViewById<EditText>(R.id.username_edit)

            val parcelFileDescriptor = contentResolver.openFileDescriptor(selectedImageUri!!, "r", null) ?: return

            val inputStream = FileInputStream(parcelFileDescriptor.fileDescriptor)
            val file = File(cacheDir, contentResolver.getFileName(selectedImageUri!!))
            val outputStream = FileOutputStream(file)
            inputStream.copyTo(outputStream)

            progress_bar.progress = 0
            val body = UploadRequestBody(file, "image", this)
            RetrofitInterface().editUserProfile(
                MultipartBody.Part.createFormData(
                    "image",
                    file.name,
                    body
                ),
                sharedPref.getInt(Constant.PREF_ID)!!,
                RequestBody.create(MediaType.parse("multipart/form-data"), fullname_edit.text.toString()),
                RequestBody.create(MediaType.parse("multipart/form-data"), username_edit.text.toString()),
            ).enqueue(object : Callback<UpdateUserResponse> {
                override fun onFailure(call: Call<UpdateUserResponse>, t: Throwable) {
                    layout_editprofileuser.snackbar(t.message!!)
                    progress_bar.progress = 0
                }

                override fun onResponse(
                    call: Call<UpdateUserResponse>,
                    response: Response<UpdateUserResponse>
                ) {
                    response.body()?.let {
                        layout_editprofileuser.snackbar(it.message)
                        progress_bar.progress = 100
                        sharedPref.put(Constant.PREF_FULLNAME, it.fullname)
                        sharedPref.put(Constant.PREF_USERNAME, it.username)
                        sharedPref.put(Constant.PREF_PROFPIC, it.profpic)
                    }
                }
            })
        }



    }

    override fun onProgressUpdate(percentage: Int) {
        progress_bar.progress = percentage
    }

    companion object {
        const val REQUEST_CODE_PICK_IMAGE = 101
    }
}