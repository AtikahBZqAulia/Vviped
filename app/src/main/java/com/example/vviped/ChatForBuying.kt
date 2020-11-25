package com.example.vviped

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_chat.*
import kotlinx.android.synthetic.main.activity_chat.btn_BackFromChat
import kotlinx.android.synthetic.main.activity_chat_for_buying.*
import kotlinx.android.synthetic.main.activity_chat.product_price as product_price1
import kotlinx.android.synthetic.main.activity_chat_for_buying.product_campaign as product_campaign1
import kotlinx.android.synthetic.main.activity_chat_for_buying.product_name as product_name1

class ChatForBuying : AppCompatActivity() {


//    val sellerUsername = seller_username.text.toString().trim()
//    val productName = product_name.text.toString().trim()
//    val productPrice = product_price.text.toString().trim()
//    val productCampaign = product_campaign.text.toString().trim()

//    val msgToSeller = "Hi, " + sellerName + "!"
//            "\n" + "Saya tertarik dengan produk anda:  " + productName + " yang dijual di Vviped." +
//            "\n" + "dengan harga Rp." + productPrice + "." +
//            "\n" + "dengan hasil penjualan yang akan disumbangkan ke campaign: " + productCampaign + "."



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_for_buying)

//        val coba: TextView = findViewById(R.id.seller_username)
//        val strCoba: String = coba.text.toString()

        val sellerName = findViewById<TextView>(R.id.seller_username)
        val nameProduct = findViewById<TextView>(R.id.product_name)
        val priceProduct = findViewById<TextView>(R.id.product_price)
        val campaignProduct = findViewById<TextView>(R.id.product_campaign)

        val strSellerName = sellerName.text.toString()
        val strProductName = nameProduct.text.toString()
        val strPriceProduct = priceProduct.text.toString()
        val strCampaignProduct = campaignProduct.text.toString()

        val inputMsg = findViewById<EditText>(R.id.input_message)


        btnSendMessage.setOnClickListener {
            val message = "[VVIPED APP NOTIFICATION] \n\n" +
                    "Hai, "+  strSellerName + ". \n" +
                    "Saya tertarik dengan produk anda:  \n \n" +
                    "*" +strProductName + "*" + "\n\n" +
                    "Harga: " + strPriceProduct + "\n" +
                    "Untuk campaign: " + strCampaignProduct + "\n"
            sendWhatsapp(message)
        }
        btn_BackFromChat.setOnClickListener {
            onBackPressed()
        }
    }

    private fun sendWhatsapp(message: String) {
    val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.setPackage("com.whatsapp")
        intent.putExtra(Intent.EXTRA_TEXT, message)

        //checking whatsapp is installed or not
        if(intent.resolveActivity(packageManager) == null) {
            Toast.makeText(this,
                "Please install whatsapp to send the message to seller",
                Toast.LENGTH_SHORT).show()
            return
        }
        // starting whatsapp
        startActivity(intent)

    }


}

