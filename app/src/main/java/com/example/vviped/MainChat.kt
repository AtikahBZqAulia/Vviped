package com.example.vviped

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.media.MediaScannerConnection
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_chat.*
import com.example.vviped.model.Chat
import com.example.vviped.AdapterChat
import kotlinx.android.synthetic.main.activity_register.*
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class MainChat : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        BackFromChat.setOnClickListener{
            onBackPressed()
        }

        val listViewType = mutableListOf<Int>()
        listViewType.add(1)
        listViewType.add(2)
        listViewType.add(2)
        listViewType.add(1)
        listViewType.add(2)
        listViewType.add(2)
        listViewType.add(1)
        listViewType.add(2)
        val listChat = mutableListOf<Chat>()
        listChat.add(Chat(message = "Halo kak, bisa kurang lagi gak?", dateTime = "16:36 PM "))
        listChat.add(Chat(message = "Yaah, gabisa sis maaf.. ", dateTime = "16:40 PM "))
        listChat.add(Chat(message = "Harga sudah nett", dateTime = "16:40 PM "))
        listChat.add(Chat(message = "ooh, yaudah deh kak kalau gitu saya ambil aja", dateTime = "16:41 PM "))
        listChat.add(Chat(message = "Tolong diisi ya sis alamat untuk pengiriman", dateTime = "16:42 PM "))
        listChat.add(Chat(message = "Nama, Alamat, No.Telp: ", dateTime = "16:42 PM "))
        listChat.add(Chat(message = "Nama: Maura, Alamat: Bintara 6, No.Telp:087883520021 ", dateTime = "16:42 PM "))
        listChat.add(Chat(message = "Oke sis terima kasih, akan segera saya kirim ya, ditunggu nomor resi nya ", dateTime = "16:42 PM "))


        val adapterChat = AdapterChat(listViewType = listViewType, listChat = listChat)
        chatRecyclerView.layoutManager = LinearLayoutManager(this)
        chatRecyclerView.adapter = adapterChat
    }
}