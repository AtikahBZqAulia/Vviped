package com.ilkom.vviped

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import com.ilkom.vviped.model.RetrofitInterface
import com.ilkom.vviped.model.UploadResponse
import com.ilkom.vviped.model.login.Constant
import kotlinx.android.synthetic.main.activity_chat_for_buying.*
import kotlinx.android.synthetic.main.activity_selling_posts_category_item.*
import kotlinx.android.synthetic.main.sellingposts_category_layout.*
import kotlinx.android.synthetic.main.sellingposts_category_layout.product_name
import kotlinx.android.synthetic.main.sellingposts_category_layout.produk_deskripsi
import kotlinx.android.synthetic.main.sellingposts_category_layout.produk_kondisi
import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SellingPostsCategoryItem : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selling_posts_category_item)

        val buyButton = findViewById<Button>(R.id.btn_buy2)
        val sharePost = findViewById<ImageButton>(R.id.share_btn2)
        val backspace = findViewById<ImageView>(R.id.backspace2)

        val productName = intent.getStringExtra("product_name")
        product_name.text = productName

        val productCondition = intent.getStringExtra("ktg_product_condition")
        produk_kondisi.text = productCondition

        val productLoc = intent.getStringExtra("ktg_sellerlocation")
        lokasi_penjual2.text =  productLoc

        val productDesc = intent.getStringExtra("ktg_product_desc")
        produk_deskripsi.text = productDesc

        val sellerUsername = intent.getStringExtra("seller_username")
        username_post2.text = sellerUsername

        val product_price = intent.getStringExtra("product_price")
        harga_produk2.text = product_price

        val campaign_title = intent.getStringExtra("campaign_title")
       campaign_title2.text = campaign_title

        val whatsapp = intent.getStringExtra("whatsapp")
        whatsapp_number2.text = whatsapp

        val image_link = intent.getStringExtra("image_link")

        val whatsappNumber =  whatsapp_number2.text.toString()
        val seller_username =  username_post2.text.toString()
        val product_name = product_name.text.toString()
        val campaign_title2 = campaign_title2.text.toString()

        buyButton.setOnClickListener {
            val intent = Intent(this, ChatForBuying::class.java)
            intent.putExtra("seller_username", seller_username )
            intent.putExtra("product_name", product_name )
            intent.putExtra("product_price", product_price )
            intent.putExtra("campaign_title", campaign_title2 )
            intent.putExtra("whatsapp", whatsappNumber )
            startActivity(intent)
        }

        sharePost.setOnClickListener{
            val shareIntent = Intent()
            shareIntent.action = Intent.ACTION_SEND
            shareIntent.type="text/plain"
            shareIntent.putExtra(Intent.EXTRA_TEXT,
                "Bantu berdonasi dengan membeli barang: $product_name " +
                        "seharga Rp$product_price " +
                        "guna mendukung galang dana untuk campaign: $campaign_title2. " +
                        "Beli barangnya sekarang juga hanya di Vviped! Lihat gambar disini: $image_link"
            )
            val sendIntent = Intent.createChooser(shareIntent, null)
           startActivity(sendIntent)
        }

        backspace2.setOnClickListener {
            onBackPressed()
        }



    }
}