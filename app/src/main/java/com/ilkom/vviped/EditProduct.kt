package com.ilkom.vviped

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_edit_product.*

class EditProduct : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_product)

        campaign_title.text = intent.getStringExtra("nama_campaign")
        namaproduk_edit.text = intent.getStringExtra("nama_produk")
        edit_priceproduct.setText(intent.getStringExtra("harga_produk"))
        edit_productdesc.setText(intent.getStringExtra("deskripsi_produk"))
        edit_productloc.setText(intent.getStringExtra("lokasi_produk"))
        edit_whatsapp.setText(intent.getStringExtra("whatsapp_penjual"))
        //edit_whatsapp.setText(intent.getStringExtra("id_product"))

        backspace.setOnClickListener {
            onBackPressed()
        }
    }
}
