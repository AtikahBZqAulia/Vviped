package com.example.vviped


import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_chat.*
import com.example.vviped.model.Chat
import java.text.SimpleDateFormat
import java.util.*

class MainChat : AppCompatActivity() {
    private lateinit var listViewType: MutableList<Int>
    private lateinit var listChat: MutableList<Chat>
    private lateinit var adapterChat: AdapterChat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        btn_BackFromChat.setOnClickListener {
            onBackPressed()
        }

        btnSendMessage.setOnClickListener{
            Toast.makeText(this@MainChat, "button send clicked", Toast.LENGTH_SHORT).show()
        }

        input_message.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEND ) {
                val idTypeChat = radiogroup_chat.checkedRadioButtonId
                val typeChat = if(idTypeChat == R.id.radioButton_myself){
                    AdapterChat.VIEW_TYPE_MY_SELF
                } else {
                    AdapterChat.VIEW_TYPE_USER
                }
                val message = input_message.text.toString().trim()
                if (message.isEmpty()){
                    Toast.makeText(this@MainChat, "Message is empty", Toast.LENGTH_SHORT).show()
                } else {
                    val dateTime = SimpleDateFormat("dd-MM-yyyy HH:mm", Locale.US).format(Date())
                    val chat = Chat(message = message, dateTime = dateTime)
                    listViewType.add(typeChat)
                    listChat.add(chat)
                    adapterChat.notifyDataSetChanged()
                    input_message.setText("")

                    var im = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    im.hideSoftInputFromWindow(currentFocus?.windowToken, 0 )




                }

            }
            true
        }
        setupAdapterRecyclerView()


}

    private fun setupAdapterRecyclerView() {
        listViewType = mutableListOf()
        listChat = mutableListOf()
        adapterChat = AdapterChat(listViewType = listViewType, listChat = listChat)
        chatRecyclerView.layoutManager = LinearLayoutManager(this)
        chatRecyclerView.adapter = adapterChat
    }


}
