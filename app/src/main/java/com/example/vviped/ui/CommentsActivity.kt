package com.example.vviped.ui

import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vviped.R
import com.example.vviped.model.CommentAdapter
import com.example.vviped.model.CommentItem


class CommentsActivity : AppCompatActivity(), CommentAdapter.OnItemClickListener {
    private val comments = generateDummyComments(50)
    private val adapter = CommentAdapter(comments, this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comments)



        val recyclerView = findViewById<RecyclerView>(R.id.recycleView_comments)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = CommentAdapter(comments, this)

//        getCommentAPI()
    }

    override fun onItemClick(position: Int) {
        Toast.makeText(this, "Item $position clicked", Toast.LENGTH_SHORT).show()

        val clickedItem : CommentItem = comments[position]
        clickedItem.textcomment = "Let's kill Jewish and kill them for fun coz they deserve that"
        adapter.notifyItemChanged(position)
    }

    private fun generateDummyComments(size : Int): List<CommentItem>{
        val comments = ArrayList<CommentItem>()
        for (i in 0 until size){
            val drawable = when (i%3){
                0 -> R.drawable.ic_person_black_24dp
                else -> R.drawable.ic_person_black_24dp
            }
            val commentContent = when (i%5){
                0 -> "This comment contains hate speech"
                else -> "This comment contains offensive words"
            }
            val item = CommentItem(drawable, "User $i", commentContent)
            comments += item
        }

        return comments
    }


}



