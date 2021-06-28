package com.brian.githubuserdemo.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.brian.githubuserdemo.R
import com.brian.githubuserdemo.model.UserData
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_user.view.*

class UserListAdapter(
    private val data: ArrayList<UserData>
) : RecyclerView.Adapter<UserListAdapter.ViewHolder>() {

    lateinit var context: Context

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val icon: ImageView = itemView.img_avatar
        val name: TextView = itemView.text_name
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val inflater = LayoutInflater.from(context)
        val user = inflater.inflate(R.layout.item_user, parent, false)

        return ViewHolder(user)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        item.let {
            Glide.with(context)
                .load(it.avatarUrl)
                .apply(RequestOptions.circleCropTransform())
                .into(holder.icon)

            holder.name.text = it.login
        }

    }

    override fun getItemCount(): Int = data.size
}