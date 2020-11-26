package com.example.vviped

import android.content.ComponentName
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.telephony.PhoneNumberUtils
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_chat_for_buying.*
import kotlinx.android.synthetic.main.activity_chat_for_buying.product_name
import kotlinx.android.synthetic.main.sellingposts_layout.*


class ChatForBuying : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_for_buying)

        val productName = intent.getStringExtra("product_name")
        product_name.text = productName

        val seller_name = intent.getStringExtra("seller_username")
        seller_username.text = seller_name

        val productPrice = intent.getStringExtra("product_price")
        product_price.text = productPrice

        val whatsappNumber = intent.getStringExtra("whatsapp")
        whatsapp_number_chat.text = whatsappNumber


        btnSendMessage.setOnClickListener {
            openWhatsApp()
        }
        backspace.setOnClickListener {
            onBackPressed()
        }
    }

    private fun openWhatsApp() {
        val isWhatsappInstalled = whatsappInstalledOrNot("com.whatsapp")
        if (isWhatsappInstalled) {

            val whatsapp = findViewById<TextView>(R.id.whatsapp_number_chat)
            val strWhatsapp = whatsapp.text.toString()

            val sellerName = findViewById<TextView>(R.id.seller_username)
            val strSellerName = sellerName.text.toString()

            val nameProduct = findViewById<TextView>(R.id.product_name)
            val strProductName = nameProduct.text.toString()

            val priceProduct = findViewById<TextView>(R.id.product_price)
            val strPriceProduct = priceProduct.text.toString()

            val campaignProduct = findViewById<TextView>(R.id.product_campaign)
            val strCampaignProduct = campaignProduct.text.toString()


            val message = "_*[VVIPED APP NOTIFICATION]*_ \n\n" +
                    "Hai, ```"+  strSellerName + "```! \n" +
                    "Saya tertarik dengan produk anda:  \n \n" +
                    "*" +strProductName + "*" + "\n\n" +
                    "*dengan harga:* \n Rp" + strPriceProduct + "\n" +
                    "*untuk campaign:* \n" + strCampaignProduct + "\n"

            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("http://api.whatsapp.com/send?phone=" + strWhatsapp + "&text=" + message)
            startActivity(intent)

        } else {
            val uri = Uri.parse("market://details?id=com.whatsapp")
            val goToMarket = Intent(Intent.ACTION_VIEW, uri)
            Toast.makeText(
                this, "Please install Whatsapp to send the message to seller.",
                Toast.LENGTH_SHORT
            ).show()
            startActivity(goToMarket)
        }
    }

    private fun whatsappInstalledOrNot(uri: String): Boolean {
        val pm = packageManager
        var app_installed = false
        app_installed = try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES)
            true
        } catch (e: PackageManager.NameNotFoundException) {
            false
        }
        return app_installed
    }

}

