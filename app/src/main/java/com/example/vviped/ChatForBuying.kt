package com.example.vviped

import android.content.ComponentName
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.telephony.PhoneNumberUtils
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_chat_for_buying.*


class ChatForBuying : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_for_buying)

        val sellerName = findViewById<TextView>(R.id.seller_username)
        val nameProduct = findViewById<TextView>(R.id.product_name)
        val priceProduct = findViewById<TextView>(R.id.product_price)
        val campaignProduct = findViewById<TextView>(R.id.product_campaign)

        val strSellerName = sellerName.text.toString()
        val strProductName = nameProduct.text.toString()
        val strPriceProduct = priceProduct.text.toString()
        val strCampaignProduct = campaignProduct.text.toString()


        btnSendMessage.setOnClickListener {
            val message = "_[VVIPED APP NOTIFICATION]_ \n\n" +
                    "Hai, ```"+  strSellerName + "```. \n" +
                    "Saya tertarik dengan produk anda:  \n \n" +
                    "*" +strProductName + "*" + "\n\n" +
                    "Harga: " + strPriceProduct + "\n" +
                    "Untuk campaign: " + strCampaignProduct + "\n"
            openWhatsApp(message)
        }
        backspace.setOnClickListener {
            onBackPressed()
        }
    }

    private fun openWhatsApp(message: Any) {
        val isWhatsappInstalled = whatsappInstalledOrNot("com.whatsapp")
        if (isWhatsappInstalled) {
            val toNumber = "6287883520021"
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("http://api.whatsapp.com/send?phone=" + toNumber + "&text=" + message)
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

