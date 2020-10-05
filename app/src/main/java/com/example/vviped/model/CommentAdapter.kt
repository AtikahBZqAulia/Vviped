package com.example.vviped.model

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.vviped.ui.CommentsActivity
import com.example.vviped.R
import com.example.vviped.comment_action
import kotlinx.android.synthetic.main.comment_layout.view.*

class CommentAdapter(
    private val comments : List <CommentItem>,
    private val listener : OnItemClickListener
) : RecyclerView.Adapter<CommentAdapter.ViewHolder>() {
//inner
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {

        val prof_images = itemView.comments_profpic
        val uname_comments = itemView.comments_uname
        val text_comments = itemView.comments_text
        val comment_act = itemView.comment_act

        init{
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position : Int = adapterPosition
            if(position != RecyclerView.NO_POSITION){
            listener.onItemClick(position)
            }
        }


        fun bindView(comment: CommentItem) {
            prof_images.setImageResource(comment.imagecomment)
            uname_comments.text = comment.unamecomment
            text_comments.text = comment.textcomment

            comment_act.setOnClickListener{
                val context = comment_act.context
                val intent = Intent(context, comment_action::class.java)
                context.startActivity(intent)
            }
        }


}

    interface OnItemClickListener{
        fun onItemClick(position: Int)

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.comment_layout, parent, false)
        return ViewHolder(itemView)
    }
    override fun getItemCount() = comments.size

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val currentItem = comments[position]

        viewHolder.prof_images.setImageResource(currentItem.imagecomment)
        viewHolder.uname_comments.text = currentItem.unamecomment
        viewHolder.text_comments.text = currentItem.textcomment
    }



}
