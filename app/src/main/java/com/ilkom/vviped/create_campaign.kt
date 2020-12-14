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
import kotlinx.android.synthetic.main.activity_create_campaign.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

class create_campaign : AppCompatActivity(), UploadRequestBody.UploadCallback {

    private var selectedImageUri: Uri? = null

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_campaign)

        backspace.setOnClickListener {
            onBackPressed()
        }

        image_campaign.setOnClickListener {
            openImageChooser()
        }

        button_upload.setOnClickListener {
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
                    image_campaign.setImageURI(selectedImageUri)
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    private fun uploadImage() {
        if (selectedImageUri == null) {
            layout_campaign.snackbar("Select an Image First")
            return
        }

        val campaigncategory = findViewById<EditText>(R.id.campaign_category)
        val campaigntitle = findViewById<EditText>(R.id.campaign_name)
        val campaigndesc = findViewById<EditText>(R.id.campaign_deskripsi)
        val donationgoes = findViewById<EditText>(R.id.campaign_penerima)
        val usagedetails = findViewById<EditText>(R.id.usage_detail)
        val phonenumber = findViewById<EditText>(R.id.campaign_phone)
        val sharedPref = PreferenceHelper(this)

        val parcelFileDescriptor = contentResolver.openFileDescriptor(selectedImageUri!!, "r", null) ?: return

        val inputStream = FileInputStream(parcelFileDescriptor.fileDescriptor)
        val file = File(cacheDir, contentResolver.getFileName(selectedImageUri!!))
        val outputStream = FileOutputStream(file)
        inputStream.copyTo(outputStream)


        progress_bar.progress = 0
        val body = UploadRequestBody(file, "image", this)
        RetrofitInterface().uploadCampaign(
            MultipartBody.Part.createFormData(
                "image",
                file.name,
                body
            ),
            RequestBody.create(MediaType.parse("multipart/form-data"), campaigncategory.text.toString()),
            RequestBody.create(MediaType.parse("multipart/form-data"), campaigntitle.text.toString()),
            RequestBody.create(MediaType.parse("multipart/form-data"), campaigndesc.text.toString()),
            RequestBody.create(MediaType.parse("multipart/form-data"), donationgoes.text.toString()),
            RequestBody.create(MediaType.parse("multipart/form-data"), usagedetails.text.toString()),
            RequestBody.create(MediaType.parse("multipart/form-data"), phonenumber.text.toString()),
            sharedPref.getInt(Constant.PREF_ID)!!
        ).enqueue(object : Callback<UploadResponse> {
            override fun onFailure(call: Call<UploadResponse>, t: Throwable) {
                layout_campaign.snackbar(t.message!!)
                progress_bar.progress = 0
            }

            override fun onResponse(
                call: Call<UploadResponse>,
                response: Response<UploadResponse>
            ) {
                response.body()?.let {
                    layout_campaign.snackbar(it.message)
                    progress_bar.progress = 100
                }
            }
        })

    }

    override fun onProgressUpdate(percentage: Int) {
        progress_bar.progress = percentage
    }

    companion object {
        const val REQUEST_CODE_PICK_IMAGE = 101
    }
}