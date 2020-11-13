package com.example.vviped

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.widget.EditText
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.vviped.model.*
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_upload_selling.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import android.widget.RadioButton
import android.widget.RadioGroup

class UploadSellingActivity : AppCompatActivity(), UploadRequestBody.UploadCallback {

    private var selectedImageUri: Uri? = null

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload_selling)

        image_view.setOnClickListener {
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
            startActivityForResult(it, REQUEST_CODE_PICK_IMAGE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                REQUEST_CODE_PICK_IMAGE -> {
                    selectedImageUri = data?.data
                    image_view.setImageURI(selectedImageUri)
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    private fun uploadImage() {
        if (selectedImageUri == null) {
            layout_root.snackbar("Select an Image First")
            return
        }

        val emailcurrentuser = FirebaseAuth.getInstance().currentUser!!.email

        val productprice = findViewById<EditText>(R.id.text_priceproduct)
        val productname = findViewById<EditText>(R.id.text_productname)
        val productdesc = findViewById<EditText>(R.id.text_productdesc)
        val sellerlocation = findViewById<EditText>(R.id.text_productloc)
        val radioGroup = findViewById<RadioGroup>(R.id.radiogroup)
        val intSelectButton: Int = radioGroup!!.checkedRadioButtonId
        val radioButton = findViewById<RadioButton>(intSelectButton)

        val parcelFileDescriptor = contentResolver.openFileDescriptor(selectedImageUri!!, "r", null) ?: return

        val inputStream = FileInputStream(parcelFileDescriptor.fileDescriptor)
        val file = File(cacheDir, contentResolver.getFileName(selectedImageUri!!))
        val outputStream = FileOutputStream(file)
        inputStream.copyTo(outputStream)

        progress_bar.progress = 0
        val body = UploadRequestBody(file, "image", this)
        RetrofitInterface().uploadImage(
            MultipartBody.Part.createFormData(
                "image",
                file.name,
                body
            ),
            RequestBody.create(MediaType.parse("multipart/form-data"), productprice.text.toString()),
            RequestBody.create(MediaType.parse("multipart/form-data"), productname.text.toString()),
            RequestBody.create(MediaType.parse("multipart/form-data"), radioButton.text.toString()),
            RequestBody.create(MediaType.parse("multipart/form-data"), productdesc.text.toString()),
            RequestBody.create(MediaType.parse("multipart/form-data"), sellerlocation.text.toString()),
            RequestBody.create(MediaType.parse("multipart/form-data"), "SALE")
        ).enqueue(object : Callback<UploadResponse> {
            override fun onFailure(call: Call<UploadResponse>, t: Throwable) {
                layout_root.snackbar(t.message!!)
                progress_bar.progress = 0
            }

            override fun onResponse(
                call: Call<UploadResponse>,
                response: Response<UploadResponse>
            ) {
                response.body()?.let {
                    layout_root.snackbar(it.message)
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
